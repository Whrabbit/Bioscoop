package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.API.ApiConnector;
import com.example.whrabbit.bioscoop.API.DetailApiConnector;
import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.API.TMDBConnectorListener;
import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.RecentWatch;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by mark on 2-4-2017.
 */

public class FilmInfoActivity extends AppCompatActivity implements TMDBConnectorListener{

    private Button ticketsTabBttn, reviewsTabBttn, historyBttn;
    private TextView infoTitle, infoRelease, infoAge, infoLanguage, infoRuntime, infoGenre, infoDirector, infoRating, infoPlot;
    private ImageView filmBackdrop;
    private String genres;
    private Bundle extra;
    private Film film;
    private ArrayList genreList;

    private int FilmID;
    private String username;

    private DatabaseHandler dbh;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info);

        extra = getIntent().getExtras();
        film = extra.getParcelable("FILM");
        genres = "";
        genreList = new ArrayList<>();

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
        infoAge.setText("Age Rating: " + "-");
        infoLanguage.setText("Language: " + film.getOriginal_language());
        infoRuntime.setText("Runtime: " + "-");

        infoGenre.setText("Genres: " + genres);

        infoDirector.setText("Director: " + "-");
        infoRating.setText("Rating: " + "-");
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

        historyBttn = (Button) findViewById(R.id.historyBttn);
        historyBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);

                RecentWatch recentWatch = new RecentWatch();
                FilmID = film.getId();
                username = ((MyApplication) getBaseContext().getApplicationContext()).getSignedInUsername();

                recentWatch.setCustomerUsername(username);
                recentWatch.setFilmID(FilmID);

                dbh.addRecentWatch(recentWatch);

                //Intent i = new Intent(getApplicationContext(), FilmListActivityHistory.class);
                //i.putExtra("FILM", film);
            }
        });

        getFilmInfo();


    }

    public void getFilmInfo() {

        DetailApiConnector connector = new DetailApiConnector(this);

        String url = "https://api.themoviedb.org/3/movie/" + film.getId() + "?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US&append_to_response=release_dates";

            String[]urls = new String[]{url};
            connector.execute(urls);

    }

    @Override
    public void onFilmsAvailable(Film film) {
        ArrayList<String> genres = film.getGenres();
        String rating;
        int runtime;

        if (film.getCertification().equals("")){
            infoAge.setText("Age Rating: " + "No Rating Found");
        } else {
            infoAge.setText("Age Rating: " + film.getCertification());
        }

        String str = "";
        for ( String genre : genres) {
            str += genre + " ";
        }
        infoGenre.setText("Genre: " + str);


        infoRuntime.setText("Runtime: " + film.getRuntime() + " min");
    }
}
