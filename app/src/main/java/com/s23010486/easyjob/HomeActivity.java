package com.s23010486.easyjob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initialize views
        TextView tvUserName = findViewById(R.id.tvUserName);
        CardView cardHirePeople = findViewById(R.id.HirePeople); // Fixed to match XML
        CardView cardFindJobs = findViewById(R.id.cardFindJobs);
        FloatingActionButton fabChatBot = findViewById(R.id.fabChatBot);
        View chatOverlay = findViewById(R.id.chatOverlay);

        // Set user name
        tvUserName.setText("John Doe");

        // Set click listeners
        cardHirePeople.setOnClickListener(v -> {
            // Handle hire people click
            startActivity(new Intent(HomeActivity.this, HirePeopleActivity.class));
        });

        cardFindJobs.setOnClickListener(v -> {
            // Handle find jobs click

        });

        // FAB chat button
        fabChatBot.setOnClickListener(v -> {
            // Show chat overlay
            chatOverlay.setVisibility(View.VISIBLE);
        });

        // Bottom navigation click handlers
        findViewById(R.id.navHome).setOnClickListener(v -> {
            // Already in home, do nothing or refresh
        });

        findViewById(R.id.chat).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ChatActivity.class));
        });

        findViewById(R.id.job).setOnClickListener(v -> {

        });

        findViewById(R.id.history).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
        });

        // Chat overlay close button
        findViewById(R.id.btnCloseChat).setOnClickListener(v -> {
            chatOverlay.setVisibility(View.GONE);
        });

        // Click outside chat to close
        chatOverlay.setOnClickListener(v -> {
            chatOverlay.setVisibility(View.GONE);
        });
    }

    @Override
    public void onBackPressed() {
        View chatOverlay = findViewById(R.id.chatOverlay);
        if (chatOverlay.getVisibility() == View.VISIBLE) {
            chatOverlay.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}