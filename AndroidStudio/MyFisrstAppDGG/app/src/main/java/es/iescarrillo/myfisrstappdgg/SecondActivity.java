package es.iescarrillo.myfisrstappdgg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSend;
    EditText campoTexto;
    EditText campoNumero;
    EditText campoNumeroDecimal;
    Switch booleano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSend = findViewById(R.id.btnSendData);
        campoTexto = findViewById(R.id.editTextString);
        campoNumero = findViewById(R.id.editTextNumber);
        campoNumeroDecimal = findViewById(R.id.editTextNumberDecimal);
        booleano = findViewById(R.id.switch1);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.btnSendData).isPressed()) {
            Intent viewNameIntent = new Intent(this, ThirdActivity.class);
            viewNameIntent.putExtra("campoTexto", campoTexto.getText().toString());
            viewNameIntent.putExtra("campoNumero", campoNumero.getText().toString());
            viewNameIntent.putExtra("campoNumeroDecimal", campoNumeroDecimal.getText().toString());
            viewNameIntent.putExtra("booleano", booleano.isChecked());

            startActivity(viewNameIntent);
        }
    }
}