package es.iescarrillo.peliculas;

public class Pelicula {

    private String name;
    private String descripcion;
    private int image;
    private float valoracion;

    private String director;   // NUEVO
    private String sinopsis;   // NUEVO

    // Constructor completo
    public Pelicula(String name, String descripcion, int image, float valoracion,
                    String director, String sinopsis) {

        this.name = name;
        this.descripcion = descripcion;
        this.image = image;
        this.valoracion = valoracion;
        this.director = director;
        this.sinopsis = sinopsis;
    }

    //Constructor antiguo
    public Pelicula(String name, String descripcion, int image, float valoracion) {
        this(name, descripcion, image, valoracion,
                "Desconocido", "Sin sinopsis disponible");
    }

    public float getValoracion() {
        return valoracion;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDirector() {
        return director;
    }

    public String getSinopsis() {
        return sinopsis;
    }
}
