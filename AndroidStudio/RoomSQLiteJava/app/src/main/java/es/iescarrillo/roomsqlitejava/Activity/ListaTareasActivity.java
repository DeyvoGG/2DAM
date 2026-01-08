package es.iescarrillo.roomsqlitejava.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iescarrillo.roomsqlitejava.DataBase.AppDatabase;
import es.iescarrillo.roomsqlitejava.DataBase.DatabaseClient;
import es.iescarrillo.roomsqlitejava.R;
import es.iescarrillo.roomsqlitejava.Adapters.TareaAdapter;
import es.iescarrillo.roomsqlitejava.Modelos.TareaConRelaciones;

/**
 * Actividad que muestra una lista de tareas con sus relaciones:
 * nombre del usuario asignado y nombre de la categoría.
 */
public class ListaTareasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TareaAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        recyclerView = findViewById(R.id.recyclerViewTareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        // Inicializar adaptador con lista vacía
        adapter = new TareaAdapter(new java.util.ArrayList<>());
        recyclerView.setAdapter(adapter);

        cargarTareasConRelaciones();
    }

    private void cargarTareasConRelaciones() {
        new Thread(() -> {
            try {
                List<TareaConRelaciones> lista = db.daoTarea().obtenerTareasConRelaciones();

                runOnUiThread(() -> {
                    if (lista.isEmpty()) {
                        Toast.makeText(this, "No hay tareas registradas", Toast.LENGTH_SHORT).show();
                    }
                    adapter.actualizarLista(lista);
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error al cargar las tareas", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}