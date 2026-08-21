package com.example.smartpantryassignment.models;

import android.os.Bundle;

import androidx.activity.EdgeToEdge; //pulls the Jetpack utility tools used to force the apps ui to draw behind the system bars for fullscreen design
import androidx.appcompat.app.AppCompatActivity; //backward compatible base class for creating screens to run accross diferent versions of android
import androidx.core.graphics.Insets; //core graphics inserts the structural padding applied to rectangle to shrink and expand bounderies
import androidx.core.view.ViewCompat; //compatability view
import androidx.core.view.WindowInsetsCompat; //utility class to calculate exact dimensions of system interface elements such as status bar
import androidx.recyclerview.widget.LinearLayoutManager; //layoutmgr
import androidx.recyclerview.widget.RecyclerView; //recyclerview
import com.example.smartpantryassignment.adapters.PantryAdapter; //connection to other modules
import com.example.smartpantryassignment.database.DatabaseHelper;
import com.example.smartpantryassignment.models.PantryItem;
import com.example.smartpantryassignment.R;
import java.util.ArrayList;

public class PantryActivity extends AppCompatActivity {

    RecyclerView recyclerPantry;
    PantryAdapter adapter;
    DatabaseHelper dbHelper;
    ArrayList<PantryItem> pantryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry);

        recyclerPantry = findViewById(R.id.recyclerPantry);
        dbHelper = new DatabaseHelper(this);
        pantryList = dbHelper.getAllIngredients();
        adapter = new PantryAdapter(pantryList);
        recyclerPantry.setLayoutManager(
                new LinearLayoutManager(this));
        recyclerPantry.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}