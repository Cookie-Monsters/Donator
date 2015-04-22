package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

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

        headline.setText(SharedPreferencesHelper.getString(MainActivity.this, Constants.SHARED_PREFERENCES_KEY_CHARITY_NAME, getString(R.string.charity_name_default)));
        description.setText(SharedPreferencesHelper.getString(MainActivity.this, Constants.SHARED_PREFERENCES_KEY_DESCRIPTION, getString(R.string.charity_description_default)));

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
