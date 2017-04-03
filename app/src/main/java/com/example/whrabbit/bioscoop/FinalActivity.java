package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalActivity extends AppCompatActivity {
    private Button backToHomeBttn,toEticketBttn, toRouteBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        toEticketBttn = (Button) findViewById(R.id.toEticketBttn);
        toEticketBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailedTicketActivity.class);
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
