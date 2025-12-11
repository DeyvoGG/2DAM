package com.example.ishoppinglist.code.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishoppinglist.code.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private AppDatabaseHelper dbHelper;

    public CategoryDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public long insert(Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        values.put("description", category.getDescription());
        values.put("color_hex", category.getColorHex());
        long id = db.insert("category", null, values);
        db.close();
        return id;
    }

    public Category getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("category", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        Category category = null;
        if (cursor != null && cursor.moveToFirst()) {
            category = new Category(
                    cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("color_hex"))
            );
            cursor.close();
        }
        db.close();
        return category;
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("category", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Category category = new Category(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getString(cursor.getColumnIndexOrThrow("color_hex"))
                );
                categories.add(category);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return categories;
    }

    public int update(Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        values.put("description", category.getDescription());
        values.put("color_hex", category.getColorHex());
        int rows = db.update("category", values, "id = ?", new String[]{String.valueOf(category.getId())});
        db.close();
        return rows;
    }

    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("category", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}