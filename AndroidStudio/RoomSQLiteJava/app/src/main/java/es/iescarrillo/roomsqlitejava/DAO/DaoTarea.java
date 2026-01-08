package es.iescarrillo.roomsqlitejava.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iescarrillo.roomsqlitejava.Modelos.Tarea;
import es.iescarrillo.roomsqlitejava.Modelos.TareaConRelaciones;

/**
 * DAO para gestionar operaciones CRUD sobre la tabla 'tareas',
 * incluyendo consultas con JOIN para mostrar relaciones.
 */
@Dao
public interface DaoTarea {

    @Insert
    void insertarTarea(Tarea tarea);

    @Update
    void actualizarTarea(Tarea tarea);

    @Delete
    void borrarTarea(Tarea tarea);

    /**
     * Obtiene todas las tareas junto con el nombre del usuario y de la categoría asociados.
     * Usa JOIN entre las tablas tareas, usuarios y categorias.
     */
    @Query("SELECT " +
            "t.idTarea, t.titulo, t.descripcion, t.fecha, t.completada, " +
            "t.idCategoria, t.idUsuario, " +
            "c.nombre AS nombreCategoria, " +
            "u.nombre AS nombreUsuario " +
            "FROM tareas t " +
            "JOIN categorias c ON t.idCategoria = c.idCategoria " +
            "JOIN usuarios u ON t.idUsuario = u.idUsuario")
    List<TareaConRelaciones> obtenerTareasConRelaciones();

    /**
     * Obtiene todas las tareas sin relaciones (solo campos de la tabla tareas).
     */
    @Query("SELECT * FROM tareas")
    List<Tarea> obtenerTodasLasTareas();

    @Query("SELECT * FROM tareas WHERE idTarea = :id")
    Tarea obtenerTareaPorId(int id);
}