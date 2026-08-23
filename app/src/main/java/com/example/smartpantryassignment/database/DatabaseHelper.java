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
    private static final int DATABASE_VERSION = 7;

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
        db.execSQL(CREATE_RECIPE_INGREDIENTS_TABLE);//create recipe ingrdients
        //ContentValues values = new ContentValues();
        //values.put(
          //      "recipeName",
            //    "Test Recipe");
        //values.put(
          //      "recipeProcess",
            //    "test method");
        //db.insert(
          //      "Recipes",
            //    null,
              //  values);



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
        db.execSQL("DROP TABLE IF EXISTS PantryItems");
        db.execSQL("DROP TABLE IF EXISTS Recipes");
        db.execSQL("DROP TABLE IF EXISTS RecipeIngredients");
        onCreate(db);
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
        addRecipe(
                "Potato White Sauce",
                "Heat milk and butter. Use a tablespoon of milk to mix the potato flour and salt together to ensure no clumping. Add potato flour and milk mix stirring until smooth, leave for 4 mins then remove and serve."
        );
        addRecipe(
                "Rice Flour Flatbread",
                "Boil potato. Mix the Rice flour, salt and potato flour together. Mash the boiled potato with the flour mix and press into 2 balls. Flatten and rollout. Heat pan to very hot and turn the flatbread when it makes big air pockets "
        );
        addRecipe(
                "Cottage Pie",
                "Add mince to an oven pan. Add the mash on top and level. Put the grated cheese generously on top. Bake until cheese browns and bubbles ."
        );
        addRecipe(
                "BLT Sandwich",
                "Pack the bread with the bacon, Tomato slices and lettuce in layers. Season with salt and blackpepper corns and enjoy."
        );
        addRecipe(
                "Dagwood Sandwich",
                "Fry the egg, add it to you breadroll as base. Add the steakstrips and bacon on top. Add the sliced tomatoes and sauce to complete it."
        );
        addRecipe(
                "Boerewors Rolls",
                "Put the Boerewors on the hotdog roll. Add the garnish of onions and tomato mix"
        );
        addRecipe(
                "Chicken Lentil Soup",
                "Put the Chicken, lentils, garlic, potato and butter into the slowcooker and cook for 2 hours. Spice close to 1 and a half hour afetr cooking for best taste ."
        );
        addRecipe(
                "Ostrich Flatbread meal",
                "Cook the ostrich pieces in the garlic and water mix. Spice as per your liking with your favourite spice mix. Butter the flatbreads and put the ostrich pieces on top."
        );
        addRecipe(
                "Apple Pie",
                "Cut the dough into square pieces of 15 cm by 15cm. Add a teaspoon of the apple and raisen mix into the middle. Fold all corners over to the middle until entirely wrapped. Bake for 30 mins."
        );
        addRecipe(
                "Ricepaper Mochi",
                "Fold the yogurt and fruit mix into the ricepaper. Freeze for 30 minutes"
        );
        addRecipe(
                "Rumballs",
                "Mix the condensemilk, biscuits and cacao together. Add the rum and roll into small balls - palmsize. Roll the balls in the coconut and put in the fridge for 30 minutes - better served cold."
                );
        addRecipe(
                "Sugar Cone Marshmallow Desert",
                "Fill the sugar cones with marshmallows, add the blocks of chocolate and cover in foil. Put on the braai for 10 minutes."
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
        values.put("unitMeasure", unitMeasure);
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
        // Toasti cheese and tomato RecipeId 4
        addRecipeIngredient(4,
                "Bread",
                2,
                "pieces");
        addRecipeIngredient(4,
                "Cheese",
                2,
                "slices");
        addRecipeIngredient(4,
                "Tomato",
                1,
                "piece");
        // Egg Fried Rice RecipeId 5
        addRecipeIngredient(5,
                "Rice",
                250,
                "g");
        addRecipeIngredient(5,
                "Eggs",
                2,
                "pieces");
        // Potato White Sauce RecipeId 6
        addRecipeIngredient(6,
                "Potato flour",
                1,
                "tablespoon");
        addRecipeIngredient(6,
                "Butter",
                1,
                "tablespoon");
        addRecipeIngredient(6,
                "Milk",
                1,
                "cup");
        // Rice flour flat bread RecipeId 7
        addRecipeIngredient(7,
                "Potato flour",
                1,
                "tablespoon");
        addRecipeIngredient(7,
                "Rice Flour",
                125,
                "g");
        addRecipeIngredient(7,
                "Boiled Potato",
                1,
                "piece");
        // Cottage Pie RecipeId 8
        addRecipeIngredient(8,
                "Mince",
                1,
                "cup");
        addRecipeIngredient(8,
                "Mashed Potatoes",
                1,
                "cup");
        addRecipeIngredient(8,
                "Cheese",
                1,
                "cup");
        // BLT sandwich RecipeId 9
        addRecipeIngredient(9,
                "Bacon",
                5,
                "strips");
        addRecipeIngredient(9,
                "Bread",
                1,
                "piece");
        addRecipeIngredient(9,
                "Lettuce",
                2,
                "pieces");
        addRecipeIngredient(9,
                "Tomato",
                2,
                "slices");
        // Dagwood RecipeId 10
        addRecipeIngredient(10,
                "Steakstrips",
                1,
                "cup");
        addRecipeIngredient(10,
                "Egg",
                2,
                "pieces");
        addRecipeIngredient(10,
                "Tomato",
                2,
                "slices");
        addRecipeIngredient(10,
                "Bread",
                2,
                "slices");
        // Boerewors Roll RecipeId 11
        addRecipeIngredient(11,
                "Hotdog Roll",
                1,
                "Roll");
        addRecipeIngredient(11,
                "Boerewors",
                1,
                "piece");
        addRecipeIngredient(11,
                "Tomato and Onion mix",
                3,
                "tablespoon");
        // Chicken Cabbage Lentil Soup RecipeId 12
        addRecipeIngredient(12,
                "Chicken pieces",
                1,
                "cup");
        addRecipeIngredient(12,
                "Lentils",
                1,
                "cup");
        addRecipeIngredient(12,
                "Garlic cloves",
                3,
                "cup");
        addRecipeIngredient(12,
                "Baby cabbage",
                1,
                "piece");
        // Ostrich flatbread meal RecipeId 13
        addRecipeIngredient(13,
                "Ostrich cubes",
                1,
                "cup");
        addRecipeIngredient(13,
                "Flatbread",
                1,
                "piece");
        addRecipeIngredient(13,
                "Onions",
                1,
                "cup");
        // Apple pie RecipeId 14
        addRecipeIngredient(14,
                "Apple",
                1,
                "piece");
        addRecipeIngredient(14,
                "Puff Pastry",
                1,
                "roll");
        addRecipeIngredient(14,
                "Sugar",
                1,
                "cup");
        addRecipeIngredient(14,
                "Raisins",
                1,
                "cup");
        // Rice Paper Mochi RecipeId 15
        addRecipeIngredient(15,
                "Rice Paper",
                1,
                "piece");
        addRecipeIngredient(15,
                "Yogurt",
                250,
                "ml");
        addRecipeIngredient(15,
                "Strawberry",
                1,
                "cup");
        // Rum Balls RecipeId 8
        addRecipeIngredient(16,
                "Rum",
                2,
                "teaspoons");
        addRecipeIngredient(16,
                "Cocoa powder",
                1,
                "cup");
        addRecipeIngredient(16,
                "Marie Biscuits",
                1,
                "pack");
        addRecipeIngredient(16,
                "Condensedmilk",
                1,
                "can");
        addRecipeIngredient(16,
                "Dried coconut flakes",
                1,
                "cup");
        // Sugar Cones marshmallow desert RecipeId 17
        addRecipeIngredient(17,
                "Chocolate bar blocks",
                3,
                "pieces");
        addRecipeIngredient(17,
                "Ice Cream Cone",
                1,
                "cone");
        addRecipeIngredient(17,
                "Marshmallows",
                3,
                "pieces");
    }
    public void seedDatabase(){
        seedRecipes();
        seedRecipeIngredients();

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
            if (ingredients.size() == 0) {
                canMake = false;

            }
            for (RecipeIngredient ingredient : ingredients) {
                boolean found = false;
                ArrayList<PantryItem> pantryItems =
                        getAllIngredients();
                for (PantryItem pantryItem : pantryItems) {
                    if (pantryItem.getIngredient()
                            .equalsIgnoreCase(
                                    ingredient.getIngredient())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) {
                suggestedRecipes.add(recipe);
            }
        }
        return suggestedRecipes;

    }
                public Recipe getRecipeById ( int recipeId){
                    SQLiteDatabase db = this.getReadableDatabase();
                    Cursor cursor = db.rawQuery(
                            "SELECT * FROM Recipes WHERE recipeId=?",
                            new String[]{String.valueOf(recipeId)}
                    );
                    Recipe recipe = null;
                    if (cursor.moveToFirst()) {
                        recipe = new Recipe();
                        recipe.setRecipeId(
                                cursor.getInt(0));
                        recipe.setRecipeName(
                                cursor.getString(1));
                        recipe.setRecipeProcess(
                                cursor.getString(2));
                    }
                    cursor.close();
                    db.close();
                    return recipe;
                }
            }


//database helper manages the SQLite database where it will create tables and CRUD methods