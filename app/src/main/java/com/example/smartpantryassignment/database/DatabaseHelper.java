package com.example.smartpantryassignment.database;
import com.example.smartpantryassignment.models.PantryItem;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;
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
                        "expireDate TEXT" +
                        ")";
    db.execSQL(CREATE_PANTRY_TABLE);
    } //creates table in the SQL database
    public void deleteIngredient(int pantryId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                "PantryItems",
                "pantryId=?",
                new String[]{String.valueOf(pantryId)}
        );
        db.close();
    }
    public void updateIngredient(PantryItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ingredient", item.getIngredient());
        values.put("quantity", item.getQuantity());
        values.put("unitMeasure", item.getUnitMeasure());
        values.put("expiryDate", item.getExpireDate());
        db.update(
                "PantryItems",
                values,
                "pantryId=?",
                new String[]{String.valueOf(item.getPantryId())}
        );
        db.close();
    }
    public void addIngredient(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ingredient", item.getIngredient());
        values.put("quantity", item.getQuantity());
        values.put("unitMeasure", item.getUnitMeasure());
        values.put("expireDate", item.getExpireDate());

        db.insert("PantryItems", null, values);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
    public ArrayList<PantryItem> getAllIngredients() { //add method and will show all the ingredients
        ArrayList<PantryItem> pantryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); //opens the database to allow reading data
        Cursor cursor = db.rawQuery(
                "SELECT * FROM PantryItems", //runs the query for all ingredients in the table
                null);
        if (cursor.moveToFirst()) {
            do {
                PantryItem item = new PantryItem(); //creates pantryitem objects then fill it with data from the db
                item.setPantryId(
                        cursor.getInt(0));
                item.setIngredient(
                        cursor.getString(1));
                item.setQuantity(
                        cursor.getDouble(2));
                item.setUnitMeasure(
                        cursor.getString(3));
                item.setExpireDate(
                        cursor.getString(4));
                pantryList.add(item); //adds to the list
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return pantryList;

    }
}





//database helper manages the SQLite database where it will create tables and CRUD methods