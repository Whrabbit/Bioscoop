package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by mark on 2-4-2017.
 */

public class TicketActivity extends AppCompatActivity {
    private TextView aantalTicketsTV, aantalStudentenTV, aantalKinderenTV;
    private SeekBar kinderenBar, studentenBar;
    private int aantalTickets, aantalStudenten, aantalKinderen;

    Button ticketSeatBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketSeatBttn = (Button) findViewById(R.id.ticketSeatBttn);
        ticketSeatBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectSeatsActivity.class);
                startActivity(i);
            }
        });



        aantalTicketsTV = (TextView) findViewById(R.id.ticketAantalView);
        aantalStudentenTV = (TextView) findViewById(R.id.ticketStudentView);
        aantalKinderenTV = (TextView) findViewById(R.id.ticketKindView);

        kinderenBar = (SeekBar) findViewById(R.id.ticketStudentSeek);
        studentenBar = (SeekBar) findViewById(R.id.ticketKindSeek);

        aantalTickets = 8;
        aantalStudenten = 0;
        aantalKinderen = 0;

        aantalTicketsTV.setText("Aantal Tickets: " + aantalTickets);
        aantalStudentenTV.setText("Aantal Studenten: " + aantalStudenten);
        aantalKinderenTV.setText("Aantal Kinderen: " + aantalKinderen);


        kinderenBar.setMax(aantalTickets);
        kinderenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                aantalKinderen = i;
                aantalKinderenTV.setText("Aantal Kinderen: " + i);

                if ((aantalStudenten + aantalKinderen) > aantalTickets){
                    studentenBar.setProgress(aantalTickets - aantalKinderen);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        studentenBar.setMax(aantalTickets);
        studentenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                aantalStudenten = i;
                aantalStudentenTV.setText("Aantal Studenten: " + i);


                if ((aantalStudenten + aantalKinderen) > aantalTickets){
                    kinderenBar.setProgress(aantalTickets - aantalStudenten);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}



