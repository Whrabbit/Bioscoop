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

public class DetailApiConnector extends AsyncTask<String, Void, JSONObject> {

    private TMDBConnectorListener listener;

    public DetailApiConnector(TMDBConnectorListener listener) {
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

                film = new Film();

                film.setPoster_path(getStringFromJSON(json, "poster_path"));
                film.setOverview(getStringFromJSON(json, "overview"));
                film.setRelease_date(getStringFromJSON(json, "release_date"));
                film.setOriginal_title(getStringFromJSON(json, "original_title"));
                film.setOriginal_language(getStringFromJSON(json, "original_language"));
                film.setTitle(getStringFromJSON(json, "title"));
                film.setBackdrop_path(getStringFromJSON(json, "backdrop_path"));

                if (json.has("id") && !json.isNull("id")) {
                    int result = json.getInt("id");
                    film.setId(result);
                }

                if (json.has("runtime") && !json.isNull("runtime")) {
                    int result = json.getInt("runtime");
                    film.setRuntime(result);
                }

                if (json.has("release_dates") && !json.isNull("release_dates")) {
                    JSONArray results = json.getJSONObject("release_dates").getJSONArray("results");

                    for (int x = 0; x < results.length(); x++) {
                        if (results.getJSONObject(x).getString("iso_3166_1").equals("US")) {
                            JSONArray dates = results.getJSONObject(x).getJSONArray("release_dates");

                            for (int y = 0; y < dates.length(); y++) {
                                if (!dates.getJSONObject(y).getString("certification").equals("")) {
                                    film.setCertification(dates.getJSONObject(y).getString("certification"));
                                }
                            }
                        }
                    }
                }

                if (json.has("genres") && !json.isNull("genres")) {
                    JSONArray results = json.getJSONArray("genres");
                    ArrayList<String> genres = new ArrayList<>();

                    for (int x = 0; x < results.length(); x++) {
                        genres.add(results.getJSONObject(x).getString("name"));
                    }

                    film.setGenres(genres);
                }

                listener.onFilmsAvailable(film);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String getStringFromJSON(JSONObject json, String q) {
        String result = null;

        try {
            if (json.has(q) && !json.isNull(q)) {
                result = json.getString(q);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }
}
