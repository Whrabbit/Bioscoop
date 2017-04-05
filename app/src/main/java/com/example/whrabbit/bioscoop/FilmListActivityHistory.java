package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.whrabbit.bioscoop.API.DetailApiConnector;
import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.API.FilmAdapter;
import com.example.whrabbit.bioscoop.API.TMDBConnectorListener;
import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.RecentWatch;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FilmListActivityHistory extends AppCompatActivity implements TMDBConnectorListener {

    private ListView historyListView;
    private Button teZienBttn;
    private FilmAdapter filmAdapter;
    private ArrayList<Film> films;
    private ArrayList<RecentWatch> history;
    private String username;

    private DatabaseHandler dbh;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list_history);

        dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);

        films = new ArrayList<>();

        teZienBttn = (Button) findViewById(R.id.teZienBttn);
        teZienBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FilmListActivity.class);
                startActivity(i);
            }
        });

        historyListView = (ListView) findViewById(R.id.historyListView);
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), FilmInfoActivity.class);
                i.putExtra("FILM", films.get(position));
                startActivity(i);
            }
        });

        username = ((MyApplication) this.getApplicationContext()).getSignedInUsername();

        filmAdapter = new FilmAdapter(getApplicationContext(), films);
        historyListView.setAdapter(filmAdapter);

        history = new ArrayList<>();
        history = dbh.getRecentWatch(username);

        getFilm();

    }

    public void getFilm() {

        int FilmID;

        DetailApiConnector connector = new DetailApiConnector(this);

        if (history.size() > 0) {

            FilmID = history.get(index).getFilmID();

            String url = "https://api.themoviedb.org/3/movie/" + FilmID +
                    "?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US&append_to_response=release_dates";

            String[] urls = new String[]{url};
            connector.execute(urls);

        }


    }

    @Override
    public void onFilmsAvailable(Film film) {
        if (film != null) {
            films.add(film);
        } else {
            films.clear();
        }
        filmAdapter.notifyDataSetChanged();
        if (index < history.size() - 1) {
            index++;
            getFilm();
        }
    }
}
