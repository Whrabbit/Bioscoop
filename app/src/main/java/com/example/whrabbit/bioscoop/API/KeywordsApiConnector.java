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

/**
 * Created by Koen Kamman on 4-4-2017.
 */

public class KeywordsApiConnector extends AsyncTask<String, Void, JSONObject> {

    private KeywordConnectorListener listener;

    public KeywordsApiConnector(KeywordConnectorListener listener) {
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

        if (json.equals(null)) {
            listener.onKeywordAvailable(null, -1);
        } else {
            try {

                    JSONArray keywords = json.getJSONArray("results");

                    for (int i = 0; i < keywords.length(); i++) {
                        String keyword = keywords.getJSONObject(i).getString("name");
                        int keyword_id = keywords.getJSONObject(i).getInt("id");

                        listener.onKeywordAvailable(keyword, keyword_id);

                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
