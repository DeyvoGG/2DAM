package es.iescarrillo.ishoppinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityAnyadir extends AppCompatActivity {

    EditText anyadirId;
    EditText anyadirNombre;
    EditText anyadirDescripcion;
    Button cancelar;
    Button anyadirGuardar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anyadir);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        anyadirId = findViewById(R.id.anyadirid);
        anyadirNombre = findViewById(R.id.anyadirNombre);
        anyadirDescripcion = findViewById(R.id.anyadirDescripcion);
        cancelar = findViewById(R.id.cancelar);
        anyadirGuardar = findViewById(R.id.anyadirGuardar);

        // Botón Cancelar
        cancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED); // No se añade nada
            finish();
        });

        // Botón Guardar
        anyadirGuardar.setOnClickListener(v -> {
            // Crear un nuevo Producto con los datos introducidos
            String id = anyadirId.getText().toString();
            String nombre = anyadirNombre.getText().toString();
            String descripcion = anyadirDescripcion.getText().toString();

            Producto nuevoProducto = new Producto(id, nombre, descripcion, true);

            // Devolver el producto a MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("productoNuevo", nuevoProducto);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
