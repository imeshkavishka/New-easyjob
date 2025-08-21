package com.s23010486.easyjob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class FindJobActivity extends AppCompatActivity {

    private Button fullTimeButton;
    private Button partTimeButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_jobs);

        // Initialize views - FIXED IDs to match XML
        fullTimeButton = findViewById(R.id.fullTimeButton);
        partTimeButton = findViewById(R.id.partTimeButton);
        settingsButton = findViewById(R.id.settingsButton);

        // Set click listeners
        fullTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullTimeButton.setBackgroundResource(R.drawable.button_selected_background);
                fullTimeButton.setTextColor(getResources().getColor(android.R.color.white));
                partTimeButton.setBackgroundResource(R.drawable.button_unselected_background);
                partTimeButton.setTextColor(getResources().getColor(android.R.color.black));
            }
        });

        partTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partTimeButton.setBackgroundResource(R.drawable.button_selected_background);
                partTimeButton.setTextColor(getResources().getColor(android.R.color.white));
                fullTimeButton.setBackgroundResource(R.drawable.button_unselected_background);
                fullTimeButton.setTextColor(getResources().getColor(android.R.color.black));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindJobActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}