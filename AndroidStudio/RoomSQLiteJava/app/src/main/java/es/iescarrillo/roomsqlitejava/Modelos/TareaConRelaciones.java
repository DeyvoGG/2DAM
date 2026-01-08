package es.iescarrillo.roomsqlitejava.Modelos;

/**
 * Clase auxiliar para almacenar el resultado de una consulta JOIN
 * que incluye datos de Tarea, Usuario y Categoria.
 * No es una entidad Room, solo un contenedor de datos (POJO).
 */
public class TareaConRelaciones {

    public int idTarea;
    public String titulo;
    public String descripcion;
    public String fecha;
    public boolean completada;
    public int idCategoria;
    public int idUsuario;
    public String nombreCategoria;
    public String nombreUsuario;

    // Constructor vacío requerido por Room al mapear resultados
    public TareaConRelaciones() {}

    // Getters y setters (opcionales, pero recomendados si se usan en adaptadores)

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

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
