package es.iescarrillo.pruebaroom.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Library {
    @PrimaryKey
    public long libraryId;

    public long userOwnerId;
    public String nombre;

    public Library(long libraryId, String nombre, long userOwnerId) {
        this.libraryId = libraryId;
        this.nombre = nombre;
        this.userOwnerId = userOwnerId;
    }

}
