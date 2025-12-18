package es.iescarrillo.pruebaroom.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public long userId;

    public long userOwnerId;
    public String nombre;

    public User(String nombre, long userId, long userOwnerId) {
        this.nombre = nombre;
        this.userId = userId;
        this.userOwnerId = userOwnerId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(long userOwnerId) {
        this.userOwnerId = userOwnerId;
    }
}
