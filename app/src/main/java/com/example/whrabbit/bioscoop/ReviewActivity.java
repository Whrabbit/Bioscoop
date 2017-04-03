package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.Domain.Review;

/**
 * Created by mark on 2-4-2017.
 */

public class ReviewActivity extends AppCompatActivity {
    private TextView userName, userRealName;
    private EditText reviewBox;

    Button reviewSubmitBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        reviewSubmitBttn = (Button) findViewById(R.id.reviewSubmitBttn);
        reviewSubmitBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReviewListActivity.class);
                startActivity(i);
            }
        });

        userName = (TextView) findViewById(R.id.userName);
        userRealName = (TextView) findViewById(R.id.userRealName);

        reviewBox = (EditText) findViewById(R.id.reviewBox);

        Review r = new Review();
        r.setCustomerID(userName.getText().length());
        r.setCustomerName(userRealName.getText().toString());
        r.setReview(reviewBox.getText().toString());

        userName.setText("");
        userRealName.setText("");
        reviewBox.setText("");






    }
}
