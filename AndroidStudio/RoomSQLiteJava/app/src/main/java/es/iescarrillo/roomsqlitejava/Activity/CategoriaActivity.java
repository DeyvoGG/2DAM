package es.iescarrillo.roomsqlitejava.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.iescarrillo.roomsqlitejava.DataBase.AppDatabase;
import es.iescarrillo.roomsqlitejava.Modelos.Categoria;
import es.iescarrillo.roomsqlitejava.DataBase.DatabaseClient;
import es.iescarrillo.roomsqlitejava.R;

/**
 * Actividad para crear una nueva categoría y guardarla en la base de datos.
 */
public class CategoriaActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etDescripcion;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        etNombre = findViewById(R.id.etNombreCategoria);
        etDescripcion = findViewById(R.id.etDescripcionCategoria);
        btnGuardar = findViewById(R.id.btnGuardarCategoria);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCategoria();
            }
        });
    }

    private void guardarCategoria() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre de la categoría es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si la descripción está vacía, se guarda como cadena vacía (puedes usar null si prefieres)
        Categoria categoria = new Categoria(nombre, descripcion);

        new Thread(() -> {
            AppDatabase db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
            db.daoCategoria().insertarCategoria(categoria);

            runOnUiThread(() -> {
                Toast.makeText(CategoriaActivity.this, "Categoría guardada correctamente", Toast.LENGTH_SHORT).show();
                finish(); // Regresa a MainActivity
            });
        }).start();
    }
}