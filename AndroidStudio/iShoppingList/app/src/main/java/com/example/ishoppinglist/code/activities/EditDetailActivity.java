package com.example.ishoppinglist.code.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.database.ProductDAO;
import com.example.ishoppinglist.code.models.Product;

public class EditDetailActivity extends AppCompatActivity {

    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar DAO
        productDAO = new ProductDAO(this);

        // Obtener componentes
        EditText nameEdit = findViewById(R.id.etNameEdit);
        EditText detailsEdit = findViewById(R.id.etDescriptionEdit);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch sw2 = findViewById(R.id.swChangueStatus);
        Button btnCancel = findViewById(R.id.btnCancel4);
        Button btnSave = findViewById(R.id.btnSave3);

        // Obtener el ID del producto desde el Intent
        long idProduct = getIntent().getLongExtra("productid", -1);
        Product edit = (idProduct != -1) ? productDAO.getById(idProduct) : null;

        // Rellenar campos si el producto existe
        if (edit != null) {
            nameEdit.setText(edit.getName());
            detailsEdit.setText(edit.getDescription());
            // En tu lógica: Switch ON = "completado", así que:
            sw2.setChecked(edit.isCompleted());
        }

        // Botón Cancelar
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditDetailActivity.this, MainActivity.class));
                finish();
            }
        });

        // Botón Guardar
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString().trim();
                String description = detailsEdit.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(EditDetailActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (description.isEmpty()) {
                    Toast.makeText(EditDetailActivity.this, "La descripción no puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (edit != null) {
                    edit.setName(name);
                    edit.setDescription(description);
                    edit.setCompleted(sw2.isChecked()); // Switch ON → completado = true

                    int updated = productDAO.update(edit);
                    if (updated > 0) {
                        Toast.makeText(EditDetailActivity.this, "Producto actualizado con éxito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditDetailActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(EditDetailActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}