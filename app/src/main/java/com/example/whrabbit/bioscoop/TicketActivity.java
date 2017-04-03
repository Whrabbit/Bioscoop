package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mark on 2-4-2017.
 */

public class TicketActivity extends AppCompatActivity {

    Button ticketSeatBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketSeatBttn = (Button) findViewById(R.id.ticketSeatBttn);
        ticketSeatBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectSeatsActivity.class);
                startActivity(i);
            }
        });
    }
}
