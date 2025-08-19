package com.s23010486.easyjob;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static final String TAG = "ForgotPasswordActivity";
    private EditText emailEditText;
    private Button resetButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        resetButton = findViewById(R.id.resetButton);

        // Check for null views
        if (emailEditText == null || resetButton == null) {
            Log.e(TAG, "One or more views not found in forgotpassword.xml");
            Toast.makeText(this, "Layout error, contact support", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Reset button click listener
        resetButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Implement actual password reset logic (e.g., send email)
                Toast.makeText(this, "Password reset link sent to " + email, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}