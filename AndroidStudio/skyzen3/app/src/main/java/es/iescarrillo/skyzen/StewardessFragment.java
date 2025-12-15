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

public class StewardessFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stewardess, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Añadir listeners a los textos (opcional)
        view.findViewById(R.id.txtMedical).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Solicitando atención médica...", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.txtDiscomfort).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Un empleado viene en camino...", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.txtFood).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Su pedido de comida ha sido enviado.", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.txtCleaning).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Equipo de limpieza en camino.", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.txtFlightInfo).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Mostrando información del vuelo...", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.txtOther).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Por favor, especifique su necesidad.", Toast.LENGTH_SHORT).show();
        });
    }
}