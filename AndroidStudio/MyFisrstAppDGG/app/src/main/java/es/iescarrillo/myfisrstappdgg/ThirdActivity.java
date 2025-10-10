package es.iescarrillo.myfisrstappdgg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInicio;
    TextView campoTexto;
    TextView campoNumero;
    TextView campoNumeroDecimal;
    TextView boo;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        campoTexto = findViewById(R.id.DatosCadena);
        campoNumero = findViewById(R.id.DatosEnteros);
        campoNumeroDecimal = findViewById(R.id.DatosDecimales);
        boo = findViewById(R.id.BooleanS);
        btnInicio = findViewById(R.id.btnVolverInicio);
        btnInicio.setOnClickListener(this);

        Intent newIntent = getIntent();
        if (newIntent != null) {
            campoTexto.setText("Texto: " + newIntent.getStringExtra("campoTexto"));
            campoNumero.setText("Entero: " + newIntent.getStringExtra("campoNumero"));
            campoNumeroDecimal.setText("Decimal: " + newIntent.getStringExtra("campoNumeroDecimal"));
            boolean estadoSwitch = newIntent.getBooleanExtra("booleano", false);
            boo.setText("Switch activado: " + estadoSwitch);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnVolverInicio) {
            Intent viewNameIntent = new Intent(ThirdActivity.this, MainActivity.class);
            startActivity(viewNameIntent);
        }
    }
}
