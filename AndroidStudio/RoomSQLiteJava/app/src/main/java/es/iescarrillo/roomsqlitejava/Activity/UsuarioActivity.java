package es.iescarrillo.roomsqlitejava.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.iescarrillo.roomsqlitejava.DataBase.AppDatabase;
import es.iescarrillo.roomsqlitejava.DataBase.DatabaseClient;
import es.iescarrillo.roomsqlitejava.R;
import es.iescarrillo.roomsqlitejava.Modelos.Usuario;

/**
 * Actividad para crear un nuevo usuario y guardarlo en la base de datos.
 */
public class UsuarioActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etEmail;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        etNombre = findViewById(R.id.etNombreUsuario);
        etEmail = findViewById(R.id.etEmailUsuario);
        btnGuardar = findViewById(R.id.btnGuardarUsuario);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();
            }
        });
    }

    private void guardarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear objeto Usuario
        Usuario usuario = new Usuario(nombre, email);

        // Ejecutar inserción en segundo plano
        new Thread(() -> {
            AppDatabase db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
            db.daoUsuario().insertarUsuario(usuario);

            // Volver al hilo principal para mostrar mensaje y cerrar actividad
            runOnUiThread(() -> {
                Toast.makeText(UsuarioActivity.this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
                finish(); // Regresa a MainActivity
            });
        }).start();
    }
}