package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
    private Button finalBtn, idealBtn, paypalBtn, creditcardBtn;
    private TextView ticketPrijsNumber, selectedSeat, method;
    private String paymentMethod = "iDeal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ticketPrijsNumber = (TextView) findViewById(R.id.ticketPrijsNumber);
        selectedSeat = (TextView) findViewById(R.id.selected_seat);
        method = (TextView) findViewById(R.id.ticketPayView);

        ticketPrijsNumber.setText(getString(R.string.ticketPrijsNumber) + getIntent().getExtras().getInt("PRICE") + "");
        selectedSeat.setText(" " + getIntent().getExtras().getInt("SEAT"));
        method.setText(paymentMethod);

        finalBtn = (Button) findViewById(R.id.finalBtn);
        finalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FinalActivity.class);
                i.putExtras(getIntent().getExtras());
                i.putExtra("PAYMENT_METHOD", paymentMethod);
                startActivity(i);
            }
        });

        idealBtn = (Button) findViewById(R.id.ticketIDealBttn);
        paypalBtn = (Button) findViewById(R.id.ticketPaypalBttn);
        creditcardBtn = (Button) findViewById(R.id.ticketCreditBttn);

        idealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethod = "iDeal";
                method.setText(paymentMethod);
            }
        });

        paypalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethod = "PayPal";
                method.setText(paymentMethod);
            }
        });

        creditcardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentMethod = "Credit Card";
                method.setText(paymentMethod);
            }
        });
    }
}
