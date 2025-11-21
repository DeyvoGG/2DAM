package es.iescarrillo.listview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista = findViewById(R.id.lista);

        // Al hacer clic: cambia el color a azul
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView texto = (TextView) view;
                texto.setBackgroundColor(Color.parseColor("#ADD8E6")); // Azul claro
            }
        });

        // Al mantener pulsado: vuelve a blanco
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView texto = (TextView) view;
                texto.setBackgroundColor(Color.WHITE);
                return true; // Evita que también dispare el click normal
            }
        });
    }
}
