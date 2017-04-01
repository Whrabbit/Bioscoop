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
            for (int i = 0; i < urls.length; i++) {
                URL url = new URL(urls[i]);
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

        String poster_path, overview, release_date, original_title, original_language, title, backdrop_path;
        int id;
        JSONArray json_genre_ids;
        ArrayList<Integer> genre_ids;
        ArrayList<String> genreStrings;
        Film film;

        Map<Integer, String> map = new HashMap<>();

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

                        if (films.getJSONObject(i).has("id") && !films.getJSONObject(i).isNull("id")) {
                            id = films.getJSONObject(i).getInt("id");
                            film.setId(id);
                        }

                        if (films.getJSONObject(i).has("genre_ids") && !films.getJSONObject(i).isNull("genre_ids")) {
                            genre_ids = new ArrayList<>();
                            genreStrings = new ArrayList<>();

                            json_genre_ids = films.getJSONObject(i).getJSONArray("genre_ids");

                            for (int x = 0; x < json_genre_ids.length(); x++) {
                                genre_ids.add(json_genre_ids.getInt(x));
                            }

                            film.setGenre_ids(genre_ids);

                            for (Integer genre_id : genre_ids) {
                                if (map.containsKey(genre_id)){
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
            }

        }
    }

}
