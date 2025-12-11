package com.example.ishoppinglist.code.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishoppinglist.code.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private AppDatabaseHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public long insert(Product item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("description", item.getDescription());
        values.put("is_completed", item.isCompleted() ? 1 : 0);
        values.put("shopping_list_id", item.getShoppingListId());
        values.put("category_id", item.getCategoryId());
        long id = db.insert("product_item", null, values);
        db.close();
        return id;
    }

    public Product getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("product_item", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        Product item = null;
        if (cursor != null && cursor.moveToFirst()) {
            item = new Product(
                    cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("is_completed")) == 1,
                    cursor.getLong(cursor.getColumnIndexOrThrow("shopping_list_id")),
                    cursor.getLong(cursor.getColumnIndexOrThrow("category_id"))
            );
            cursor.close();
        }
        db.close();
        return item;
    }

    public List<Product> getAll() {
        List<Product> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("product_item", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product item = new Product(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("is_completed")) == 1,
                        cursor.getLong(cursor.getColumnIndexOrThrow("shopping_list_id")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("category_id"))
                );
                items.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return items;
    }

    public List<Product> getByShoppingListId(long shoppingListId) {
        List<Product> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("product_item", null, "shopping_list_id = ?", new String[]{String.valueOf(shoppingListId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product item = new Product(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("is_completed")) == 1,
                        cursor.getLong(cursor.getColumnIndexOrThrow("shopping_list_id")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("category_id"))
                );
                items.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return items;
    }

    public List<Product> getCompletedByShoppingListId(long shoppingListId) {
        return getFilteredByShoppingListAndCompletion(shoppingListId, true);
    }

    public List<Product> getPendingByShoppingListId(long shoppingListId) {
        return getFilteredByShoppingListAndCompletion(shoppingListId, false);
    }

    private List<Product> getFilteredByShoppingListAndCompletion(long shoppingListId, boolean isCompleted) {
        List<Product> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "shopping_list_id = ? AND is_completed = ?";
        String[] selectionArgs = {String.valueOf(shoppingListId), isCompleted ? "1" : "0"};
        Cursor cursor = db.query("product_item", null, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product item = new Product(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        isCompleted,
                        cursor.getLong(cursor.getColumnIndexOrThrow("shopping_list_id")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("category_id"))
                );
                items.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return items;
    }

    public int update(Product item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("description", item.getDescription());
        values.put("is_completed", item.isCompleted() ? 1 : 0);
        values.put("shopping_list_id", item.getShoppingListId());
        values.put("category_id", item.getCategoryId());
        int rows = db.update("product_item", values, "id = ?", new String[]{String.valueOf(item.getId())});
        db.close();
        return rows;
    }

    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("product_item", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}