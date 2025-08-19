package com.s23010486.easyjob;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GetStartActivity extends AppCompatActivity {
    private static final String TAG = "GetStartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstart);

        Button loginButton = findViewById(R.id.loginButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(GetStartActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, "Error starting LoginActivity: " + e.getMessage());
                }
            });
        } else {
            Log.e(TAG, "loginButton not found in getstart.xml");
        }
    }
}