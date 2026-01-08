package es.iescarrillo.roomsqlitejava.Modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad que representa una categoría de tareas.
 * Cada categoría tiene un identificador único, nombre y descripción.
 */
@Entity(tableName = "categorias")
public class Categoria {

    @PrimaryKey(autoGenerate = true)
    public int idCategoria;

    public String nombre;
    public String descripcion;

    /**
     * Constructor completo para inicializar una categoría.
     *
     * @param nombre      Nombre de la categoría (ej. "Trabajo").
     * @param descripcion Descripción opcional de la categoría.
     */
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}