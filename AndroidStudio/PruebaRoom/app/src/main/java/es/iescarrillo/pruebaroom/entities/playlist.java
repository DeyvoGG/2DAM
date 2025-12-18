package es.iescarrillo.pruebaroom.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "playList")
public class playlist {
    @PrimaryKey
    public long playlistId;
    public long userCreatorId;
    @ColumnInfo(name = "playlistName")
    public String playlistName;
}
