package es.iescarrillo.diaandalucia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button boton=findViewById(R.id.sonarhimno_btn);
        boton.setOnClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });
        mp = MediaPlayer.create(this, R.raw.himno_de_andalucia);

    }

    @Override
    public void onClick(View v) {
    mp.start();
Log.i ("Estado", "Actividad Iniciada");
    }
    protected void onStart(){
        super.onStart();
        Log.i( "Estado",  "Actividad iniciada");
    }
    protected void onDestroy(Bundle savedInstanceState){
        mp.stop();
        Log.i( "Estado",  "Actividad Destruida");
    }
    protected void onPause(){
        super.onPause();
        mp.stop();
        Log.i("Estado", "Actividad Pausada");
    }
    protected void onResume(){
        super.onResume();
        Log.i("Estado", "Actividad Ejecutada");

    }
}