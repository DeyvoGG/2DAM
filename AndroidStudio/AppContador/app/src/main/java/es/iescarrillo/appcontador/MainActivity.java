package es.iescarrillo.appcontador;

import static android.view.View.VISIBLE;

import static es.iescarrillo.appcontador.R.id.muestraContador;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
public int contador;
public int numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        contador = 0;


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void incrementarContador(View vista){
        contador ++;
        mostrarResultado();
    }
    public void decrementarContador(View vista){
        contador --;
        TextView  muestraContador = findViewById(R.id.muestraContador);
        muestraContador.setText(""+contador);
        muestraContador.setVisibility(VISIBLE);
        if(contador<0){
            CheckBox negativos =  findViewById(R.id.checkBox);
            if(!negativos.isChecked()){
                contador=0;
                muestraContador.setText(""+contador);
                muestraContador.setVisibility(VISIBLE);
            }
        }

    }
    public void resetear(View vista) {
        EditText numeroInput = findViewById(R.id.numero);
        String textoNumero = numeroInput.getText().toString();

        if (!textoNumero.isEmpty()) {
            try {
                contador = Integer.parseInt(textoNumero);
            } catch (NumberFormatException e) {
                contador = 0; // En caso de que el texto no sea un número válido
            }
        } else {
            contador = 0;
        }

        mostrarResultado();
    }
    public void mostrarResultado(){
        TextView  muestraContador = findViewById(R.id.muestraContador);
        muestraContador.setText(""+contador);
        muestraContador.setVisibility(VISIBLE);
    }
}