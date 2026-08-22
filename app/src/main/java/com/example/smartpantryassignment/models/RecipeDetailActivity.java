package com.example.smartpantryassignment.models;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.smartpantryassignment.R;

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
        txtRecipeName.setText("Pancakes");
        txtIngredients.setText(
                "Eggs\nmilk\nFlour"
        );
        txtMethod.setText(
                "Mix ingredients.\nCook in pan.\nServe."
        );


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}