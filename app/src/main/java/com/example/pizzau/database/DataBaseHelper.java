package com.example.pizzau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "CatalogoBaseDatos";
    private static  final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_USERS = "CREATE TABLE productos ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nombre TEXT, "
            + "precio REAL, "
            + "quantity INTEGER)";


    public DataBaseHelper (Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS productos");
        onCreate(db);
    }
}