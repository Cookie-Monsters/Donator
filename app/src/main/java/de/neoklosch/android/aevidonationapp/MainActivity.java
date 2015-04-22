package de.neoklosch.android.aevidonationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.donate_activity_button_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent amountChooserIntent = new Intent(MainActivity.this, AmountChooser.class);
                startActivity(amountChooserIntent);
            }
        });
    }
}
