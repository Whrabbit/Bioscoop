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
import com.example.whrabbit.bioscoop.API.Seats;

import java.util.List;

public class SelectSeatsActivity extends AppCompatActivity {
    public List<Seats> selectedSeats;
    private static final int MAX_SEATS = 5;
    private Button payBtn;

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
                Seats seatMo = null;
                imageView.setImageResource(R.drawable.seat_selected);
                TextView tv = (TextView) findViewById(R.id.selected_seat);
                tv.setText(" " + position);

                payBtn = (Button) findViewById(R.id.payBtn);
                payBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
                        startActivity(i);
                    }
                });





//                if (seats != null) {
//                        imageView.setImageResource(R.drawable.seat_selected);
//                    } else seats = null; {
//                        imageView.setImageResource(R.drawable.seat_sale);
//                    }
//                }

//                if (seatMo != null) {
//                    if (seatMo.isOnSale()) {
//                        if (selectedSeats.size() < MAX_SEATS) {
//                            seatMo.setSelected();
//                            selectedSeats.add(seatMo);
//                            imageView.setImageResource(R.drawable.seat_selected);
//                        } else {
//                            Toast.makeText(SelectSeats.this, "" + MAX_SEATS, Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else if (seatMo.isSelected()) {
//                        seatMo.setOnSale();
//                        selectedSeats.remove(seatMo);
//                        imageView.setImageResource(R.drawable.seat_sale);
//
//                    }
//                }

            }
        });
    }
}


//    public boolean onClickSeat(int row, int column, BaseSeatMo seat) {
//        Seats seatMo;
//
//
//    }
