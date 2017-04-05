package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.Customer;
import com.example.whrabbit.bioscoop.Domain.Review;

/**
 * Created by mark on 2-4-2017.
 */

public class ReviewActivity extends AppCompatActivity {

    TextView userName, userRealName;
    EditText reviewBox;
    RatingBar ratingBar;
    Button reviewSubmitBttn;
    DatabaseHandler dbh;
    String username;

    private Bundle extra;
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);

        userName = (TextView) findViewById(R.id.userName);
        userRealName = (TextView) findViewById(R.id.userRealName);
        reviewBox = (EditText) findViewById(R.id.reviewBox);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        userName.setText("");
        userRealName.setText("");
        username = "";

        username = ((MyApplication) this.getApplicationContext()).getSignedInUsername();
        userName.setText(username);

        Customer customer;
        customer = dbh.getCustomer(username);
        userRealName.setText(customer.getFirstName());

        reviewSubmitBttn = (Button) findViewById(R.id.reviewSubmitBttn);
        reviewSubmitBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), ReviewListActivity.class);
//                startActivity(i);

                extra = getIntent().getExtras();
                film = extra.getParcelable("FILM");

                Review r = new Review();

                r.setReview(reviewBox.getText().toString());
                r.setFilmID(film.getId());
                r.setRating(ratingBar.getRating());
                r.setCustomerUsername(username);

                dbh.addReview(r);

                reviewBox.setText("");

            }
        });
    }
}
