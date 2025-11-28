package es.iescarrillo.sqlite_ejercicioclase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "usuariosPrueba.db";
    public static final int DB_VERSION = 2;

    public static final String TABLE_USUARIOS =
            "CREATE TABLE usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "correo TEXT)";
    public static final String TABLE_TAREA =
            "CREATE TABLE tarea (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario_id INTEGER NOT NULL," +
                    "titulo TEXT NOT NULL," +  // ⬅️ Aquí faltaba una coma
                    "FOREIGN KEY(usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE)";  // ⬅️ Corregido: FOREIGN y "usuarios"

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_USUARIOS);
        db.execSQL(TABLE_TAREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS tarea");
        onCreate(db);
    }
}
