package com.s23010486.easyjob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    private EditText usernameEditText, nameEditText, emailEditText, passwordEditText,
            confirmPasswordEditText, dobEditText, genderEditText,
            mobileEditText, addressEditText;
    private CheckBox rememberMeCheckBox;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        dobEditText = findViewById(R.id.dobEditText);
        genderEditText = findViewById(R.id.genderEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        addressEditText = findViewById(R.id.addressEditText);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        Button signupButton = findViewById(R.id.signupButton);
        TextView loginTextView = findViewById(R.id.loginTextView);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set click listener for signup button
        signupButton.setOnClickListener(v -> registerUser());

        // Set click listener for login text
        loginTextView.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        // Get all input values
        String username = usernameEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String dob = dobEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        int rememberMe = rememberMeCheckBox.isChecked() ? 1 : 0;

        // Validate inputs
        if (username.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email already exists
        if (dbHelper.isValidUser(email, password)) {
            Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user to database
        long newRowId = dbHelper.addUser(username, password, email, name, rememberMe);

        // Update additional fields if they exist
        if (newRowId != -1) {
            dbHelper.updateUser(username, password, email, name, dob, gender, mobile, address, rememberMe);
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}