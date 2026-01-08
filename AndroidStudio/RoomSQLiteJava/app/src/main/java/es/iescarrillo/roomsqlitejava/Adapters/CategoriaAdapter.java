package es.iescarrillo.roomsqlitejava.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iescarrillo.roomsqlitejava.Modelos.Categoria;
import es.iescarrillo.roomsqlitejava.R;

/**
 * Adaptador para mostrar una lista de categorías en un RecyclerView.
 */
public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<Categoria> listaCategorias;

    public CategoriaAdapter(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = listaCategorias.get(position);
        holder.tvNombre.setText(categoria.nombre);
        holder.tvDescripcion.setText(categoria.descripcion != null ? categoria.descripcion : "");
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public void actualizarLista(List<Categoria> nuevaLista) {
        this.listaCategorias = nuevaLista;
        notifyDataSetChanged();
    }

    static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreCategoria);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionCategoria);
        }
    }
}
