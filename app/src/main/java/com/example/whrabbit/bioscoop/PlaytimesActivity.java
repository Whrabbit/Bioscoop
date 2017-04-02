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

public class PlaytimesActivity extends AppCompatActivity {

    private Button reviewsTabBttn, infoTabBttn;
    private ListView playtimesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playtimes);

        reviewsTabBttn = (Button) findViewById(R.id.reviewsTabBttn);
        reviewsTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReviewListActivity.class);
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

        playtimesList = (ListView) findViewById(R.id.playtimesList);
        playtimesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), TicketActivity.class);
                startActivity(i);
            }
        });
    }
}
