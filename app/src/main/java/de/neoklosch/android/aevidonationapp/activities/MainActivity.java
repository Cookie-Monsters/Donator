package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

import java.io.File;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.R;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView headline = (TextView) findViewById(R.id.donate_view_headline);
        TextView description = (TextView) findViewById(R.id.donate_view_description);
        ImageView charityImage = (ImageView) findViewById(R.id.donate_activity_charity_image);

        headline.setText(SharedPreferencesHelper.getString(MainActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_NAME, getString(R.string.charity_name_default)));
        description.setText(SharedPreferencesHelper.getString(MainActivity.this, Constants.SHARED_PREFERENCES_KEY_DESCRIPTION, getString(R.string.charity_description_default)));
        String charityImagePath = SharedPreferencesHelper.getString(MainActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_IMAGE, "");
        if (TextUtils.equals(charityImagePath, "")) {
            charityImage.setVisibility(View.GONE);
        } else {
            File imageFile = new File(charityImagePath);
            charityImage.setImageURI(Uri.parse(imageFile.toString()));
            charityImage.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.donate_activity_button_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent amountChooserIntent = new Intent(MainActivity.this, AmountChooserActivity.class);
                startActivity(amountChooserIntent);
            }
        });

        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.ripple_background);
        rippleBackground.startRippleAnimation();
    }
}
