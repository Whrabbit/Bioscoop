package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.whrabbit.bioscoop.API.Film;
import com.squareup.picasso.Picasso;

/**
 * Created by mark on 2-4-2017.
 */

public class ReviewListActivity extends AppCompatActivity {

    private Button ticketsTabBttn, infoTabBttn, makeReviewBttn;
    private ImageView filmBackdrop;
    private Bundle extra;
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        extra = getIntent().getExtras();
        film = extra.getParcelable("FILM");

        filmBackdrop = (ImageView) findViewById(R.id.filmBackdrop);

        if(film.getBackdrop_path() != null) {
            Picasso.with(this).load("https://image.tmdb.org/t/p" + "/w500" + "/" + film.getBackdrop_path()).into(filmBackdrop);
        } else {
            Picasso.with(this).load("http://placehold.it/0x0").into(filmBackdrop);
        }

        ticketsTabBttn = (Button) findViewById(R.id.ticketsTabBttn);
        ticketsTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PlaytimesActivity.class);
                i.putExtra("FILM", film);
                startActivity(i);
            }
        });

        infoTabBttn = (Button) findViewById(R.id.infoTabBttn);
        infoTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FilmInfoActivity.class);
                i.putExtra("FILM", film);
                startActivity(i);
            }
        });

        makeReviewBttn = (Button) findViewById(R.id.makeReviewBttn);
        makeReviewBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
                i.putExtra("FILM", film);
                startActivity(i);
            }
        });
    }
}
