package es.iescarrillo.roomsqlitejava.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.iescarrillo.roomsqlitejava.DataBase.AppDatabase;
import es.iescarrillo.roomsqlitejava.Modelos.Categoria;
import es.iescarrillo.roomsqlitejava.DataBase.DatabaseClient;
import es.iescarrillo.roomsqlitejava.R;
import es.iescarrillo.roomsqlitejava.Modelos.Tarea;
import es.iescarrillo.roomsqlitejava.Modelos.Usuario;

/**
 * Actividad para crear una nueva tarea, seleccionando un usuario y una categoría existentes.
 */
public class NuevaTareaActivity extends AppCompatActivity {

    private EditText etTitulo;
    private EditText etDescripcion;
    private EditText etFecha;
    private CheckBox cbCompletada;
    private Spinner spinnerUsuarios;
    private Spinner spinnerCategorias;
    private Button btnGuardar;

    private List<Usuario> listaUsuarios;
    private List<Categoria> listaCategorias;

    private int idUsuarioSeleccionado = -1;
    private int idCategoriaSeleccionada = -1;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        inicializarViews();
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        // Cargar datos para los spinners en segundo plano
        cargarDatosParaSpinners();
    }

    private void inicializarViews() {
        etTitulo = findViewById(R.id.etTituloTarea);
        etDescripcion = findViewById(R.id.etDescripcionTarea);
        etFecha = findViewById(R.id.etFechaTarea);
        cbCompletada = findViewById(R.id.cbCompletada);
        spinnerUsuarios = findViewById(R.id.spinnerUsuarios);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        btnGuardar = findViewById(R.id.btnGuardarTarea);

        btnGuardar.setOnClickListener(v -> guardarTarea());
    }

    private void cargarDatosParaSpinners() {
        new Thread(() -> {
            try {
                listaUsuarios = db.daoUsuario().obtenerTodosLosUsuarios();
                listaCategorias = db.daoCategoria().obtenerTodasLasCategorias();

                runOnUiThread(() -> {
                    configurarSpinnerUsuarios();
                    configurarSpinnerCategorias();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error al cargar datos para la tarea", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private void configurarSpinnerUsuarios() {
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            Toast.makeText(this, "No hay usuarios. Cree al menos uno.", Toast.LENGTH_LONG).show();
            return;
        }

        // Crear lista de nombres para mostrar en el spinner
        String[] nombresUsuarios = new String[listaUsuarios.size()];
        for (int i = 0; i < listaUsuarios.size(); i++) {
            nombresUsuarios[i] = listaUsuarios.get(i).nombre;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresUsuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuarios.setAdapter(adapter);

        spinnerUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idUsuarioSeleccionado = listaUsuarios.get(position).idUsuario;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                idUsuarioSeleccionado = -1;
            }
        });
    }

    private void configurarSpinnerCategorias() {
        if (listaCategorias == null || listaCategorias.isEmpty()) {
            Toast.makeText(this, "No hay categorías. Cree al menos una.", Toast.LENGTH_LONG).show();
            return;
        }

        String[] nombresCategorias = new String[listaCategorias.size()];
        for (int i = 0; i < listaCategorias.size(); i++) {
            nombresCategorias[i] = listaCategorias.get(i).nombre;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresCategorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapter);

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idCategoriaSeleccionada = listaCategorias.get(position).idCategoria;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                idCategoriaSeleccionada = -1;
            }
        });
    }

    private void guardarTarea() {
        String titulo = etTitulo.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();
        boolean completada = cbCompletada.isChecked();

        if (titulo.isEmpty()) {
            Toast.makeText(this, "El título es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fecha.isEmpty()) {
            Toast.makeText(this, "La fecha es obligatoria (formato: AAAA-MM-DD)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (idUsuarioSeleccionado == -1) {
            Toast.makeText(this, "Seleccione un usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        if (idCategoriaSeleccionada == -1) {
            Toast.makeText(this, "Seleccione una categoría", Toast.LENGTH_SHORT).show();
            return;
        }

        Tarea tarea = new Tarea(titulo, descripcion, fecha, completada, idCategoriaSeleccionada, idUsuarioSeleccionado);

        new Thread(() -> {
            db.daoTarea().insertarTarea(tarea);

            runOnUiThread(() -> {
                Toast.makeText(NuevaTareaActivity.this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}