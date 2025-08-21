package com.s23010486.easyjob;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    private Switch switchNotifications, switchSecurity;
    private RadioGroup rgTheme;
    private Spinner spinnerLanguage;
    private Button btnSaveSettings;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "JobAppSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();
        loadSavedSettings();
        setupListeners();
    }

    private void initializeViews() {
        switchNotifications = findViewById(R.id.switchNotifications);
        switchSecurity = findViewById(R.id.switchSecurity);
        rgTheme = findViewById(R.id.rgTheme);
        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Setup language spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);
    }

    private void loadSavedSettings() {
        boolean notificationsEnabled = sharedPreferences.getBoolean("notifications", true);
        switchNotifications.setChecked(notificationsEnabled);

        boolean securityEnabled = sharedPreferences.getBoolean("security", false);
        switchSecurity.setChecked(securityEnabled);

        int themeMode = sharedPreferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_NO);
        if (themeMode == AppCompatDelegate.MODE_NIGHT_YES) {
            rgTheme.check(R.id.rbDarkTheme);
        } else {
            rgTheme.check(R.id.rbLightTheme);
        }

        String savedLanguage = sharedPreferences.getString("language", "en");
        int position = savedLanguage.equals("si") ? 1 : 0;
        spinnerLanguage.setSelection(position);
    }

    private void setupListeners() {
        btnSaveSettings.setOnClickListener(v -> saveSettings());
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("notifications", switchNotifications.isChecked());
        editor.putBoolean("security", switchSecurity.isChecked());

        int themeMode = (rgTheme.getCheckedRadioButtonId() == R.id.rbDarkTheme) ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        editor.putInt("theme", themeMode);

        String selectedLanguage = spinnerLanguage.getSelectedItemPosition() == 1 ? "si" : "en";
        editor.putString("language", selectedLanguage);

        editor.apply();
        AppCompatDelegate.setDefaultNightMode(themeMode);

        Toast.makeText(this, "Settings saved successfully!", Toast.LENGTH_SHORT).show();
    }
}