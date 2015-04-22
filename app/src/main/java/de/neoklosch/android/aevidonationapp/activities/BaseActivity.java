package de.neoklosch.android.aevidonationapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aevi.authentication.AuthenticationRequest;
import com.aevi.authentication.AuthenticationResult;
import com.aevi.authentication.Role;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.R;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SharedPreferencesHelper.getBoolean(this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, false)) {
            startAuthenticationRequest();
        }
    }

    public void startAuthenticationRequest() {
        startActivityForResult(new AuthenticationRequest(Role.MANAGER, Role.TECHNICIAN).createIntent(), Constants.AUTHENTICATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.AUTHENTICATION_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                if (!SharedPreferencesHelper.getBoolean(this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, false)) {
                    finish();
                } else {
                    Toast.makeText(BaseActivity.this, getString(R.string.error_no_permission), Toast.LENGTH_SHORT).show();
                }
                return;
            }

            Intent settingsActivityIntent = new Intent(BaseActivity.this, SettingsActivity.class);
            settingsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(settingsActivityIntent);
            finish();
        }
    }
}
