package es.iescarrillo.roomsqlitejava.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iescarrillo.roomsqlitejava.Modelos.Categoria;

/**
 * DAO para gestionar operaciones CRUD sobre la tabla 'categorias'.
 */
@Dao
public interface DaoCategoria {

    @Insert
    void insertarCategoria(Categoria categoria);

    @Update
    void actualizarCategoria(Categoria categoria);

    @Delete
    void borrarCategoria(Categoria categoria);

    @Query("SELECT * FROM categorias")
    List<Categoria> obtenerTodasLasCategorias();

    @Query("SELECT * FROM categorias WHERE idCategoria = :id")
    Categoria obtenerCategoriaPorId(int id);
}