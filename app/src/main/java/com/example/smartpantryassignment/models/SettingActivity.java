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

public class SettingActivity extends AppCompatActivity {
    Switch switchNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}