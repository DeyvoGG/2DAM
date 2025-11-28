package es.iescarrillo.tareaevo_di;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CreateHabitFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_habit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(CreateHabitFragment.this)
                    .navigateUp();
        });

        Button btnSave = view.findViewById(R.id.btnSaveHabit);
        btnSave.setOnClickListener(v -> {
            // Aquí iría la lógica para guardar el hábito (en BD, SharedPrefs, etc.)
            Toast.makeText(getContext(), "Hábito guardado (pendiente de implementar)", Toast.LENGTH_SHORT).show();

            // Volver al fragment principal
            NavHostFragment.findNavController(CreateHabitFragment.this)
                    .navigateUp();
        });
    }
}