package de.neoklosch.android.aevidonationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aevi.payment.PaymentRequest;
import com.aevi.payment.TransactionResult;

import java.math.BigDecimal;
import java.util.Currency;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.donate_activity_button_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentRequest payment = new PaymentRequest(new BigDecimal("20.00"));
                payment.setCurrency(Currency.getInstance("EUR"));
                startActivityForResult(payment.createIntent(), 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TransactionResult result = TransactionResult.fromIntent(data);
        Toast.makeText(this, "Transaction result: " + result.getTransactionStatus(), Toast.LENGTH_SHORT).show();

    }
}
