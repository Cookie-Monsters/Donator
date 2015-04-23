package de.neoklosch.android.aevidonationapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aevi.authentication.AuthenticationRequest;
import com.aevi.authentication.Role;
import com.andreabaccega.widget.FormEditText;

import java.io.File;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.R;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;


public class SettingsActivity extends AppCompatActivity {
    private String donateButtonImagePath;
    private String charityImagePath;
    private FormEditText charityName;
    private FormEditText description;
    private FormEditText defaultAmount;
    private ImageView charityImageChooser;
    private ImageView donateButtonImageChooser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        startAuthenticationRequest();

        charityName = (FormEditText) findViewById(R.id.charity_name);
        description = (FormEditText) findViewById(R.id.description);
        defaultAmount = (FormEditText) findViewById(R.id.default_amount);
        charityImageChooser = (ImageView) findViewById(R.id.activity_settings_charity_image_chooser);
        donateButtonImageChooser = (ImageView) findViewById(R.id.activity_settings_donate_button_image_chooser);

        prefillValues();

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormEditText[] allFields = {charityName, description, defaultAmount};

                boolean allValid = true;
                for (FormEditText field: allFields) {
                    allValid = field.testValidity() && allValid;
                }

                float defaultAmountValue = 0.0f;
                try {
                    defaultAmountValue = Float.parseFloat(defaultAmount.getText().toString());
                    if (defaultAmountValue <= 0.0f) {
                        allValid = false;
                    }
                } catch (NumberFormatException nfe) {
                    allValid = false;
                }

                if (allValid) {
                    SharedPreferencesHelper.putString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_NAME, charityName.getText().toString());
                    SharedPreferencesHelper.putString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DESCRIPTION, description.getText().toString());
                    SharedPreferencesHelper.putFloat(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DEFAULT_AMOUNT, defaultAmountValue);
                    SharedPreferencesHelper.putBoolean(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, true);
                    if (charityImagePath != null && !TextUtils.equals(charityImagePath, "")) {
                        SharedPreferencesHelper.putString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_IMAGE, charityImagePath);
                    }
                    if (donateButtonImagePath != null && !TextUtils.equals(donateButtonImagePath, "")) {
                        SharedPreferencesHelper.putString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DONATE_BUTTON_IMAGE, donateButtonImagePath);
                    }
                    Intent mainActivityIntent = new Intent(SettingsActivity.this, MainActivity.class);
                    mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainActivityIntent);
                    finish();
                }
            }
        });

        findViewById(R.id.activity_settings_charity_image_chooser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageChooserIntent = new Intent();
                imageChooserIntent.setType("image/*");
                imageChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imageChooserIntent, "Select a Picture"), Constants.CHARITY_IMAGE_CHOOSER_REQUEST_CODE);
            }
        });

        findViewById(R.id.activity_settings_donate_button_image_chooser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageChooserIntent = new Intent();
                imageChooserIntent.setType("image/*");
                imageChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imageChooserIntent, "Select a Picture"), Constants.DONATE_BUTTON_IMAGE_CHOOSER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CHARITY_IMAGE_CHOOSER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                charityImagePath = getRealPathFromURI(SettingsActivity.this, selectedImageUri);
                charityImageChooser.setImageURI(selectedImageUri);
            }
        } else if (requestCode == Constants.DONATE_BUTTON_IMAGE_CHOOSER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                donateButtonImagePath = getRealPathFromURI(SettingsActivity.this, selectedImageUri);
                Log.v("MARKUS KEKE", donateButtonImagePath);
                donateButtonImageChooser.setImageURI(selectedImageUri);
            }
        } else if (requestCode == Constants.AUTHENTICATION_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                if (SharedPreferencesHelper.getBoolean(this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, false)) {
                    finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startAuthenticationRequest() {
        startActivityForResult(new AuthenticationRequest(Role.MANAGER, Role.TECHNICIAN).createIntent(), Constants.AUTHENTICATION_REQUEST_CODE);
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void prefillValues() {
        charityName.setText(SharedPreferencesHelper.getString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_NAME, ""));
        description.setText(SharedPreferencesHelper.getString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DESCRIPTION, ""));
        float defaultAmountValue = SharedPreferencesHelper.getFloat(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DEFAULT_AMOUNT, 0.0f);
        if (defaultAmountValue != 0.0f) {
            defaultAmount.setText("" + defaultAmountValue);
        }
        charityImagePath = SharedPreferencesHelper.getString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_IMAGE, "");
        if (charityImagePath != null && !TextUtils.equals(charityImagePath, "")) {
            File imageFile = new File(charityImagePath);
            charityImageChooser.setImageURI(Uri.parse(imageFile.toString()));
        }
        donateButtonImagePath = SharedPreferencesHelper.getString(SettingsActivity.this, Constants.SHARED_PREFERENCES_KEY_DONATE_BUTTON_IMAGE, "");
        if (donateButtonImagePath != null && !TextUtils.equals(donateButtonImagePath, "")) {
            File imageFile = new File(donateButtonImagePath);
            donateButtonImageChooser.setImageURI(Uri.parse(imageFile.toString()));
        }
    }
}
