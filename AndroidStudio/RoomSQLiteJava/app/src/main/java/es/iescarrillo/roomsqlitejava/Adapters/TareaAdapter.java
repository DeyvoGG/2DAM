package es.iescarrillo.roomsqlitejava.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iescarrillo.roomsqlitejava.R;
import es.iescarrillo.roomsqlitejava.Modelos.TareaConRelaciones;

/**
 * Adaptador para mostrar una lista de tareas con relaciones (usuario y categoría)
 * usando la clase TareaConRelaciones.
 */
public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<TareaConRelaciones> listaTareas;

    public TareaAdapter(List<TareaConRelaciones> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        TareaConRelaciones tarea = listaTareas.get(position);
        holder.tvTitulo.setText(tarea.titulo);
        holder.tvDescripcion.setText(tarea.descripcion != null ? tarea.descripcion : "");

        String usuarioCategoria = "Usuario: " + tarea.nombreUsuario + " | Categoría: " + tarea.nombreCategoria;
        holder.tvUsuarioCategoria.setText(usuarioCategoria);

        String estado = tarea.completada ? "Completada" : "Pendiente";
        String fechaEstado = "Fecha: " + tarea.fecha + " | Estado: " + estado;
        holder.tvFechaCompletada.setText(fechaEstado);
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public void actualizarLista(List<TareaConRelaciones> nuevaLista) {
        this.listaTareas = nuevaLista;
        notifyDataSetChanged();
    }

    static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvDescripcion;
        TextView tvUsuarioCategoria;
        TextView tvFechaCompletada;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloTarea);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionTarea);
            tvUsuarioCategoria = itemView.findViewById(R.id.tvUsuarioCategoriaTarea);
            tvFechaCompletada = itemView.findViewById(R.id.tvFechaCompletadaTarea);
        }
    }
}