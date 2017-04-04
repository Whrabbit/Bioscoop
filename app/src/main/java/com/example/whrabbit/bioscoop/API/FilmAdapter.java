package com.example.whrabbit.bioscoop.API;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Koen Kamman on 31-3-2017.
 */

public class FilmAdapter extends ArrayAdapter<Film> {

    public FilmAdapter(Context context, ArrayList<Film> items) {
        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Film item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.film_row, parent, false);
        }

        ImageView filmImage = (ImageView) convertView.findViewById(R.id.filmImage);
        TextView filmTitle = (TextView) convertView.findViewById(R.id.filmTitle);
        TextView filmYear = (TextView) convertView.findViewById(R.id.filmYear);

        if(item.getPoster_path() != null) {
            Picasso.with(getContext()).load("https://image.tmdb.org/t/p" + "/w342" + "/" + item.getPoster_path()).into(filmImage);
        } else {
            Picasso.with(getContext()).load("http://placehold.it/0x0").into(filmImage);
        }
        filmTitle.setText(item.getTitle());
        filmYear.setText(item.getRelease_date());

        return convertView;
    }

}
