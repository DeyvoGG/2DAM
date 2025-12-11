package com.example.ishoppinglist.code.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.adapters.ProductAdapter;
import com.example.ishoppinglist.code.database.ProductDAO;
import com.example.ishoppinglist.code.models.Product;

import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private ProductDAO productDAO;
    private List<Product> nonPendingProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar DAO
        productDAO = new ProductDAO(this);

        // Cargar productos NO pendientes (isCompleted = true)
        nonPendingProducts = productDAO.getCompletedByShoppingListId(1);

        // Obtener componentes
        TextView titleNoPending = findViewById(R.id.tvProductsPending);
        Spinner spProductsNoPending = findViewById(R.id.spProductsnoPending);
        Button btnCancel = findViewById(R.id.btnCancel2);
        Button btnSave = findViewById(R.id.btnSave1);

        // Crear y asignar adaptador al Spinner
        ProductAdapter productAdapter = new ProductAdapter(ShoppingListActivity.this, 0, nonPendingProducts);
        spProductsNoPending.setAdapter(productAdapter);

        // Botón Cancelar
        btnCancel.setOnClickListener(v -> {
            startActivity(new Intent(ShoppingListActivity.this, MainActivity.class));
            finish();
        });

        // Botón Guardar
        btnSave.setOnClickListener(v -> {
            Product selectedProduct = (Product) spProductsNoPending.getSelectedItem();
            if (selectedProduct != null) {
                // Cambiar a pendiente: isCompleted = false
                selectedProduct.setCompleted(false);
                int updated = productDAO.update(selectedProduct);
                if (updated > 0) {
                    Toast.makeText(ShoppingListActivity.this, "Este producto ahora estará pendiente. Gracias por el cambio.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ShoppingListActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(ShoppingListActivity.this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ShoppingListActivity.this, "No se seleccionó ningún producto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}