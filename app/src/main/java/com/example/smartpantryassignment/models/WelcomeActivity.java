package com.example.smartpantryassignment.models;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantryassignment.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button btnStart =
                findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v->{
            Intent intent =
                    new Intent(
                            WelcomeActivity.this,
                            PantryActivity.class);
            startActivity(intent);
        });
    }
}