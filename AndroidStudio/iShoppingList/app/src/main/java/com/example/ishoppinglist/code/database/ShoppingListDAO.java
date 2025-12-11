package com.example.ishoppinglist.code.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishoppinglist.code.models.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDAO {

    private AppDatabaseHelper dbHelper;

    public ShoppingListDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public long insert(ShoppingList list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", list.getName());
        values.put("user_id", list.getUserId());
        values.put("created_at", list.getCreatedAt());
        long id = db.insert("shopping_list", null, values);
        db.close();
        return id;
    }

    public ShoppingList getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("shopping_list", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        ShoppingList list = null;
        if (cursor != null && cursor.moveToFirst()) {
            list = new ShoppingList(
                    cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getLong(cursor.getColumnIndexOrThrow("user_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
            );
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<ShoppingList> getAll() {
        List<ShoppingList> lists = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("shopping_list", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ShoppingList list = new ShoppingList(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("user_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
                );
                lists.add(list);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }

    public List<ShoppingList> getByUserId(long userId) {
        List<ShoppingList> lists = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("shopping_list", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ShoppingList list = new ShoppingList(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("user_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("created_at"))
                );
                lists.add(list);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }

    public int update(ShoppingList list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", list.getName());
        values.put("user_id", list.getUserId());
        values.put("created_at", list.getCreatedAt());
        int rows = db.update("shopping_list", values, "id = ?", new String[]{String.valueOf(list.getId())});
        db.close();
        return rows;
    }

    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("shopping_list", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}