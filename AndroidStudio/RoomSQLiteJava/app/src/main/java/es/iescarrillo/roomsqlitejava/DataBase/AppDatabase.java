package es.iescarrillo.roomsqlitejava.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.iescarrillo.roomsqlitejava.DAO.DaoCategoria;
import es.iescarrillo.roomsqlitejava.DAO.DaoTarea;
import es.iescarrillo.roomsqlitejava.DAO.DaoUsuario;
import es.iescarrillo.roomsqlitejava.Modelos.Categoria;
import es.iescarrillo.roomsqlitejava.Modelos.Tarea;
import es.iescarrillo.roomsqlitejava.Modelos.Usuario;

/**
 * Clase abstracta que define la base de datos Room.
 * Incluye las tres entidades: Usuario, Categoria y Tarea.
 * Versión inicial de la base de datos (version = 1).
 */
@Database(
        entities = {Usuario.class, Categoria.class, Tarea.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Devuelve el DAO para operaciones sobre la tabla 'usuarios'.
     */
    public abstract DaoUsuario daoUsuario();

    /**
     * Devuelve el DAO para operaciones sobre la tabla 'categorias'.
     */
    public abstract DaoCategoria daoCategoria();

    /**
     * Devuelve el DAO para operaciones sobre la tabla 'tareas',
     * incluyendo consultas con relaciones mediante JOIN.
     */
    public abstract DaoTarea daoTarea();
}