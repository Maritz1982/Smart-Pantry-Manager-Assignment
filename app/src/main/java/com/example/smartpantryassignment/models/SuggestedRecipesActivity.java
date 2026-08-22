package com.example.smartpantryassignment.models;

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

import com.example.smartpantryassignment.R;

public class SuggestedRecipesActivity extends AppCompatActivity {
    TextView txtRecipes;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);
        txtRecipes = findViewById(R.id.txtRecipes);
        dbHelper = new DatabaseHelper (this);
        ArrayList<Recipe> recipes =
                dbHelper.getSuggestedRecipes();
        StringBuilder builder =
                new StringBuilder();
        if (recipes.size)( == 0){
            builder.append(
                    "No recipes match your pantry yet. \nAdd more ingredients."

            );
        } else{
            for (Recipe recipe : recipes) {
                builder.append(
                        recipe.getRecipeName()
                ).append("\n");
            }
        }
        txtRecipes.setText(builder.toString());
    }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}