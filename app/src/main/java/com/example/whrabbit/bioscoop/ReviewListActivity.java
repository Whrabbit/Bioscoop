package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by mark on 2-4-2017.
 */

public class ReviewListActivity extends AppCompatActivity {

    private Button ticketsTabBttn, infoTabBttn, makeReviewBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        ticketsTabBttn = (Button) findViewById(R.id.ticketsTabBttn);
        ticketsTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PlaytimesActivity.class);
                startActivity(i);
            }
        });

        infoTabBttn = (Button) findViewById(R.id.infoTabBttn);
        infoTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FilmInfoActivity.class);
                startActivity(i);
            }
        });

        makeReviewBttn = (Button) findViewById(R.id.makeReviewBttn);
        makeReviewBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(i);
            }
        });
    }
}
