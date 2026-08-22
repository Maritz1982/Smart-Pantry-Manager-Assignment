package com.example.smartpantryassignment.models;

import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.smartpantryassignment.R;
import com.example.smartpantryassignment.database.DatabaseHelper;
import android.widget.Button;
import android.content.Intent;
import com.example.smartpantryassignment.models.RecipeIngredient;

public class RecipeDetailActivity extends AppCompatActivity {
    TextView txtRecipeName;
    TextView txtIngredients;
    TextView txtMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);
        txtRecipeName = findViewById(R.id.txtRecipeName);
        txtIngredients = findViewById(R.id.txtIngredients);
        txtMethod = findViewById(R.id.txtMethod);
        int recipeId =
                getIntent().getIntExtra(
                        "recipeId",
                        -1);
        DatabaseHelper dbHelper =
                new DatabaseHelper(this);
        Recipe recipe =
                dbHelper.getRecipeById(recipeId);
        if (recipe != null) {
            txtRecipeName.setText(
                    recipe.getRecipeName());
            txtMethod.setText(
                    recipe.getRecipeProcess());
            ArrayList<RecipeIngredient> ingredients =
                    dbHelper.getIngredientsForRecipe(recipeId);
                    StringBuilder ingredientBuilder =
                            new StringBuilder();
            for (RecipeIngredient ingredient : ingredients) {
                ingredientBuilder.append(
                        ingredient.getIngredient())
                        .append( " - ")
                        .append(
                                ingredient.getQuantity())
                        .append(" ")
                        .append(
                                ingredient.getUnitMeasure())
                        .append("\n");


            }
            txtIngredients.setText(
                    ingredientBuilder.toString());


        }

    }

}