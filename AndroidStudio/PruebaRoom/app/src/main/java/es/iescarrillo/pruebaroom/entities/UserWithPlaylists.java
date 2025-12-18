package es.iescarrillo.pruebaroom.entities;

import android.provider.MediaStore;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPlaylists {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )
    public List<Playlist> playlists;
}
