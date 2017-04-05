package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.API.ImageAdapter;

public class SelectSeatsActivity extends AppCompatActivity {
    private Button payBtn;
    private int seat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seats);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ImageView imageView;
                imageView = (ImageView) v;

                //imageView.setImageResource(R.drawable.seat_selected);
                TextView tv = (TextView) findViewById(R.id.selected_seat);
                tv.setText(" " + position);
                seat = position;

                boolean flag=false;

                if (!flag) {
                    imageView.setImageResource(R.drawable.seat_selected);
                    flag=true;
                }
                else {
                    imageView.setImageResource(R.drawable.seat_sale);
                    flag=false;
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

