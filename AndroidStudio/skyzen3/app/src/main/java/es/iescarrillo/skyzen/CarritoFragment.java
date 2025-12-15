// es.iescarrillo.skyzen.CarritoFragment.java
package es.iescarrillo.skyzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CarritoFragment extends Fragment {

    private LinearLayout cartItemsContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carrito, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartItemsContainer = view.findViewById(R.id.cartItemsContainer);

        // Navegación desde la barra superior
        view.findViewById(R.id.tabCarnes).setOnClickListener(v ->
                NavHostFragment.findNavController(CarritoFragment.this)
                        .navigate(R.id.carnesFragment)
        );
        view.findViewById(R.id.tabBebidas).setOnClickListener(v ->
                NavHostFragment.findNavController(CarritoFragment.this)
                        .navigate(R.id.bebidasFragment)
        );
        view.findViewById(R.id.tabPescados).setOnClickListener(v ->
                NavHostFragment.findNavController(CarritoFragment.this)
                        .navigate(R.id.pescadosFragment)
        );
        view.findViewById(R.id.tabPostres).setOnClickListener(v ->
                NavHostFragment.findNavController(CarritoFragment.this)
                        .navigate(R.id.postresFragment)
        );
        // tabCarrito: ya estamos aquí, no hace nada

        // Botones del carrito
        Button btnClear = view.findViewById(R.id.btnClearCart);
        Button btnConfirm = view.findViewById(R.id.btnConfirmOrder);

        btnClear.setOnClickListener(v -> {
            cartItemsContainer.removeAllViews();
            Toast.makeText(getContext(), "Carrito vaciado", Toast.LENGTH_SHORT).show();
        });

        btnConfirm.setOnClickListener(v -> {
            if (cartItemsContainer.getChildCount() == 0) {
                Toast.makeText(getContext(), "Tu carrito está vacío", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Pedido confirmado. ¡Gracias!", Toast.LENGTH_LONG).show();
            }
        });
    }
}