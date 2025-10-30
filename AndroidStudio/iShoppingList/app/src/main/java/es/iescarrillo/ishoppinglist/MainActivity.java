package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addProduct;
    Button addPending;
    Spinner spinnerProducts;
    Button detalles;

    ArrayList<Producto> productos;
    ArrayAdapter<Producto> adapter;

    // Lanzador para recibir resultados de ActivityDetalles
    private final ActivityResultLauncher<Intent> detalleLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        // Aquí podemos refrescar la UI si se modificó algo en ActivityDetalles
                        if (result.getResultCode() == RESULT_OK) {
                            // Por ejemplo, volver a asignar el adaptador al spinner
                            spinnerProducts.setAdapter(adapter);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias
        spinnerProducts = findViewById(R.id.spinnerProductos);
        addProduct = findViewById(R.id.addProd);
        addPending = findViewById(R.id.pendiente);
        detalles = findViewById(R.id.detalles);

        // Lista de productos
        productos = new ArrayList<>();
        productos.add(new Producto("1", "Patata", "Patata de Cádiz", true));
        productos.add(new Producto("2", "Pimiento", "Unos pican y otros no", true));
        productos.add(new Producto("3", "Cebolla", "Lloras", false));

        // Adaptador del spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProducts.setAdapter(adapter);

        // Botón detalles
        detalles.setOnClickListener(v -> {
            Producto productoSeleccionado = (Producto) spinnerProducts.getSelectedItem();
            if (productoSeleccionado != null) {
                Intent intent = new Intent(MainActivity.this, ActivityDetalles.class);
                intent.putExtra("producto", productoSeleccionado);
                detalleLauncher.launch(intent); // Lanzamos ActivityDetalles
            }
        });
    }
}
