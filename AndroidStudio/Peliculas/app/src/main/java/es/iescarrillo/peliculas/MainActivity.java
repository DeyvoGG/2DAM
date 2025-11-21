package es.iescarrillo.peliculas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeliculaAdapter adapter;
    private List<Pelicula> peliculaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Cnfigurar el toolbar como action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerProducts);

        peliculaList = new ArrayList<>();
        peliculaList.add(new Pelicula("El Joker","Drama,Suspense",R.drawable.joker1,4.5f));
        peliculaList.add(new Pelicula("El Joker 2","Drama,Suspense",R.drawable.joker,2.5f));
        peliculaList.add(new Pelicula("Niagra","Baile,Fantasia",R.drawable.niagra,3.5f,"Stalone","Durante su luna de miel en las Cataratas del Niágara, Ray y su mujer Polly conocen a Rose y George, una pareja que vive en Canadá. Rose está cansada de los celos de su marido y planea deshacerse de él con la ayuda de su amante, Patrick. Su plan es empujarlo a las cataratas, para que el asesinato parezca en realidad un accidente."));
        peliculaList.add(new Pelicula("Minioms","Animacion,Comedia",R.drawable.minioms,3.5f,"Miguelito","Los minions, ingenuos y torpes ayudantes de villano, llevan buscando, desde el principio de los tiempos, un verdadero malhechor al que servir. A lo largo de una evolución de millones de años, los minions se han puesto al servicio de los amos más despreciables. Kevin, acompañado por el rebelde Stuart y el adorable Bob, emprende un emocionante viaje para conseguir una jefa a quien servir, la terrible Scarlet Overkill."));
        peliculaList.add(new Pelicula("The Killer","Drama,C.Ficción",R.drawable.thekiller,3.9f,"Manuelito","Un asesino que asesina a gente importante. "));
        peliculaList.add(new Pelicula("Alguien volo sobre el nide del Cuco","Comedia,Fantasia",R.drawable.alguienvol,4f,"Manuleto","Un hombre que vuela sobre el nido de un cuco"));
        peliculaList.add(new Pelicula("Frankenstein","Drama,Fantasia",R.drawable.frankenstein,4.5f,"Gillermo del toro","se centra en el brillante pero egocéntrico científico Victor Frankenstein (Oscar Isaac), quien da vida a una criatura ensamblada con partes de cadáveres (Jacob Elordi). Horrorizado por su creación, la abandona, lo que lleva a la criatura a una existencia solitaria y de rechazo. La trama explora la tragedia de ambos, la búsqueda de la criatura por ser aceptada y la venganza contra su creador. "));
        peliculaList.add(new Pelicula("Frankenstein","Drama,Fantasia",R.drawable.frankenstein,4.5f,"Gillermo del toro","se centra en el brillante pero egocéntrico científico Victor Frankenstein (Oscar Isaac), quien da vida a una criatura ensamblada con partes de cadáveres (Jacob Elordi). Horrorizado por su creación, la abandona, lo que lleva a la criatura a una existencia solitaria y de rechazo. La trama explora la tragedia de ambos, la búsqueda de la criatura por ser aceptada y la venganza contra su creador. "));
        peliculaList.add(new Pelicula("Frankenstein","Drama,Fantasia",R.drawable.frankenstein,4.5f,"Gillermo del toro","se centra en el brillante pero egocéntrico científico Victor Frankenstein (Oscar Isaac), quien da vida a una criatura ensamblada con partes de cadáveres (Jacob Elordi). Horrorizado por su creación, la abandona, lo que lleva a la criatura a una existencia solitaria y de rechazo. La trama explora la tragedia de ambos, la búsqueda de la criatura por ser aceptada y la venganza contra su creador. "));
        peliculaList.add(new Pelicula("Frankenstein","Drama,Fantasia",R.drawable.frankenstein,4.5f,"Gillermo del toro","se centra en el brillante pero egocéntrico científico Victor Frankenstein (Oscar Isaac), quien da vida a una criatura ensamblada con partes de cadáveres (Jacob Elordi). Horrorizado por su creación, la abandona, lo que lleva a la criatura a una existencia solitaria y de rechazo. La trama explora la tragedia de ambos, la búsqueda de la criatura por ser aceptada y la venganza contra su creador. "));

        adapter = new PeliculaAdapter(this, peliculaList);
        recyclerView.setAdapter(adapter);
        recyclerView.scheduleLayoutAnimation();
    }

    // Inflar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Cartelera de Cine");
        }
        return true;
    }

    //Accion de pulsar el boton de info
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            mostrarInformacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Muestra Información
    private void mostrarInformacion() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Información")
                .setMessage("Aplicación de Películas\nVersión 1.0\nCreado por: David Gallardo")
                .setPositiveButton("OK", null)
                .show();
    }
}
