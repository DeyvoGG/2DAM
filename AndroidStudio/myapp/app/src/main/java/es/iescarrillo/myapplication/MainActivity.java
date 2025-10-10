package es.iescarrillo.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextText;
    private TextView textView2;
    private Button buttonEnglish, buttonSpanish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajustar el padding con los system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular los elementos del layout
        editTextText = findViewById(R.id.editTextText);
        textView2 = findViewById(R.id.textView2);
        buttonEnglish = findViewById(R.id.button2);
        buttonSpanish = findViewById(R.id.button3);

        // El TextView empieza invisible
        textView2.setVisibility(View.INVISIBLE);

        // Botón English
        buttonEnglish.setOnClickListener(v -> mostrarMensaje("english"));

        // Botón Spanish
        buttonSpanish.setOnClickListener(v -> mostrarMensaje("spanish"));
    }

    private void mostrarMensaje(String idioma) {
        String nombre = editTextText.getText().toString().trim();

        // Mostrar el TextView
        textView2.setVisibility(View.VISIBLE);

        if (nombre.isEmpty()) {
            textView2.setText("Escriba algo");
        } else {
            if (idioma.equals("spanish")) {
                textView2.setText("Hola, soy " + nombre);
            } else {
                textView2.setText("Hello, I'm " + nombre);
            }
        }
    }

}
