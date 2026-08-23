package com.example.smartpantryassignment.models;

import android.os.Bundle;
import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.smartpantryassignment.R;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class SettingActivity extends AppCompatActivity {
    Switch switchNotifications;
    Spinner spinnerDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        spinnerDays=
                findViewById(R.id.spinnerDays);
        String[] days ={
                "1 Day away",
                "3 Days away",
                "7 Days away"
        };
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        days);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(adapter);


        switchNotifications =
                findViewById(R.id.switchNotifications);
        SharedPreferences preferences =
                getSharedPreferences(
                        "SmartPantrySettings",
                        MODE_PRIVATE);
        boolean enabled =
                preferences.getBoolean(
                        "notifications",
                        false);
        switchNotifications.setChecked(enabled);
        switchNotifications.setOnCheckedChangeListener(
                (buttonView, isChecked)->{
                    SharedPreferences.Editor editor =
                            preferences.edit();
                    editor.putBoolean(
                            "notifications",
                            isChecked);
                    editor.apply();

                });

    }
}