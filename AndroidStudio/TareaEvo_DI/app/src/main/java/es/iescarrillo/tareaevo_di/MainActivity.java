package es.iescarrillo.tareaevo_di;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout dateTimeInputLayout;
    private TextInputEditText dateTimeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias al layout
        dateTimeInputLayout = findViewById(R.id.booking_date_input_layout);
        dateTimeEditText = findViewById(R.id.booking_date_edit_text);

        // Abrir DatePicker y luego TimePicker al tocar el endIcon
        dateTimeInputLayout.setEndIconOnClickListener(v -> showDateTimePicker());

        // Abrir DatePicker y luego TimePicker al tocar el TextInputEditText
        dateTimeEditText.setOnClickListener(v -> showDateTimePicker());
    }

    private void showDateTimePicker() {
        // Fecha inicial
        Calendar calendar = Calendar.getInstance();

        // DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    // Guardar fecha seleccionada
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                    // Luego abrir TimePickerDialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            MainActivity.this,
                            (timeView, hourOfDay, minute) -> {
                                String formattedTime = String.format("%02d:%02d", hourOfDay, minute);
                                // Combinar fecha y hora en el EditText
                                dateTimeEditText.setText(selectedDate + " " + formattedTime);
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true // formato 24h
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
