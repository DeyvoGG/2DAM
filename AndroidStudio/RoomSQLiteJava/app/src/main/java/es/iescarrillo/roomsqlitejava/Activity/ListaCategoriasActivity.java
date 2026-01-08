package es.iescarrillo.roomsqlitejava.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iescarrillo.roomsqlitejava.DataBase.AppDatabase;
import es.iescarrillo.roomsqlitejava.Modelos.Categoria;
import es.iescarrillo.roomsqlitejava.Adapters.CategoriaAdapter;
import es.iescarrillo.roomsqlitejava.DataBase.DatabaseClient;
import es.iescarrillo.roomsqlitejava.R;

/**
 * Actividad que muestra una lista de todas las categorías almacenadas en la base de datos.
 */
public class ListaCategoriasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoriaAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        recyclerView = findViewById(R.id.recyclerViewCategorias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        // Inicializar adaptador con una lista vacía
        adapter = new CategoriaAdapter(new java.util.ArrayList<>());
        recyclerView.setAdapter(adapter);

        cargarCategorias();
    }

    private void cargarCategorias() {
        new Thread(() -> {
            try {
                List<Categoria> lista = db.daoCategoria().obtenerTodasLasCategorias();

                runOnUiThread(() -> {
                    if (lista.isEmpty()) {
                        Toast.makeText(this, "No hay categorías registradas", Toast.LENGTH_SHORT).show();
                    }
                    adapter.actualizarLista(lista);
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error al cargar las categorías", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}