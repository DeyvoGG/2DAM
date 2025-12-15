// es.iescarrillo.skyzen.FoodMenuFragment.java
package es.iescarrillo.skyzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FoodMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnCarne).setOnClickListener(v ->
                NavHostFragment.findNavController(FoodMenuFragment.this)
                        .navigate(R.id.carnesFragment)
        );

        view.findViewById(R.id.btnPescado).setOnClickListener(v ->
                NavHostFragment.findNavController(FoodMenuFragment.this)
                        .navigate(R.id.pescadosFragment)
        );

        view.findViewById(R.id.btnBebida).setOnClickListener(v ->
                NavHostFragment.findNavController(FoodMenuFragment.this)
                        .navigate(R.id.bebidasFragment)
        );

        view.findViewById(R.id.btnPostre).setOnClickListener(v ->
                NavHostFragment.findNavController(FoodMenuFragment.this)
                        .navigate(R.id.postresFragment)
        );

        view.findViewById(R.id.fabCart).setOnClickListener(v ->
                NavHostFragment.findNavController(FoodMenuFragment.this)
                        .navigate(R.id.carritoFragment)
        );
    }
}