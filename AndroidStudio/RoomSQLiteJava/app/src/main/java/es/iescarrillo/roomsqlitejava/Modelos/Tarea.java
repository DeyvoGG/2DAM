package es.iescarrillo.roomsqlitejava.Modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad que representa una tarea.
 * Está asociada a un usuario y a una categoría mediante claves foráneas.
 */
@Entity(tableName = "tareas")
public class Tarea {

    @PrimaryKey(autoGenerate = true)
    public int idTarea;

    public String titulo;
    public String descripcion;
    public String fecha;          // Formato: "yyyy-MM-dd"
    public boolean completada;

    public int idCategoria;       // Clave foránea a Categoria
    public int idUsuario;         // Clave foránea a Usuario

    /**
     * Constructor completo para inicializar una tarea.
     *
     * @param titulo      Título de la tarea.
     * @param descripcion Descripción detallada de la tarea.
     * @param fecha       Fecha límite en formato "yyyy-MM-dd".
     * @param completada  Indica si la tarea está completada.
     * @param idCategoria ID de la categoría asociada.
     * @param idUsuario   ID del usuario propietario.
     */
    public Tarea(String titulo, String descripcion, String fecha, boolean completada, int idCategoria, int idUsuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.completada = completada;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    // Getters y setters

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}