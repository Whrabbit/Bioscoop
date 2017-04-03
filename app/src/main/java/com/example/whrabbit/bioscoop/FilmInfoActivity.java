package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.API.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mark on 2-4-2017.
 */

public class FilmInfoActivity extends AppCompatActivity {

    private Button ticketsTabBttn, reviewsTabBttn;
    private TextView infoTitle, infoRelease, infoAge, infoLanguage, infoRuntime, infoGenre, infoDirector, infoRating, infoPlot;
    private ImageView filmBackdrop;
    private String genres;
    private Bundle extra;
    private Film film;
    private ArrayList genreList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info);

        extra = getIntent().getExtras();
        film = extra.getParcelable("FILM");
        genres = "";

        infoTitle = (TextView) findViewById(R.id.infoTitle);
        infoRelease = (TextView) findViewById(R.id.infoRelease);
        infoAge = (TextView) findViewById(R.id.infoAge);
        infoLanguage = (TextView) findViewById(R.id.infoLanguage);
        infoRuntime = (TextView) findViewById(R.id.infoRuntime);
        infoGenre = (TextView) findViewById(R.id.infoGenre);
        infoDirector = (TextView) findViewById(R.id.infoDirector);
        infoRating = (TextView) findViewById(R.id.infoRating);
        infoPlot = (TextView) findViewById(R.id.infoPlot);
        filmBackdrop = (ImageView) findViewById(R.id.filmBackdrop);

        infoTitle.setText(film.getTitle());
        infoRelease.setText("Release: " + film.getRelease_date());
        infoAge.setText("Age Rating: " + "not available yet");
        infoLanguage.setText("Language: " + film.getOriginal_language());
        infoRuntime.setText("Runtime: " + "not available yet");

        genreList = film.getGenres();

        for (int i = 0; i < genreList.size(); i++){
            genres += genreList.get(i) + " ";
        }

        infoGenre.setText("Genres: " + genres);

        infoDirector.setText("Director: " + "not available yet");
        infoRating.setText("Rating: " + "not available yet");
        infoPlot.setText("Plot: " + film.getOverview());

        if(film.getBackdrop_path() != null) {
            Picasso.with(this).load("https://image.tmdb.org/t/p" + "/w500" + "/" + film.getBackdrop_path()).into(filmBackdrop);
        } else {
            Picasso.with(this).load("http://placehold.it/0x0").into(filmBackdrop);
        }

        reviewsTabBttn = (Button) findViewById(R.id.reviewsTabBttn);
        reviewsTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReviewListActivity.class);
                i.putExtra("FILM", film);
                startActivity(i);
            }
        });

        ticketsTabBttn = (Button) findViewById(R.id.ticketsTabBttn);
        ticketsTabBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PlaytimesActivity.class);
                i.putExtra("FILM", film);
                startActivity(i);
            }
        });
    }
}
