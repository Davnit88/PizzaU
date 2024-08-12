package com.example.pizzau.accessproduct;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizzau.database.DataBaseHelper;

import java.util.ArrayList;
import com.example.pizzau.product.Product;
import java.util.List;

;

public class ProductAP {
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public ProductAP(Context context){dbHelper = new DataBaseHelper(context);}
    public void cerrar (){dbHelper.close();}
    public void abrir (){db= dbHelper.getWritableDatabase();}
    public Long insertarProduct(String nombre , Float price){
        ContentValues Values = new ContentValues();
        Values.put("nombre", nombre);
        Values.put("precio" , price);
        return db.insert("productos",null,Values);
    }
    public List<Product> obtnTodProductos(){
        List<Product>productos=new ArrayList<>();
        Cursor cursor= db.rawQuery("SELECT* FROM productos",null);
        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setId(String.valueOf(cursor.getInt(0)));
                product.setName(cursor.getString(1));
                product.setPrice(String.valueOf(cursor.getFloat(2)));
                productos.add(product);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return productos;
    }
    public void  actualizarProducto(int id ,String name , String price){
        ContentValues values = new ContentValues();
        values.put("nombre", name);
        values.put("precio", price);
        db.update("productos",values,"id=?",new String[]{String.valueOf(id)});

    }
    public void eliminarProducto(int id){
        db.delete("productos", "id=?",new String[]{String.valueOf(id)});
    }
}