package com.example.whrabbit.bioscoop;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button filmBtn, biosBtn, ticketBtn, languageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmBtn = (Button) findViewById(R.id.filmBtn);
        filmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FilmList.class);
                startActivity(i);
            }
        });


        biosBtn = (Button) findViewById(R.id.biosBtn);
        biosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Contact.class);
                startActivity(i);
            }
        });


        ticketBtn = (Button) findViewById(R.id.ticketBtn);
        ticketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ticket.class);
                startActivity(i);
            }
        });
    }
}

