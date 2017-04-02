package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button filmBtn, biosBtn, ticketBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmBtn = (Button) findViewById(R.id.filmBtn);
        filmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FilmListActivity.class);
                startActivity(i);
            }
        });


        biosBtn = (Button) findViewById(R.id.biosBtn);
        biosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(i);
            }
        });


        ticketBtn = (Button) findViewById(R.id.ticketBtn);
        ticketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TicketListActivity.class);
                startActivity(i);
            }
        });
    }
}

