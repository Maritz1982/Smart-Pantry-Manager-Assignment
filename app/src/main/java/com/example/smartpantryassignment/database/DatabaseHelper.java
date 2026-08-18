package com.example.smartpantryassignment.database;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smartpantry.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PANTRY_TABLE =
                "CREATE TABLE PantryItems (" +
                        "pantryId INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "ingredient TEXT," +
                        "quantity REAL," +
                        "unitMeasure TEXT," +
                        "expiryDate TEXT" +
                        ")";
    db.execSQL(CREATE_PANTRY_TABLE);
    } //creates table in the SQL database
    public void addIngredient(PantryItem item) {
        SQLiteDatabase db = this.getWritetableDatabase();
        ContentValues values = new ContentValues();
        values.put("ingredient", item.getIngredient());
        values.put("quantity", item.getQuantity());
        values.put("unitMeasure", item.getunitMeasure());
        values.put("expireDate", item.getexpireDate());

        db.insert("PantryItems", null, values);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}

//database helper manages the SQLite database where it will create tables and CRUD methods