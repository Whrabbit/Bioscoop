package com.example.whrabbit.bioscoop.API;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Koen Kamman on 30-3-2017.
 */

public class TMDBApiConnector extends AsyncTask<String, Void, String> {

    private TMDBConnectorListener listener;

    public TMDBApiConnector(TMDBConnectorListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {

        BufferedReader reader = null;
        String response = "";

        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while( (line = reader.readLine()) != null ) {
                response += line;
            }

        } catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {

        String poster_path, overview, release_date, original_title, original_language, title, backdrop_path;
        Film film;

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray films = jsonObject.getJSONArray("results");

            for (int i = 0; i < films.length(); i++) {

                film = new Film();

                //TODO: functionaliteit toevoegen voor genres

                if (films.getJSONObject(i).has("poster_path") && !films.getJSONObject(i).isNull("poster_path")) {
                    poster_path = films.getJSONObject(i).getString("poster_path");
                    film.setPoster_path(poster_path);
                }

                if (films.getJSONObject(i).has("overview") && !films.getJSONObject(i).isNull("overview")) {
                    overview = films.getJSONObject(i).getString("overview");
                    film.setOverview(overview);
                }

                if (films.getJSONObject(i).has("release_date") && !films.getJSONObject(i).isNull("release_date")) {
                    release_date = films.getJSONObject(i).getString("release_date");
                    film.setRelease_date(release_date);
                }

                if (films.getJSONObject(i).has("original_title") && !films.getJSONObject(i).isNull("original_title")) {
                    original_title = films.getJSONObject(i).getString("original_title");
                    film.setOriginal_title(original_title);
                }

                if (films.getJSONObject(i).has("original_language") && !films.getJSONObject(i).isNull("original_language")) {
                    original_language = films.getJSONObject(i).getString("original_language");
                    film.setOriginal_language(original_language);
                }

                if (films.getJSONObject(i).has("title") && !films.getJSONObject(i).isNull("title")) {
                    title = films.getJSONObject(i).getString("title");
                    film.setTitle(title);
                }

                if (films.getJSONObject(i).has("backdrop_path") && !films.getJSONObject(i).isNull("backdrop_path")) {
                    backdrop_path = films.getJSONObject(i).getString("backdrop_path");
                    film.setBackdrop_path(backdrop_path);
                }

                listener.onFilmsAvailable(film);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
