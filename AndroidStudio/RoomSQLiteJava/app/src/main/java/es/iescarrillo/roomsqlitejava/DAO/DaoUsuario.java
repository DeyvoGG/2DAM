package es.iescarrillo.roomsqlitejava.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iescarrillo.roomsqlitejava.Modelos.Usuario;

/**
 * DAO para gestionar operaciones CRUD sobre la tabla 'usuarios'.
 */
@Dao
public interface DaoUsuario {

    @Insert
    void insertarUsuario(Usuario usuario);

    @Update
    void actualizarUsuario(Usuario usuario);

    @Delete
    void borrarUsuario(Usuario usuario);

    @Query("SELECT * FROM usuarios")
    List<Usuario> obtenerTodosLosUsuarios();

    @Query("SELECT * FROM usuarios WHERE idUsuario = :id")
    Usuario obtenerUsuarioPorId(int id);
}