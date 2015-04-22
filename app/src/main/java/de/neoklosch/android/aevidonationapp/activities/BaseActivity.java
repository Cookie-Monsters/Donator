package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SharedPreferencesHelper.getBoolean(this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, false)) {
            Intent settingsActivityIntent = new Intent(BaseActivity.this, SettingsActivity.class);
            settingsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(settingsActivityIntent);
            finish();
        }
    }
}
