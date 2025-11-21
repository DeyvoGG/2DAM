package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityDetalles extends AppCompatActivity {
    Producto producto;
    Button volverInicio;
    Button editarPropiedades;
    TextView id;
    TextView nombre;
    TextView descripcion;
    TextView estado;
    private final ActivityResultLauncher<Intent> editarLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if(result.getResultCode() == RESULT_OK){
                            Producto productoEditado = (Producto) result.getData().getSerializableExtra("productoEditado");
                            if(productoEditado != null){
                                producto = productoEditado;
                                actualizarTextViews(); // Actualizamos la UI
                            }
                        }
                    }
            );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar TextViews y botones
        id = findViewById(R.id.idproducto);
        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcion);
        estado = findViewById(R.id.textView4);
        volverInicio = findViewById(R.id.btn_inicio);
        editarPropiedades = findViewById(R.id.editarPropiedades);

        // Recibir el objeto Producto desde MainActivity
        Producto producto = (Producto) getIntent().getSerializableExtra("producto");

        if (producto != null) {
            id.setText("ID: " + producto.getId());
            nombre.setText("Nombre: " + producto.getNombreProducto());
            descripcion.setText("Descripcion: " + producto.getInfoProducto());
            estado.setText("Estado: " + (producto.getEstadoCompra() ? "Disponible" : "No disponible"));
        }

        // Botón para volver a MainActivity
        volverInicio.setOnClickListener(v -> {
            // Devuelve RESULT_OK para que MainActivity pueda refrescar la UI
            setResult(RESULT_OK);
            finish();
        });
        editarPropiedades.setOnClickListener(v -> {
            if (producto != null) {  // Usamos la variable producto ya inicializada
                Intent intent = new Intent(ActivityDetalles.this, ActivityEditar.class);
                intent.putExtra("producto", producto); // Pasamos el producto a ActivityEditar
                editarLauncher.launch(intent); // Usamos ActivityResultLauncher si queremos recibir cambios
            }
        });




    }
    private void actualizarTextViews() {

        if (producto != null) {
            id.setText("ID: " + producto.getId());
            nombre.setText("Nombre: " + producto.getNombreProducto());
            descripcion.setText("Descripcion: " + producto.getInfoProducto());
            estado.setText("Estado: " + (producto.getEstadoCompra() ? "Disponible" : "No disponible"));
        }
    }

}
