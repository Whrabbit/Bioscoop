package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FilmListActivityHistory extends AppCompatActivity {

    Button teZienBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list_history);

        teZienBttn = (Button) findViewById(R.id.teZienBttn);
        teZienBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FilmListActivity.class);
                startActivity(i);
            }
        });
    }
}
