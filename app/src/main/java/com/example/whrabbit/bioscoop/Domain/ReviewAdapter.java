package com.example.whrabbit.bioscoop.Domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.R;

import java.util.ArrayList;

/**
 * Created by mark on 4-4-2017.
 */

public class ReviewAdapter extends ArrayAdapter<Review> {

    public ReviewAdapter(Context context, ArrayList<Review> reviews) {
        super(context, 0, reviews);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Review review = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_row, parent, false);

        }

        TextView reviewerName = (TextView) convertView.findViewById(R.id.reviewerName);
        reviewerName.setText( review.getCustomerUsername() );

        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        ratingBar.setRating( review.getRating() );

        TextView reviewText = (TextView) convertView.findViewById(R.id.reviewText);
        reviewText.setText( review.getReview() );

        return convertView;
    }
}
