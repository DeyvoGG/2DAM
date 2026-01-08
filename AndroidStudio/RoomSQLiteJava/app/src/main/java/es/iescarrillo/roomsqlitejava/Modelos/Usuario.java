package es.iescarrillo.roomsqlitejava.Modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad que representa un usuario en la base de datos.
 * Contiene un identificador único, nombre y correo electrónico.
 */
@Entity(tableName = "usuarios")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    public int idUsuario;

    public String nombre;
    public String email;

    /**
     * Constructor completo para inicializar un objeto Usuario.
     *
     * @param nombre El nombre del usuario.
     * @param email  El correo electrónico del usuario.
     */
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}