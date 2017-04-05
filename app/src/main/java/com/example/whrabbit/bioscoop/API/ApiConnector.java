package com.example.whrabbit.bioscoop.API;

import android.os.AsyncTask;

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
 * Created by Koen Kamman on 3-4-2017.
 */

public class ApiConnector extends AsyncTask<String, Void, JSONObject> {

    private TMDBConnectorListener listener;

    public ApiConnector(TMDBConnectorListener listener) {
        this.listener = listener;
    }

    @Override
    protected JSONObject doInBackground(String... urls) {

        BufferedReader reader = null;
        String response = "";
        JSONObject json = null;

        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            json = new JSONObject(response);

        } catch (MalformedURLException e) {
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

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject json) {

        if (json == null) {
            listener.onFilmsAvailable(null);
        } else {

            Film film;

            try {

                    JSONArray films = json.getJSONArray("results");

                if(!films.equals(null) || films.length() == 0) {

                    for (int i = 0; i < films.length(); i++) {

                        film = new Film();

                        film.setPoster_path(getStringFromJSON(films, "poster_path", i));
                        film.setOverview(getStringFromJSON(films, "overview", i));
                        film.setRelease_date(getStringFromJSON(films, "release_date", i));
                        film.setOriginal_title(getStringFromJSON(films, "original_title", i));
                        film.setOriginal_language(getStringFromJSON(films, "original_language", i));
                        film.setTitle(getStringFromJSON(films, "title", i));
                        film.setBackdrop_path(getStringFromJSON(films, "backdrop_path", i));

                        if (films.getJSONObject(i).has("id") && !films.getJSONObject(i).isNull("id")) {
                            int result = films.getJSONObject(i).getInt("id");
                            film.setId(result);
                        }

                        listener.onFilmsAvailable(film);

                    }
                } else {
                    listener.onFilmsAvailable(null);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String getStringFromJSON(JSONArray films, String q, int index) {
        String result = null;

        try {
            if (films.getJSONObject(index).has(q) && !films.getJSONObject(index).isNull(q)) {
                result = films.getJSONObject(index).getString(q);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }
}
