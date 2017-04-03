package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mark on 2-4-2017.
 */

public class ReviewActivity extends AppCompatActivity {

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
    }
}
