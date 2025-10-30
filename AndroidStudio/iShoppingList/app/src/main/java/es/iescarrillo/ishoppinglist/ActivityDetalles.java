package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityDetalles extends AppCompatActivity {

    Button volverInicio;
    Button editarPropiedades;
    TextView id;
    TextView nombre;
    TextView descripcion;
    TextView estado;

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

        // Botón para editar propiedades (pendiente implementar)
        editarPropiedades.setOnClickListener(v -> {
            // Aquí puedes abrir un diálogo o nueva Activity para editar el producto
        });
    }
}
