package es.iescarrillo.roomsqlitejava.DataBase;

import android.content.Context;

import androidx.room.Room;

/**
 * Clase utilidad que implementa el patrón Singleton para gestionar
 * la única instancia de la base de datos Room durante la ejecución de la aplicación.
 */
public class DatabaseClient {

    private static DatabaseClient instance;
    private AppDatabase appDatabase;

    /**
     * Constructor privado para evitar instanciación externa.
     *
     * @param context Contexto de la aplicación.
     */
    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "gestion_tareas_db"
        ).build();
    }

    /**
     * Devuelve la instancia única de DatabaseClient.
     *
     * @param context Contexto de la aplicación.
     * @return Instancia de DatabaseClient.
     */
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    /**
     * Devuelve la instancia de la base de datos Room.
     *
     * @return Objeto AppDatabase.
     */
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}