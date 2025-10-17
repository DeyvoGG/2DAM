package es.iescarrillo.agenda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText fecha;
    Button limpiar;
    Button grabar;
    Button buscar;
    EditText multiLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        limpiar = findViewById(R.id.limpiar);
        fecha = findViewById(R.id.date);
        multiLinea = findViewById(R.id.multiLineMain);
        grabar = findViewById(R.id.)


    }
    public void limpiar(View v){
        fecha.setText("");
        multiLinea.setText("");
    }
    public void grabar(View v){

    }
}