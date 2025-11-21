package es.iescarrillo.ishoppinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityEditar extends AppCompatActivity {

    EditText editarId, editarNombre, editarDescripcion;
    Button guardar, volverInicio;
    Producto producto;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editarId = findViewById(R.id.editarId);
        editarNombre = findViewById(R.id.editatNombre);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        guardar = findViewById(R.id.guardar);
        volverInicio = findViewById(R.id.volverInicio);

        // Recibir producto
        producto = (Producto) getIntent().getSerializableExtra("producto");
        if(producto != null){
            editarId.setText(producto.getId());
            editarNombre.setText(producto.getNombreProducto());
            editarDescripcion.setText(producto.getInfoProducto());
        }

        // Guardar cambios
        guardar.setOnClickListener(v -> {
            producto.setId(editarId.getText().toString());
            producto.setNombreProducto(editarNombre.getText().toString());
            producto.setInfoProducto(editarDescripcion.getText().toString());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("productoEditado", producto);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Volver sin guardar
        volverInicio.setOnClickListener(v -> finish());
    }
}
