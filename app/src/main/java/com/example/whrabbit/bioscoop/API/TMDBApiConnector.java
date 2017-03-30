package com.example.whrabbit.bioscoop.API;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

}
