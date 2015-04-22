package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.skyfishjy.library.RippleBackground;

import de.neoklosch.android.aevidonationapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
