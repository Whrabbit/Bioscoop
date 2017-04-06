package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.Domain.Ticket;

public class FinalActivity extends AppCompatActivity {
    private Button backToHomeBttn,toEticketBttn, toRouteBttn;
    private TextView thxView;

    private Bundle extra;
    private Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        extra = getIntent().getExtras();
        ticket = extra.getParcelable("t");
        final String title = extra.getString("title");

        thxView = (TextView) findViewById(R.id.bedanktView);
        thxView.setText(getString(R.string.bedanktView) + getIntent().getExtras().getString("PAYMENT_METHOD"));

        toEticketBttn = (Button) findViewById(R.id.toEticketBttn);
        toEticketBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailedTicketActivity.class);
                i.putExtra("title", title);
                i.putExtras(extra);
                Log.i("tag", title);
                startActivity(i);
            }
        });

        toRouteBttn = (Button) findViewById(R.id.toRouteBttn);
        toRouteBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(i);
            }
        });

        backToHomeBttn = (Button) findViewById(R.id.backToHomeBttn);
        backToHomeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
