package com.example.ishoppinglist.code.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishoppinglist.code.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private AppDatabaseHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public long insert(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("created_at", user.getCreatedAt());
        long id = db.insert("user", null, values);
        db.close();
        return id;
    }

    public User getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("user", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
            );
            cursor.close();
        }
        db.close();
        return user;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                User user = new User(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
                );
                users.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return users;
    }

    public int update(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("created_at", user.getCreatedAt());
        int rows = db.update("user", values, "id = ?", new String[]{String.valueOf(user.getId())});
        db.close();
        return rows;
    }

    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("user", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}