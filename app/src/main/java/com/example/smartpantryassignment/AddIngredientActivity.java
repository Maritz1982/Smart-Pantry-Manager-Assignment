package com.example.smartpantryassignment;

import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.smartpantryassignment.database.DatabaseHelper;
import com.example.smartpantryassignment.models.PantryItem;

public class AddIngredientActivity extends AppCompatActivity {

    EditText editIngredient;
    EditText editQuantity;
    EditText editUnitMeasure;
    EditText editExpireDate;
    Button btnSave;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ingredient);
        editIngredient = findViewById(R.id.editIngredient);
        editQuantity = findViewById(R.id.editQuantity);
        editUnitMeasure = findViewById(R.id.editUnitMeasure);
        editExpireDate = findViewById(R.id.editExpireDate);
        btnSave = findViewById(R.id.btnSave);

        dbHelper = new DatabaseHelper(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient =
                        editIngredient.getText().toString().trim();
                String quantityText =
                        editQuantity.getText().toString().trim();
                String unitMeasure =
                        editUnitMeasure.getText().toString().trim();
                String expireDate =
                        editExpireDate.getText().toString().trim();
                if (ingredient.isEmpty()) {
                    editIngredient.setError("Ingredient Required");
                    return;
                }
                if (quantityText.isEmpty()) {
                    editQuantity.setError("Quantity Required");
                    return;
                }
                double quantity =
                        Double.parseDouble(quantityText);
                PantryItem item = new PantryItem();

                item.setIngredient(ingredient);
                item.setQuantity(quantity);
                item.setUnitMeasure(unitMeasure);
                item.setExpireDate(expireDate);
                dbHelper.addIngredient(item);
                Toast.makeText(
                        AddIngredientActivity.this,
                        "Ingredient Saved",
                        Toast.LENGTH_SHORT
                ).show();
                editIngredient.setText("");
                editQuantity.setText("");
                editUnitMeasure.setText("");
                editExpireDate.setText("");
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}