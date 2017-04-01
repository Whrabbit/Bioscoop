package com.example.whrabbit.bioscoop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.API.FilmAdapter;
import com.example.whrabbit.bioscoop.API.TMDBApiConnector;
import com.example.whrabbit.bioscoop.API.TMDBConnectorListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FilmList extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TMDBConnectorListener {

    Spinner filterTypeSpinner, filterSpinner, sortBySpinner;
    ListView filmListView;
    FilmAdapter filmAdapter;
    ArrayList<Film> films;
    Button filmSearchBttn;
    EditText filmSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        films = new ArrayList<>();

        filterTypeSpinner = (Spinner) findViewById(R.id.filterTypeSpinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.filterTypeSpinnerStrings,android.R.layout.simple_spinner_item);
        filterTypeSpinner.setAdapter(adapter);
        filterTypeSpinner.setOnItemSelectedListener(this);

        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);
        ArrayAdapter a = ArrayAdapter.createFromResource(this, R.array.filterSpinnerStrings,android.R.layout.simple_spinner_item);
        filterSpinner.setAdapter(a);
        filterSpinner.setOnItemSelectedListener(this);

        sortBySpinner = (Spinner) findViewById(R.id.sortBySpinner);
        ArrayAdapter o = ArrayAdapter.createFromResource(this, R.array.sortBySpinnerStrings,android.R.layout.simple_spinner_item);
        sortBySpinner.setAdapter(o);
        sortBySpinner.setOnItemSelectedListener(this);

        filmSearchBar = (EditText) findViewById(R.id.filmSearchBar);

        filmSearchBttn = (Button) findViewById(R.id.filmSearchBttn);
        filmSearchBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProducts(filmSearchBar.getText().toString());
            }
        });

        filmListView = (ListView) findViewById(R.id.filmListView);
        filmAdapter = new FilmAdapter(getApplicationContext(), films);
        filmListView.setAdapter(filmAdapter);

    }

    //TODO: search functionaliteit toevoegen & placeholder URL vervangen
    public void getProducts(String search) {

        try {
            String encSearch = URLEncoder.encode(search, "UTF-8");

            films.clear();

            TMDBApiConnector connector = new TMDBApiConnector(this);
            String discover_url = "https://api.themoviedb.org/3/discover/movie?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
            String genre_url = "https://api.themoviedb.org/3/genre/movie/list?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US";

            //GENRE URL BEFORE DISCOVER URL
            String[] urls = new String[] {genre_url, discover_url};
            connector.execute(urls);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFilmsAvailable(Film film) {
        films.add(film);
        filmAdapter.notifyDataSetChanged();
    }
}
