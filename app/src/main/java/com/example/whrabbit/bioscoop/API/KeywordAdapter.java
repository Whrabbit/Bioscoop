package com.example.whrabbit.bioscoop.API;

import android.content.Context;
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
 * Created by Koen Kamman on 4-4-2017.
 */

public class KeywordAdapter extends ArrayAdapter<String> {

    public KeywordAdapter(Context context, ArrayList<String> items) {
        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        String keyword = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.keyword_row, parent, false);
        }

        TextView keywordTV = (TextView) convertView.findViewById(R.id.keyword_id);

        keywordTV.setText(keyword);

        return convertView;
    }

}
