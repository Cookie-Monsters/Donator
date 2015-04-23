package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.R;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;

public class SuccessActivity extends BaseActivity {
    final Handler handler = new Handler();
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            closeActivity();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        findViewById(R.id.success_activity_button_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });

        ImageView charityImage = (ImageView) findViewById(R.id.success_activity_charity_image);

        String charityImagePath = SharedPreferencesHelper.getString(SuccessActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_IMAGE, "");

        if (TextUtils.equals(charityImagePath, "")) {
            charityImage.setVisibility(View.GONE);
        } else {
            File imageFile = new File(charityImagePath);
            charityImage.setImageURI(Uri.parse(imageFile.toString()));
            charityImage.setVisibility(View.VISIBLE);
        }

        handler.postDelayed(runnable, 20000);
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    protected void closeActivity() {
        Intent mainActivityIntent = new Intent(SuccessActivity.this, MainActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainActivityIntent);
        finish();
    }
}
