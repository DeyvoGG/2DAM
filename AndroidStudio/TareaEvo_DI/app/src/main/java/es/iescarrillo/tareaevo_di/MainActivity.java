package es.iescarrillo.tareaevo_di;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout dateInputLayout;
    private TextInputEditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateInputLayout = findViewById(R.id.booking_date_input_layout);
        dateEditText = findViewById(R.id.booking_date_edit_text);

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    dateEditText.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Abrir DatePicker al tocar el ícono
        dateInputLayout.setEndIconOnClickListener(v -> datePickerDialog.show());

        // Abrir DatePicker al tocar el campo de texto
        dateEditText.setOnClickListener(v -> datePickerDialog.show());

// Crear el builder de restricciones
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();

// Crear el DatePicker con las restricciones
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraintsBuilder.build())
                .build();
    }
}
