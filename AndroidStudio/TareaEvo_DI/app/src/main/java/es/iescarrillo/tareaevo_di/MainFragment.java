package es.iescarrillo.tareaevo_di;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainFragment extends Fragment {

    private TextInputLayout dateTimeInputLayout;
    private TextInputEditText dateTimeEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Dentro de onViewCreated en MainFragment.java

        FloatingActionButton fabAdd = view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            NavHostFragment.findNavController(MainFragment.this)
                    .navigate(R.id.action_mainFragment_to_createHabitFragment);
        });

        // Referencias
        dateTimeInputLayout = view.findViewById(R.id.booking_date_input_layout);
        dateTimeEditText = view.findViewById(R.id.booking_date_edit_text);

        // Configurar listeners
        dateTimeInputLayout.setEndIconOnClickListener(v -> showDateTimePicker());
        dateTimeEditText.setOnClickListener(v -> showDateTimePicker());
    }

    private void showDateTimePicker() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            requireContext(),
                            (timeView, hourOfDay, minute) -> {
                                String formattedTime = String.format("%02d:%02d", hourOfDay, minute);
                                dateTimeEditText.setText(selectedDate + " " + formattedTime);
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                    );
                    timePickerDialog.show();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}