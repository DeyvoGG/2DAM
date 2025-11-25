package es.iescarrillo.ejemplodate_time_picker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import androidx.core.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnSelectDate;
    private Button btnSelectRange;
    private Button btnSelectTime;
    private TextView tvSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSelectRange = findViewById(R.id.btnSelectRange);
        btnSelectTime = findViewById(R.id.btnSelectTime);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        //-----------------------------------------
        //   DATE PICKER (selección de 1 fecha)
        //-----------------------------------------
        MaterialDatePicker.Builder<Long> builder =
                MaterialDatePicker.Builder.datePicker();

        builder.setTitleText("Selecciona una fecha");
        final MaterialDatePicker<Long> datePicker = builder.build();

        btnSelectDate.setOnClickListener(v ->
                datePicker.show(getSupportFragmentManager(), "DATE_PICKER")
        );

        datePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String fecha = sdf.format(new Date(selection));
            tvSelectedDate.setText("Fecha seleccionada: " + fecha);
        });

        //-----------------------------------------
        // RANGE PICKER
        //-----------------------------------------
        MaterialDatePicker.Builder<Pair<Long, Long>> rangeBuilder =
                MaterialDatePicker.Builder.dateRangePicker();

        rangeBuilder.setTitleText("Selecciona un rango de fechas");
        final MaterialDatePicker<Pair<Long, Long>> rangePicker = rangeBuilder.build();

        btnSelectRange.setOnClickListener(v ->
                rangePicker.show(getSupportFragmentManager(), "RANGE_DATE_PICKER")
        );

        rangePicker.addOnPositiveButtonClickListener(selection -> {
            Long inicio = selection.first;
            Long fin = selection.second;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String fechaInicio = sdf.format(new Date(inicio));
            String fechaFin = sdf.format(new Date(fin));

            tvSelectedDate.setText("Rango: " + fechaInicio + " - " + fechaFin);
        });

        //-----------------------------------------
        //   TIME PICKER
        //-----------------------------------------
        btnSelectTime.setOnClickListener(v -> {

            // Crear el TimePicker
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Selecciona una hora")
                    .build();

            // Mostrarlo
            timePicker.show(getSupportFragmentManager(), "TIME_PICKER");

            // Capturar la hora seleccionada
            timePicker.addOnPositiveButtonClickListener(view -> {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String horaFormateada = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);

                tvSelectedDate.setText("Hora seleccionada: " + horaFormateada);
            });
        });

    }
}
