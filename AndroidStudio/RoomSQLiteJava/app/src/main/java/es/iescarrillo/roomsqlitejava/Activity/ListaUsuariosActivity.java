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
import es.iescarrillo.roomsqlitejava.Modelos.Usuario;
import es.iescarrillo.roomsqlitejava.Adapters.UsuarioAdapter;

/**
 * Actividad que muestra una lista de todos los usuarios almacenados en la base de datos.
 */
public class ListaUsuariosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuarioAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        recyclerView = findViewById(R.id.recyclerViewUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        // Inicializar adaptador con una lista vacía
        adapter = new UsuarioAdapter(new java.util.ArrayList<>());
        recyclerView.setAdapter(adapter);

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        new Thread(() -> {
            try {
                List<Usuario> lista = db.daoUsuario().obtenerTodosLosUsuarios();

                runOnUiThread(() -> {
                    if (lista.isEmpty()) {
                        Toast.makeText(this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
                    }
                    adapter.actualizarLista(lista);
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error al cargar los usuarios", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}