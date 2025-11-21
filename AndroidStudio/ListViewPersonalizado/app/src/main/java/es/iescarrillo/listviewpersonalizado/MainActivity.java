package es.iescarrillo.listviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        // Implementamos las interfaces de eventos
        implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        AbsListView.OnScrollListener,
        AdapterView.OnItemSelectedListener {

    ListView listView;
    ArrayList<String> listaNombres;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1️⃣ Referencia al ListView del layout
        listView = findViewById(R.id.listView);

        // 2️⃣ Crear el ArrayList con datos
        listaNombres = new ArrayList<>();
        listaNombres.add("Juan");
        listaNombres.add("María");
        listaNombres.add("Pedro");
        listaNombres.add("Lucía");

        // 3️⃣ Crear el ArrayAdapter (contexto, layout de cada ítem, lista de datos)
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNombres);

        // 4️⃣ Asignar el adapter al ListView
        listView.setAdapter(adapter);

        // 5️⃣ Asignar los listeners (eventos) al ListView
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnScrollListener(this);
        listView.setOnItemSelectedListener(this);
    }

    //  EVENTO 1: Click corto en un elemento
    // Se ejecuta cuando el usuario pulsa un ítem de la lista una sola vez.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Has hecho click en: " + nombre, Toast.LENGTH_SHORT).show();
    }

    // 🔹 EVENTO 2: Click largo en un elemento
    // Se ejecuta cuando el usuario mantiene pulsado un ítem durante un tiempo.
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Click largo sobre: " + nombre, Toast.LENGTH_SHORT).show();
        return true; // true = el evento se consume (no se lanza ningún otro)
    }

    //  EVENTO 3: Estado del scroll (al mover la lista)
    // Detecta cuándo el usuario está desplazando la lista o la lista está quieta.
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                Toast.makeText(this, "Lista detenida", Toast.LENGTH_SHORT).show();
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Toast.makeText(this, "Desplazando con el dedo", Toast.LENGTH_SHORT).show();
                break;
            case SCROLL_STATE_FLING:
                Toast.makeText(this, "Desplazamiento rápido (fling)", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // Método obligatorio del OnScrollListener, pero no lo usamos en este ejemplo.
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // Este método se ejecuta continuamente mientras haces scroll.
    }

    //  EVENTO 4: Elemento seleccionado (por ejemplo, con teclado o control remoto)
    // Se ejecuta cuando un elemento de la lista obtiene el foco o es seleccionado.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Seleccionado: " + nombre, Toast.LENGTH_SHORT).show();
    }

    // Se ejecuta cuando no hay ningún elemento seleccionado.
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Ningún elemento seleccionado", Toast.LENGTH_SHORT).show();
    }
}
