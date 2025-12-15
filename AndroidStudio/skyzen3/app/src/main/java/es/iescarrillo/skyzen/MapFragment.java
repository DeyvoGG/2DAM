// es.iescarrillo.skyzen.MapFragment.java
package es.iescarrillo.skyzen;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Polyline flightPath;
    private Marker planeMarker;

    // Coordenadas
    private final LatLng MADRID = new LatLng(40.4168, -3.7038);
    private final LatLng BARCELONA = new LatLng(41.3851, 2.1734);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Añadir marcadores de ciudades
        mMap.addMarker(new MarkerOptions().position(MADRID).title("Madrid"));
        mMap.addMarker(new MarkerOptions().position(BARCELONA).title("Barcelona"));

        // Dibujar línea de vuelo (roja, gruesa)
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(MADRID)
                .add(BARCELONA)
                .color(Color.RED)
                .width(8f)
                .zIndex(1); // Por debajo del marcador del avión

        flightPath = mMap.addPolyline(polylineOptions);

        // Añadir marcador de avión (más pequeño)
        planeMarker = mMap.addMarker(new MarkerOptions()
                .position(MADRID)
                .title("Vuelo SKY123")
                .icon(getSmallerAirplaneIcon())
                .anchor(0.5f, 0.5f)
                .zIndex(2));

        // Centrar el mapa
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.8, -0.8), 6));

        // Iniciar animación después de un breve retraso
        mMap.setOnMapLoadedCallback(() -> animateFlight());
    }

    private void animateFlight() {
        // Obtener puntos interpolados entre Madrid y Barcelona
        List<LatLng> points = interpolate(MADRID, BARCELONA, 100);

        ValueAnimator animator = ValueAnimator.ofInt(0, points.size() - 1);
        animator.setDuration(5000); // 5 segundos
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);

        animator.addUpdateListener(animation -> {
            int index = (int) animation.getAnimatedValue();
            LatLng point = points.get(index);

            // Mover el marcador del avión
            planeMarker.setPosition(point);

            // Opcional: rotar el avión (no con marcador por defecto, pero puedes usar un icono personalizado)
        });

        animator.start();
    }

    // Interpolar puntos entre dos coordenadas
    private List<LatLng> interpolate(LatLng start, LatLng end, int numPoints) {
        List<LatLng> points = new ArrayList<>();
        double latStep = (end.latitude - start.latitude) / (numPoints - 1);
        double lngStep = (end.longitude - start.longitude) / (numPoints - 1);

        for (int i = 0; i < numPoints; i++) {
            double lat = start.latitude + (latStep * i);
            double lng = start.longitude + (lngStep * i);
            points.add(new LatLng(lat, lng));
        }
        return points;
    }
    private BitmapDescriptor getSmallerAirplaneIcon() {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_airplane, null);
        if (drawable == null) {
            // Si no hay icono personalizado, usa el marcador azul pequeño
            return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        }

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        // Escalar a 60x60 píxeles (ajusta según necesites)
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap);
    }
}
