package es.iescarrillo.peliculas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetallePeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        // TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Botón back
            getSupportActionBar().setTitle("Detalle de Película");
        }

        ImageView imgPelicula = findViewById(R.id.imagenPelicula);
        TextView tvTitulo = findViewById(R.id.tituloPelicula);
        TextView tvDirector = findViewById(R.id.directorPelicula);
        TextView tvSinopsis = findViewById(R.id.sinopsisPelicula);

        // RECIBIR DATOS DEL INTENT
        String titulo = getIntent().getStringExtra("name");
        String director = getIntent().getStringExtra("director");
        String sinopsis = getIntent().getStringExtra("sinopsis");
        int imagen = getIntent().getIntExtra("image", 0);

        tvTitulo.setText(titulo);
        tvDirector.setText("Director: " + director);
        tvSinopsis.setText(sinopsis);
        Glide.with(this).load(imagen).into(imgPelicula);
    }

    // BOTÓN DE BACK DE LA TOOLBAR
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
