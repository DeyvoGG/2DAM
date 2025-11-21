package es.iescarrillo.peliculas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private List<Pelicula> peliculaList;
    private Context context;

    public PeliculaAdapter(Context context, List<Pelicula> peliculaList) {
        this.peliculaList = peliculaList;
        this.context = context;
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPelicula;
        TextView tvName, tvDescription;
        RatingBar tvEstrellas;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPelicula = itemView.findViewById(R.id.imgPelicula);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvEstrellas = itemView.findViewById(R.id.tvEstrella);
        }
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula film = peliculaList.get(position);

        holder.tvName.setText(film.getName());
        holder.tvDescription.setText(film.getDescripcion());
        holder.tvEstrellas.setRating(film.getValoracion());

        Glide.with(context)
                .load(film.getImage())
                .into(holder.imgPelicula);

        // onclick que abre la activity detalle
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallePeliculaActivity.class);
            intent.putExtra("name", film.getName());
            intent.putExtra("descripcion", film.getDescripcion());
            intent.putExtra("valoracion", film.getValoracion());
            intent.putExtra("image", film.getImage());
            intent.putExtra("director", film.getDirector());
            intent.putExtra("sinopsis", film.getSinopsis());
            context.startActivity(intent);
        });
        if (holder.itemView.getAnimation() == null) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_fade_in);
            holder.itemView.startAnimation(animation);
        }
    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }
}
