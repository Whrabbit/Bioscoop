package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.Review;

/**
 * Created by mark on 2-4-2017.
 */

public class ReviewActivity extends AppCompatActivity {
    TextView userName, userRealName, reviewBox;
    //EditText reviewBox;
    Button reviewSubmitBttn;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        userName = (TextView) findViewById(R.id.reviewerName);
        userRealName = (TextView) findViewById(R.id.userRealName);
        reviewBox = (TextView) findViewById(R.id.reviewText);

        db = new DatabaseHandler(getApplicationContext(), null, null, 1);

        reviewSubmitBttn = (Button) findViewById(R.id.reviewSubmitBttn);
        reviewSubmitBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), ReviewListActivity.class);
//                startActivity(i);
                Review r = new Review();
                //r.setCustomerID(userName.getText().length());
                //r.setCustomerName(userRealName.getText().toString());
                r.setReview(reviewBox.getText().toString());

                db.addReview(r);

                //userName.setText("");
                //userRealName.setText("");
                reviewBox.setText("");

            }
        });
    }
}
