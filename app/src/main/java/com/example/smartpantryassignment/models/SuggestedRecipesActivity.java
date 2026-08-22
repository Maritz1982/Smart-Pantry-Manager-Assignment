package com.example.smartpantryassignment.models;

import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import com.example.smartpantryassignment.R;
import com.example.smartpantryassignment.database.DatabaseHelper;
import com.example.smartpantryassignment.models.Recipe;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import com.example.smartpantryassignment.R;

public class SuggestedRecipesActivity extends AppCompatActivity {
    TextView txtRecipes;
    DatabaseHelper dbHelper;
    Button btnOpenRecipe;
    EditText edtRecipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);
        txtRecipes = findViewById(R.id.txtRecipes);
        dbHelper = new DatabaseHelper(this);
        ArrayList<Recipe> recipes =
                dbHelper.getSuggestedRecipes();
        btnOpenRecipe =
                findViewById(R.id.btnOpenRecipe);
        btnOpenRecipe.setOnClickListener(v -> {
            if (!recipes.isEmpty()) {
                Intent intent =
                        new Intent(
                                SuggestedRecipesActivity.this,
                                RecipeDetailActivity.class);
                intent.putExtra(
                        "recipeId",
                        recipes.get(0).getRecipeId());
                startActivity(intent);

            }
        });
        StringBuilder builder =
                new StringBuilder();
        builder.append("recipes Found: ")
                .append(recipes.size())
                .append("\n\n");
        if (recipes.size() == 0) {
            builder.append(
                    "No Recipes match your pantry yet.\nAdd more ingredients."
            );

        } else {
            for (Recipe recipe : recipes) {
                builder.append(
                                recipe.getRecipeId()
                        ).append(" - ")
                        .append(
                                recipe.getRecipeName()
                        ).append("\n");
            }
        }


        txtRecipes.setText(builder.toString());
        edtRecipeId =
                findViewById(R.id.edtRecipeId);
        btnOpenRecipe =
                findViewById(R.id.btnOpenRecipe);
        btnOpenRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int recipeId =
                        Integer.parseInt(
                                edtRecipeId.getText().toString());
                Intent intent =
                        new Intent(
                                SuggestedRecipesActivity.this,
                                RecipeDetailActivity.class);
                intent.putExtra(
                        "recipeId",
                        recipeId);
                startActivity(intent);

            }
        });


        }
        }
