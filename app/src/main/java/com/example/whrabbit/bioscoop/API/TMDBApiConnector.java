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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koen Kamman on 30-3-2017.
 */

public class TMDBApiConnector extends AsyncTask<String, Void, ArrayList<JSONObject>> {

    private TMDBConnectorListener listener;

    public TMDBApiConnector(TMDBConnectorListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... urls) {

        BufferedReader reader = null;
        String response = "";
        String genre_url = "https://api.themoviedb.org/3/genre/movie/list?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=en-US";
        ArrayList<JSONObject> responseList = new ArrayList<>();

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add(genre_url);

        for (String url : urls) {
            urlList.add(url);
        }

        try {
            for (int i = 0; i < urlList.size(); i++) {
                URL url = new URL(urlList.get(i));
                URLConnection connection = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                }

                responseList.add(new JSONObject(response));
                response = "";

            }

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

        return responseList;

    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonList) {

        JSONArray json_genre_ids;
        ArrayList<Integer> genre_ids;
        ArrayList<String> genreStrings;
        Film film;

        Map<Integer, String> map = new HashMap<>();

        if (jsonList == null) {
            listener.onFilmsAvailable(null);
        } else {
            for (JSONObject jsonObject : jsonList) {

                try {

                    if (jsonObject.has("genres") && !jsonObject.isNull("genres")) {
                        JSONArray genres = jsonObject.getJSONArray("genres");

                        for (int i = 0; i < genres.length(); i++) {

                            if (genres.getJSONObject(i).has("name") && !genres.getJSONObject(i).isNull("name")) {
                                map.put(genres.getJSONObject(i).getInt("id"), genres.getJSONObject(i).getString("name"));
                            }
                        }
                    }

                    if (jsonObject.has("results") && !jsonObject.isNull("results")) {
                        JSONArray films = jsonObject.getJSONArray("results");

                        for (int i = 0; i < films.length(); i++) {

                            film = new Film();

                            film.setPoster_path(getJsonString(films, "poster_path", i));
                            film.setOverview(getJsonString(films, "overview", i));
                            film.setRelease_date(getJsonString(films, "release_date", i));
                            film.setOriginal_title(getJsonString(films, "original_title", i));
                            film.setOriginal_language(getJsonString(films, "original_language", i));
                            film.setTitle(getJsonString(films, "title", i));
                            film.setBackdrop_path(getJsonString(films, "backdrop_path", i));

                            if (films.getJSONObject(i).has("genre_ids") && !films.getJSONObject(i).isNull("genre_ids")) {

                                genre_ids = new ArrayList<>();
                                genreStrings = new ArrayList<>();
                                json_genre_ids = films.getJSONObject(i).getJSONArray("genre_ids");

                                for (int x = 0; x < json_genre_ids.length(); x++) {
                                    genre_ids.add(json_genre_ids.getInt(x));
                                }
                                film.setGenre_ids(genre_ids);

                                for (Integer genre_id : genre_ids) {
                                    if (map.containsKey(genre_id)) {
                                        genreStrings.add(map.get(genre_id));
                                    }
                                }
                                film.setGenres(genreStrings);

                            }

                            listener.onFilmsAvailable(film);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFilmsAvailable(null);
                }

            }
        }
    }

    private String getJsonString(JSONArray films, String q, int index) {
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
