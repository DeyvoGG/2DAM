package com.example.ishoppinglist.code.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.adapters.ProductRecyclerViewAdapter;
import com.example.ishoppinglist.code.database.ProductDAO;
import com.example.ishoppinglist.code.models.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductDAO productDAO;
    private List<Product> pendingProducts;
    private RecyclerView rvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        productDAO = new ProductDAO(this);
        pendingProducts = productDAO.getPendingByShoppingListId(1);

        // Inicializar vistas
        rvProducts = findViewById(R.id.rvProducts);
        Button btnAddNewProduct = findViewById(R.id.btnnew);
        Button btnAddPendingProduct = findViewById(R.id.btnPending);
        Button btnManageCategories = findViewById(R.id.btnManageCategories); // Nuevo botón

        // Configurar RecyclerView
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(pendingProducts, product -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
        rvProducts.setAdapter(adapter);

        // Configurar botones
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnPending) {
                    startActivity(new Intent(MainActivity.this, ShoppingListActivity.class));
                } else if (v.getId() == R.id.btnnew) {
                    startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                } else if (v.getId() == R.id.btnManageCategories) {
                    startActivity(new Intent(MainActivity.this, CategoryListActivity.class));
                }
            }
        };

        btnAddPendingProduct.setOnClickListener(buttonClickListener);
        btnAddNewProduct.setOnClickListener(buttonClickListener);
        btnManageCategories.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar productos pendientes al volver
        pendingProducts = productDAO.getPendingByShoppingListId(1);
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(pendingProducts, product -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
        rvProducts.setAdapter(adapter);
    }
}