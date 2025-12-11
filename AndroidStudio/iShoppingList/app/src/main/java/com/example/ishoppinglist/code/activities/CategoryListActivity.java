package com.example.ishoppinglist.code.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.adapters.CategoryRecyclerViewAdapter;
import com.example.ishoppinglist.code.database.CategoryDAO;
import com.example.ishoppinglist.code.models.Category;

import java.util.List;
import java.util.UUID;

public class CategoryListActivity extends AppCompatActivity {

    private CategoryDAO categoryDAO;
    private List<Category> categories;
    private RecyclerView rvCategories;
    private CategoryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryDAO = new CategoryDAO(this);
        loadCategories();

        rvCategories = findViewById(R.id.rvCategories);
        Button btnAddCategory = findViewById(R.id.btnAddCategory);

        adapter = new CategoryRecyclerViewAdapter(categories, new CategoryRecyclerViewAdapter.OnCategoryActionListener() {
            @Override
            public void onEdit(Category category) {
                showEditDialog(category);
            }

            @Override
            public void onDelete(Category category) {
                categoryDAO.delete(category.getId());
                loadCategories();
                adapter.notifyDataSetChanged();
                Toast.makeText(CategoryListActivity.this, "Categoría eliminada", Toast.LENGTH_SHORT).show();
            }
        });
        rvCategories.setAdapter(adapter);

        btnAddCategory.setOnClickListener(v -> showAddDialog());
    }

    private void loadCategories() {
        categories = categoryDAO.getAll();
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nueva Categoría");

        View view = getLayoutInflater().inflate(R.layout.dialog_category, null);
        EditText etName = view.findViewById(R.id.etCategoryName);
        EditText etDescription = view.findViewById(R.id.etCategoryDescription);
        EditText etColor = view.findViewById(R.id.etCategoryColor);

        etColor.setHint("#FF5733");

        builder.setView(view);
        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String name = etName.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();
            String color = etColor.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Nombre es obligatorio", Toast.LENGTH_SHORT).show();
                return;
            }
            if (color.isEmpty()) color = "#FFFFFF";

            Category category = new Category();
            category.setName(name);
            category.setDescription(desc);
            category.setColorHex(color);

            categoryDAO.insert(category);
            loadCategories();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Categoría añadida", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void showEditDialog(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Categoría");

        View view = getLayoutInflater().inflate(R.layout.dialog_category, null);
        EditText etName = view.findViewById(R.id.etCategoryName);
        EditText etDescription = view.findViewById(R.id.etCategoryDescription);
        EditText etColor = view.findViewById(R.id.etCategoryColor);

        etName.setText(category.getName());
        etDescription.setText(category.getDescription());
        etColor.setText(category.getColorHex());

        builder.setView(view);
        builder.setPositiveButton("Actualizar", (dialog, which) -> {
            String name = etName.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();
            String color = etColor.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Nombre es obligatorio", Toast.LENGTH_SHORT).show();
                return;
            }
            if (color.isEmpty()) color = "#FFFFFF";

            category.setName(name);
            category.setDescription(desc);
            category.setColorHex(color);

            categoryDAO.update(category);
            loadCategories();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Categoría actualizada", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategories();
        adapter.notifyDataSetChanged();
    }
}