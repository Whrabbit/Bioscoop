package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.API.ImageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectSeatsActivity extends AppCompatActivity {
    private Button payBtn;
    private int seat;
    private Map<Integer, Boolean> seatTakenMap;
    int aantalSeatsTaken = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seats);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        final int aantalTickets = getIntent().getExtras().getInt("AANTAL");


        //Create a new HashMap the size of the GridView, containing an Integer and Boolean.
        //The Integer matches the seat positions.
        //If the Boolean is true, the seat is taken.
        seatTakenMap = new HashMap<>();
        for (int i = 0; i < gridview.getCount(); i++){
            seatTakenMap.put(i, false);
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView imageView;
                imageView = (ImageView) v;

                //imageView.setImageResource(R.drawable.seat_selected);
                TextView tv = (TextView) findViewById(R.id.selected_seat);
                tv.setText(" " + position);
                seat = position;

                //Check whether the seat is taken or not, and change the image accordingly.
                //After changing the image, set the Boolean to true/false.
                if (!seatTakenMap.get(position)){
                    if (aantalSeatsTaken < aantalTickets) {
                        imageView.setImageResource(R.drawable.seat_selected);
                        seatTakenMap.put(position, true);
                        aantalSeatsTaken++;
                    }
                } else {
                    imageView.setImageResource(R.drawable.seat_sale);
                    seatTakenMap.put(position, false);
                    aantalSeatsTaken--;
                }

                payBtn = (Button) findViewById(R.id.payBtn);
                payBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
                        i.putExtras(getIntent().getExtras());
                        i.putExtra("SEAT", seat);
                        startActivity(i);



                    }
                });


            }
        });
    }
}

