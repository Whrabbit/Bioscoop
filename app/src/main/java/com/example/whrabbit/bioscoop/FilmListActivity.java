package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.API.FilmAdapter;
import com.example.whrabbit.bioscoop.API.ApiConnector;
import com.example.whrabbit.bioscoop.API.TMDBConnectorListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FilmListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TMDBConnectorListener {

    static final int PICK_FILTERS_REQUEST = 1;

    Spinner filterTypeSpinner, filterSpinner, sortBySpinner;
    ListView filmListView;
    FilmAdapter filmAdapter;
    ArrayList<Film> films;
    ArrayList<Integer> keyword_ids;
    Button filmSearchBttn, bekekenBttn;
    EditText filmSearchBar;
    String sortBy = "";
    String searchType = "";

    Button discoverBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        films = new ArrayList<>();
        keyword_ids = new ArrayList<>();

        discoverBtn = (Button) findViewById(R.id.discoverBtnID);

        discoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FilterActivity.class);
                startActivityForResult(i, PICK_FILTERS_REQUEST);
            }
        });

        sortBySpinner = (Spinner) findViewById(R.id.sortBySpinner);
        ArrayAdapter o = ArrayAdapter.createFromResource(this, R.array.sortBySpinnerStrings, android.R.layout.simple_spinner_item);
        sortBySpinner.setAdapter(o);
        sortBySpinner.setOnItemSelectedListener(this);

        filmSearchBar = (EditText) findViewById(R.id.filmSearchBar);

        filmSearchBttn = (Button) findViewById(R.id.filmSearchBttn);
        filmSearchBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchType = "search";
                getFilms(filmSearchBar.getText().toString());
            }
        });

        bekekenBttn = (Button) findViewById(R.id.bekekenBttn);
        bekekenBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FilmListActivityHistory.class);
                startActivity(i);
            }
        });

        filmListView = (ListView) findViewById(R.id.filmListView);
        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), FilmInfoActivity.class);
                i.putExtra("FILM", films.get(position));
                startActivity(i);
            }
        });

        filmAdapter = new FilmAdapter(getApplicationContext(), films);
        filmListView.setAdapter(filmAdapter);

    }

    //TODO: Construct url based on search and filters
    public void getFilms(String search) {
        String url = "";


        if (!search.equals("") || searchType.equals("discover")) {
            films.clear();
            ApiConnector connector = new ApiConnector(this);

            try {
                String encSearch = URLEncoder.encode(search, "UTF-8");

                films.clear();

                if (searchType.equals("discover")) {
                    String keywords = "&with_keywords=";

                    if (!keyword_ids.equals(null)) {
                        for (Integer id : keyword_ids) {
                            keywords += id + ",";
                        }
                    }

                    url = "https://api.themoviedb.org/3/discover/movie?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US&sort_by=" + sortBy + "&include_adult=false&include_video=false&page=1&with_release_type=2|3" + keywords;
                } else if (searchType.equals("search")) {
                    url = "https://api.themoviedb.org/3/search/movie?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US&query=" + encSearch + "&page=1&include_adult=false";
                }

                String[] urls = new String[]{url};
                connector.execute(urls);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        searchType = "discover";
        if (parent.equals(sortBySpinner)) {
            String text = parent.getSelectedItem().toString();
            sortBy = text;
            Log.i("test", sortBy);
            getFilms("");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFilmsAvailable(Film film) {
        if (film != null) {
            films.add(film);
        } else {
            films.clear();
        }
        filmAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILTERS_REQUEST) {
            if (resultCode == RESULT_OK) {
                ArrayList<Integer> result = data.getExtras().getIntegerArrayList("KEYWORD_IDS");
                keyword_ids = result;
                searchType = "discover";
                getFilms("");
                filmAdapter.notifyDataSetChanged();
            }
        }
    }
}
