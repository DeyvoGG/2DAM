// es.iescarrillo.skyzen.BebidasFragment.java
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

public class BebidasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bebidas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tabCarnes).setOnClickListener(v ->
                NavHostFragment.findNavController(BebidasFragment.this)
                        .navigate(R.id.carnesFragment)
        );
        view.findViewById(R.id.tabBebidas).setOnClickListener(v -> {});
        view.findViewById(R.id.tabPescados).setOnClickListener(v ->
                NavHostFragment.findNavController(BebidasFragment.this)
                        .navigate(R.id.pescadosFragment)
        );
        view.findViewById(R.id.tabPostres).setOnClickListener(v ->
                NavHostFragment.findNavController(BebidasFragment.this)
                        .navigate(R.id.postresFragment)
        );
        view.findViewById(R.id.tabCarrito).setOnClickListener(v ->
                NavHostFragment.findNavController(BebidasFragment.this)
                        .navigate(R.id.carritoFragment)
        );

        setupItemClick(view, R.id.itemAgua, "Agua");
        setupItemClick(view, R.id.itemRefresco, "Refresco");
        setupItemClick(view, R.id.itemCerveza, "Cerveza");
        setupItemClick(view, R.id.itemVino, "Vino");
        setupItemClick(view, R.id.itemCocktail, "Cocktail");
        setupItemClick(view, R.id.itemJugo, "Jugo");
    }

    private void setupItemClick(View view, int resId, String itemName) {
        view.findViewById(resId).setOnClickListener(v ->
                Toast.makeText(getContext(), "Seleccionaste: " + itemName, Toast.LENGTH_SHORT).show()
        );
    }
}