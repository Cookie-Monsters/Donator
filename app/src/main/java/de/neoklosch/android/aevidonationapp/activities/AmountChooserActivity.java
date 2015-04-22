package de.neoklosch.android.aevidonationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aevi.payment.PaymentRequest;
import com.aevi.payment.TransactionResult;
import com.aevi.payment.TransactionStatus;

import java.math.BigDecimal;
import java.util.Currency;

import de.neoklosch.android.aevidonationapp.Constants;
import de.neoklosch.android.aevidonationapp.R;
import de.neoklosch.android.aevidonationapp.SharedPreferencesHelper;

public class AmountChooserActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_chooser);

        EditText amountValue = (EditText) findViewById(R.id.amount_chooser_activity_value);
        amountValue.setText("" + SharedPreferencesHelper.getFloat(AmountChooserActivity.this, Constants.SHARED_PREFERENCES_KEY_DEFAULT_AMOUNT, 10.0f));

        findViewById(R.id.amount_chooser_activity_button_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText amountValue = (EditText) findViewById(R.id.amount_chooser_activity_value);
                PaymentRequest payment = new PaymentRequest(new BigDecimal(amountValue.getText().toString()));
                payment.setCurrency(Currency.getInstance("EUR"));
                startActivityForResult(payment.createIntent(), Constants.PAYMENT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.PAYMENT_REQUEST_CODE) {
            TransactionResult result = TransactionResult.fromIntent(data);
            if (result.getTransactionStatus() == TransactionStatus.APPROVED) {
                Intent successIntent = new Intent(AmountChooserActivity.this, SuccessActivity.class);
                startActivity(successIntent);
            } else {
                Toast.makeText(this, "An Error occurred", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
