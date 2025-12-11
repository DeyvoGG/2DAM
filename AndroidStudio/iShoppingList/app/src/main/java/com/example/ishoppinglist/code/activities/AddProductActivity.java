package com.example.ishoppinglist.code.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.database.CategoryDAO;
import com.example.ishoppinglist.code.database.ProductDAO;
import com.example.ishoppinglist.code.models.Category;
import com.example.ishoppinglist.code.models.Product;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private Spinner spCategory;
    private List<Category> categories;
    private long selectedCategoryId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar DAOs
        productDAO = new ProductDAO(this);
        categoryDAO = new CategoryDAO(this);

        // Obtener vistas
        EditText productNameInput = findViewById(R.id.etNewName);
        EditText productDescriptionInput = findViewById(R.id.etNewDescription);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch productStateSwitch = findViewById(R.id.sw1);
        spCategory = findViewById(R.id.spCategory);
        Button saveButton = findViewById(R.id.btnSave2);
        Button cancelButton = findViewById(R.id.btnCancel3);

        // Cargar categorías en el Spinner
        loadCategoriesIntoSpinner();

        // Configurar botón Cancelar
        cancelButton.setOnClickListener(v -> {
            Toast.makeText(this, "Regresando a la página principal...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        // Configurar botón Guardar
        saveButton.setOnClickListener(v -> {
            String name = productNameInput.getText().toString().trim();
            String description = productDescriptionInput.getText().toString().trim();
            boolean isCompleted = productStateSwitch.isChecked();

            if (name.isEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }
            if (description.isEmpty()) {
                Toast.makeText(this, "La descripción no puede estar vacía", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedCategoryId == -1) {
                Toast.makeText(this, "Selecciona una categoría", Toast.LENGTH_SHORT).show();
                return;
            }

            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setDescription(description);
            newProduct.setCompleted(isCompleted);
            newProduct.setShoppingListId(1); // Lista principal
            newProduct.setCategoryId(selectedCategoryId);

            long id = productDAO.insert(newProduct);
            if (id != -1) {
                Toast.makeText(this, "Producto agregado con éxito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCategoriesIntoSpinner() {
        categories = categoryDAO.getAll();
        if (categories.isEmpty()) {
            // Si no hay categorías, inserta una por defecto (puede suceder en primera ejecución)
            Category defaultCat = new Category();
            defaultCat.setName("Sin categoría");
            defaultCat.setDescription("Categoría predeterminada");
            defaultCat.setColorHex("#FFFFFF");
            categoryDAO.insert(defaultCat);
            categories = categoryDAO.getAll();
        }

        // Extraer nombres para el Spinner
        String[] categoryNames = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryNames[i] = categories.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        // Seleccionar primera categoría por defecto
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryId = categories.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategoryId = -1;
            }
        });

        // Seleccionar la primera (índice 0) automáticamente
        if (!categories.isEmpty()) {
            spCategory.setSelection(0);
            selectedCategoryId = categories.get(0).getId();
        }
    }
}