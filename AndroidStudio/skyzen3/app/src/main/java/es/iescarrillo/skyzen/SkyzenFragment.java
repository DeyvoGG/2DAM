package es.iescarrillo.skyzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.iescarrillo.skyzen.R;

public class SkyzenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skyzen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Opcional: si usas AppCompatActivity, podrías hacer setSupportActionBar(toolbar)

        // Configurar BottomNavigationView
        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_back) {
                // Volver atrás (pop back stack)
                NavHostFragment.findNavController(SkyzenFragment.this).navigateUp();
                return true;
            } else if (id == R.id.nav_map) {
                Toast.makeText(getContext(), "Mapa seleccionado", Toast.LENGTH_SHORT).show();
                // Aquí irías a otro fragment o acción
                return true;
            } else if (id == R.id.nav_stewardess) {
                Toast.makeText(getContext(), "Azafata seleccionada", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_food) {
                Toast.makeText(getContext(), "Comida seleccionada", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
}