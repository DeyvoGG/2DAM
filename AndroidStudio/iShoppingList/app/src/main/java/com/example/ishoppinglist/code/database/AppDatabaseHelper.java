package com.example.ishoppinglist.code.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "shopping_list.db";
    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método llamado al crear la base de datos por primera vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla User
        String CREATE_USER_TABLE = "CREATE TABLE user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "created_at TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_USER_TABLE);

        // Crear tabla ShoppingList
        String CREATE_SHOPPING_LIST_TABLE = "CREATE TABLE shopping_list (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "user_id INTEGER NOT NULL," +
                "created_at TEXT NOT NULL," +
                "FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE" +
                ");";
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);

        // Crear tabla Category
        String CREATE_CATEGORY_TABLE = "CREATE TABLE category (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL UNIQUE," +
                "description TEXT," +
                "color_hex TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_CATEGORY_TABLE);

        // Crear tabla ProductItem
        String CREATE_PRODUCT_ITEM_TABLE = "CREATE TABLE product_item (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "is_completed INTEGER NOT NULL DEFAULT 0," + // 0 = false, 1 = true
                "shopping_list_id INTEGER NOT NULL," +
                "category_id INTEGER NOT NULL," +
                "FOREIGN KEY(shopping_list_id) REFERENCES shopping_list(id) ON DELETE CASCADE," +
                "FOREIGN KEY(category_id) REFERENCES category(id) ON DELETE CASCADE" +
                ");";
        db.execSQL(CREATE_PRODUCT_ITEM_TABLE);

        //Insertar datos iniciales
        insertInitialData(db);
    }

    // Método para futuras actualizaciones de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de cambios futuros, aquí se haría la migración
        db.execSQL("DROP TABLE IF EXISTS product_item");
        db.execSQL("DROP TABLE IF EXISTS shopping_list");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    // Método para insertar datos iniciales
    private void insertInitialData(SQLiteDatabase db) {
        // Insertar un usuario por defecto
        db.execSQL("INSERT INTO user (name, email, created_at) VALUES ('Usuario Predeterminado', 'default@example.com', datetime('now'));");

        // Insertar categorías
        db.execSQL("INSERT INTO category (name, description, color_hex) VALUES ('Comida Rápida', 'Hamburguesas, tacos, etc.', '#FF5733');");
        db.execSQL("INSERT INTO category (name, description, color_hex) VALUES ('Platos Gourmet', 'Sushi, paella, etc.', '#33FF57');");
        db.execSQL("INSERT INTO category (name, description, color_hex) VALUES ('Postres', 'Dulces y snacks', '#3357FF');");

        // Obtener el user_id insertado (será 1)
        // Obtener category_id de "Comida Rápida" (será 1), etc.

        // Insertar una lista de compra
        db.execSQL("INSERT INTO shopping_list (name, user_id, created_at) VALUES ('Lista Principal', 1, datetime('now'));");

        // Insertar productos
        db.execSQL("INSERT INTO product_item (name, description, is_completed, shopping_list_id, category_id) VALUES " +
                "('Hamburguesa BBQ', 'Bien jugosa y con BBQ', 1, 1, 1), " +
                "('Alitas Picantes', 'Para los amantes del picante', 0, 1, 1), " +
                "('Sushi de Salmón', 'Para los fans del sushi', 1, 1, 2), " +
                "('Crepe Dulce', 'Con Nutella y fresas', 1, 1, 3);");
    }
}