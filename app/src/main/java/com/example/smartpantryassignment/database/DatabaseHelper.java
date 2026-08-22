package com.example.smartpantryassignment.database;
import com.example.smartpantryassignment.models.PantryItem;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;
import com.example.smartpantryassignment.models.Recipe;
import com.example.smartpantryassignment.models.RecipeIngredient;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smartpantry.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PANTRY_TABLE =
                "CREATE TABLE PantryItems (" +
                        "pantryId INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "ingredient TEXT," +
                        "quantity REAL," +
                        "unitMeasure TEXT," +
                        "expireDate TEXT" +
                        ")";
        db.execSQL(CREATE_PANTRY_TABLE);
        //creates table in the SQL database

        String CREATE_RECIPES_TABLE = //creating db tables in SQLite
                "CREATE TABLE Recipes (" +
                        "recipeId INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "recipeName TEXT," +
                        "recipeProcess TEXT" +
                        ")";

        db.execSQL(CREATE_RECIPES_TABLE); //execute it

        String CREATE_RECIPE_INGREDIENTS_TABLE =
                "CREATE TABLE RecipeIngredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "recipeId INTEGER," +
                        "ingredient TEXT," +
                        "quantity REAL," +
                        "unitMeasure TEXT" +
                        ")";
        db.execSQL(CREATE_RECIPE_INGREDIENTS_TABLE);
    }

    public void deleteIngredient(int pantryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                "PantryItems",
                "pantryId=?",
                new String[]{String.valueOf(pantryId)}
        );
        db.close();
    }

    public void updateIngredient(PantryItem item) {
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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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

    public void addRecipe(String recipeName, //getting the addrecipe's
                          String recipeProcess) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recipeName", recipeName);
        values.put("recipeProcess", recipeProcess);
        db.insert("Recipes", null, values);
        db.close();
    }

    public void seedRecipes() {
        addRecipe(
                "Pancakes",
                "Mix all dry ingredients first. Then add the wet ingredients and mix thoroughly. Cook in high heat pan. Turn once little bubbles appear. Serve with choice of sugar or savoury toppings."
        );
        addRecipe("French Toast",
                "Mix eggs with salt and pepper, use a fork to mix for best results. Dip the bread in the egg mixture and fry. Serve with syrup or butter"
        );
        addRecipe("Omelette",
                "Mix eggs with milk, beat mix and add salt. Fry in pan until slightly stiffened then turn."
        );
        addRecipe("Toasted Cheese and Tomato",
                "Butter bread and turn upside down(butter should be on the outside). Put thinly sliced tomato, spice with favourite spices. Add cheese genorously. Add sweetchilly and mayonaise drops all over. Close the sandwich and bake for 3 mins on each side in the airfryer"
        );
        addRecipe(
                "Egg Fried Rice",
                "Cook rice with the egg mixture and cut vegatables."
        );
    }

    public void addRecipeIngredient(int recipeId,
                                    String ingredient,
                                    double quantity,
                                    String unitMeasure) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recipeId", recipeId);
        values.put("quantity", quantity);
        values.put("unitMeasure", unitMeasure);
        values.put("recipeId", recipeId);
        values.put("ingredient", ingredient);
        values.put("quantity", quantity);
        values.put("unitmeasure", unitMeasure);
        db.insert("RecipeIngredients", null, values);
        db.close();
    }

    public void seedRecipeIngredients() {
        // Pancake recipe ID 1
        addRecipeIngredient(1,
                "Eggs",
                2,
                "pieces");
        addRecipeIngredient(1,
                "Milk",
                250,
                "ml");
        addRecipeIngredient(1,
                "Flour",
                200,
                "g");
        //French toast recipe ID 2
        addRecipeIngredient(2,
                "Eggs",
                2,
                "pieces");
        addRecipeIngredient(2,
                "Milk",
                100,
                "ml");
        addRecipeIngredient(2,
                "Flour",
                25,
                "g");
        addRecipeIngredient(2,
                "Bread",
                2,
                "pieces");
        // Omelette Recipe DI 3
        addRecipeIngredient(3,
                "Eggs",
                3,
                "pieces");
        addRecipeIngredient(3,
                "Milk",
                50,
                "ml");
    }

    public ArrayList<Recipe> getAllRecipes() { //db retrieval methods
        ArrayList<Recipe> recipeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Recipes",
                null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(
                        cursor.getInt(0));
                recipe.setRecipeName(
                        cursor.getString(1));
                recipe.setRecipeProcess(
                        cursor.getString(2));
                recipeList.add(recipe);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return recipeList;
    }

    public ArrayList<RecipeIngredient>
    getIngredientsForRecipe(int recipeId) {
        ArrayList<RecipeIngredient> ingredientList =
                new ArrayList<>();
        SQLiteDatabase db =
                this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM RecipeIngredients WHERE recipeId=?",
                new String[]{String.valueOf(recipeId)}
        );
        if (cursor.moveToFirst()) {
            do {
                RecipeIngredient ingredient =
                        new RecipeIngredient();
                ingredient.setRecipeId(
                        cursor.getInt(1));
                ingredient.setIngredient(
                        cursor.getString(2));
                ingredient.setQuantity(
                        cursor.getDouble(3));
                ingredient.setUnitMeasure(
                        cursor.getString(4));
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return ingredientList;
    }

    public ArrayList<Recipe> getSuggestedRecipes() { //strict matching logic creation
        ArrayList<Recipe> suggestedRecipes =
                new ArrayList<>();
        ArrayList<Recipe> recipes = getAllRecipes();
        for (Recipe recipe : recipes) {
            boolean canMake = true;
            ArrayList<RecipeIngredient> ingredients =
                    getIngredientsForRecipe(
                            recipe.getRecipeId());
            for (RecipeIngredient ingredient : ingredients) {
                boolean found = false;
                ArrayList<PantryItem> pantryItems =
                        getAllIngredients();
                for (PantryItem pantryItem : pantryItems) {
                    if (pantryItem.getIngredient()
                            .equalsIgnoreCase(
                                    ingredient.getIngredient())) {
                        if (pantryItem.getQuantity()
                                >= ingredient.getQuantity()) {
                            found = true;
                        }
                    }
                }
                if (!found){
                    canMake = false;
                    break;
                }
            }
            if (canMake){
                suggestedRecipes.add(recipe);
            }
        }
        return suggestedRecipes;
    }
}





//database helper manages the SQLite database where it will create tables and CRUD methods