package es.iescarrillo.skyzen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private String selectedLanguage = "es"; // valor por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configurar Spinner de idiomas
        Spinner spinnerLanguage = findViewById(R.id.spinnerLanguage);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.language_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        // Opcional: detectar cambio de idioma
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String language = parent.getItemAtPosition(position).toString();
                // Aquí podrías cambiar el idioma de la app (opcional avanzado)
                // Por ahora, solo guardamos o mostramos un mensaje
                String code;
                switch (position) {
                    case 0: code = "es"; break; // Español
                    case 1: code = "en"; break; // Inglés
                    case 2: code = "fr"; break; // Francés
                    case 3: code = "de"; break; // Alemán
                    case 4: code = "it"; break; // Italiano
                    default: code = "es";
                }
                selectedLanguage = code;
                // Opcional: mostrar feedback
                // Toast.makeText(Login.this, "Idioma: " + language, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLanguage = "es";
            }
        });

        // Botón de Entrar
        Button btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}