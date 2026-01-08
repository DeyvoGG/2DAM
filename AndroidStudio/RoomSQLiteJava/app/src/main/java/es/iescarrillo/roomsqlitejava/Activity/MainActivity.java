package es.iescarrillo.roomsqlitejava.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.iescarrillo.roomsqlitejava.R;

/**
 * Actividad principal que muestra un menú de navegación
 * hacia las distintas funcionalidades de la aplicación.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botones para gestionar usuarios
        Button btnUsuarios = findViewById(R.id.btnUsuarios);
        Button btnListaUsuarios = findViewById(R.id.btnListaUsuarios);

        // Botones para gestionar categorías
        Button btnCategorias = findViewById(R.id.btnCategorias);
        Button btnListaCategorias = findViewById(R.id.btnListaCategorias);

        // Botones para gestionar tareas
        Button btnTareas = findViewById(R.id.btnTareas);
        Button btnListaTareas = findViewById(R.id.btnListaTareas);

        // Configuración de listeners
        btnUsuarios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UsuarioActivity.class);
            startActivity(intent);
        });

        btnCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriaActivity.class);
            startActivity(intent);
        });

        btnTareas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
            startActivity(intent);
        });

        btnListaUsuarios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaUsuariosActivity.class);
            startActivity(intent);
        });

        btnListaCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaCategoriasActivity.class);
            startActivity(intent);
        });

        btnListaTareas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaTareasActivity.class);
            startActivity(intent);
        });
    }
}