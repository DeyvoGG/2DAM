package es.iescarrillo.sqlite_ejercicioclase.entidades;

public class Tarea {
    private int id;
    private int usuarioId;
    private String titulo;
    public Tarea(){}

    public Tarea(int id, String titulo, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}

