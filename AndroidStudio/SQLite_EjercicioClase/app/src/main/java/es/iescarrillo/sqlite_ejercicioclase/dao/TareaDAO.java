package es.iescarrillo.sqlite_ejercicioclase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import es.iescarrillo.sqlite_ejercicioclase.database.DatabaseHelper;
import es.iescarrillo.sqlite_ejercicioclase.entidades.Tarea;
import es.iescarrillo.sqlite_ejercicioclase.entidades.Usuario;

public class TareaDAO {
    private DatabaseHelper dbHelper;
    public TareaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    // ---------- CREATE ----------
    public long insertar(Tarea tarea) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("ID", tarea.getId());
        valores.put("Titulo", tarea.getTitulo());
        valores.put("ID Usuario", tarea.getUsuarioId());
        return db.insert("tarea", null, valores);
    }
}
