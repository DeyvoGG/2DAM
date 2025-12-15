// es.iescarrillo.skyzen.CarnesFragment.java
package es.iescarrillo.skyzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CarnesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carnes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tabCarnes).setOnClickListener(v -> {});
        view.findViewById(R.id.tabBebidas).setOnClickListener(v ->
                NavHostFragment.findNavController(CarnesFragment.this)
                        .navigate(R.id.bebidasFragment)
        );
        view.findViewById(R.id.tabPescados).setOnClickListener(v ->
                NavHostFragment.findNavController(CarnesFragment.this)
                        .navigate(R.id.pescadosFragment)
        );
        view.findViewById(R.id.tabPostres).setOnClickListener(v ->
                NavHostFragment.findNavController(CarnesFragment.this)
                        .navigate(R.id.postresFragment)
        );
        view.findViewById(R.id.tabCarrito).setOnClickListener(v ->
                NavHostFragment.findNavController(CarnesFragment.this)
                        .navigate(R.id.carritoFragment)
        );

        setupItemClick(view, R.id.itemBurguer, "Burguers");
        setupItemClick(view, R.id.itemPluma, "Pluma Ibérica");
        setupItemClick(view, R.id.itemPechuga, "Pechuga");
        setupItemClick(view, R.id.itemSolomillo, "Solomillo");
        setupItemClick(view, R.id.itemCachopo, "Cachopo");
        setupItemClick(view, R.id.itemAlitas, "Alitas");
        setupItemClick(view, R.id.itemPato, "Pato");
    }

    private void setupItemClick(View view, int resId, String itemName) {
        view.findViewById(resId).setOnClickListener(v ->
                Toast.makeText(getContext(), "Seleccionaste: " + itemName, Toast.LENGTH_SHORT).show()
        );
    }
}