package de.neoklosch.android.aevidonationapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
            Intent settingsActivityIntent = new Intent(BaseActivity.this, SettingsActivity.class);
            settingsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(settingsActivityIntent);
            finish();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        ImageView imageView = new ImageView(BaseActivity.this);
//        imageView.setImageDrawable(BaseActivity.this.getResources().getDrawable(R.mipmap.settings));
//        imageView.setAdjustViewBounds(true);
//        imageView.setMaxHeight(20);
//        imageView.setMaxWidth(20);
//        imageView.setPadding(5, 5, 5, 5);
//
//        FrameLayout rootLayout = (FrameLayout) findViewById(android.R.id.content);
//        rootLayout.addView(imageView);
    }


}
