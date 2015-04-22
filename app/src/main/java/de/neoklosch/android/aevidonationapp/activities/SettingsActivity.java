package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.andreabaccega.widget.FormEditText;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.R;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormEditText charityName = (FormEditText) findViewById(R.id.charity_name);
                FormEditText description = (FormEditText) findViewById(R.id.description);
                FormEditText defaultAmount = (FormEditText) findViewById(R.id.default_amount);

                FormEditText[] allFields = {charityName, description, defaultAmount};

                boolean allValid = true;
                for (FormEditText field: allFields) {
                    allValid = field.testValidity() && allValid;
                }

                float defaultAmountValue = 0.0f;
                try {
                    defaultAmountValue = Float.parseFloat(defaultAmount.getText().toString());
                } catch (NumberFormatException nfe) {
                    defaultAmount.setError(getString(R.string.error_wrong_default_amount));
                    allValid = false;
                }

                if (allValid) {
                    SharedPreferencesHelper.putString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_NAME, charityName.getText().toString());
                    SharedPreferencesHelper.putString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DESCRIPTION, description.getText().toString());
                    SharedPreferencesHelper.putFloat(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DEFAULT_AMOUNT, defaultAmountValue);
                    SharedPreferencesHelper.putBoolean(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, true);
                    Intent mainActivityIntent = new Intent(SettingsActivity.this, MainActivity.class);
                    mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainActivityIntent);
                    finish();
                }
            }
        });
    }
}
