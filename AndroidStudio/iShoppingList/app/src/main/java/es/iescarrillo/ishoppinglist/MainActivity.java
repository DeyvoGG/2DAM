package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

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
    Button detalles;
    Spinner spinnerProducts;

    ArrayList<Producto> productos;
    ArrayAdapter<Producto> adapter;

    // Lanzador para añadir un producto
    private final ActivityResultLauncher<Intent> anyadirLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if(result.getResultCode() == RESULT_OK) {
                            Producto productoNuevo = (Producto) result.getData().getSerializableExtra("productoNuevo");
                            if(productoNuevo != null){
                                productos.add(productoNuevo);
                                adapter.notifyDataSetChanged(); // Refrescar Spinner
                                spinnerProducts.setSelection(productos.size() - 1); // Seleccionar el nuevo producto
                            }
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

        // Inicializar referencias
        spinnerProducts = findViewById(R.id.spinnerProductos);
        addProduct = findViewById(R.id.addProd);
        addPending = findViewById(R.id.pendiente);
        detalles = findViewById(R.id.detalles);

        // Lista de productos iniciales
        productos = new ArrayList<>();
        productos.add(new Producto("1", "Patata", "Patata de Cádiz", true));
        productos.add(new Producto("2", "Pimiento", "Unos pican y otros no", true));
        productos.add(new Producto("3", "Cebolla", "Lloras", false));

        // Adaptador del Spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProducts.setAdapter(adapter);


        // Botón Detalles
        detalles.setOnClickListener(v -> {
            Producto productoSeleccionado = (Producto) spinnerProducts.getSelectedItem();
            if(productoSeleccionado != null){
                Intent intent = new Intent(MainActivity.this, ActivityDetalles.class);
                intent.putExtra("producto", productoSeleccionado);
                startActivity(intent);
            }
        });
    }
    public void addProduct(View view) {
        Intent intent = new Intent(this, ActivityAnyadir.class);
        anyadirLauncher.launch(intent); // Lanzar usando el launcher
    }

}
