package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.Ticket;

/**
 * Created by mark on 2-4-2017.
 */

public class TicketActivity extends AppCompatActivity {
    private TextView aantalTicketsTV, aantalStudentenTV, aantalKinderenTV, ticketPrijsNumber;
    private SeekBar kinderenBar, studentenBar;
    private int aantalTickets, aantalStudenten, aantalKinderen, ticketPrijs;
    private Spinner ticketAantalSpinner;
    private Film film;
    private Bundle extra;

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
                i.putExtra("AANTAL", aantalTickets);
                extra = getIntent().getExtras();
                film = extra.getParcelable("FILM");

                i.putExtra("FILM", film);
                i.putExtra("PRICE", ticketPrijs);
                startActivity(i);


                final DatabaseHandler dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);
                Ticket ticket = new Ticket();
                String username = ((MyApplication) getApplicationContext()).getSignedInUsername();
                Log.i("TAG", username);
                ticket.setUsername("");
                ticket.setAmountOfTickets(aantalTickets);
                ticket.setFilmId(film.getId());
                ticket.setPrice(ticketPrijs);
                ticket.setUsername(username);
                dbh.addTicket(ticket);

            }
        });



        aantalTicketsTV = (TextView) findViewById(R.id.ticketAantalView);
        aantalStudentenTV = (TextView) findViewById(R.id.ticketStudentView);
        aantalKinderenTV = (TextView) findViewById(R.id.ticketKindView);
        ticketPrijsNumber = (TextView) findViewById(R.id.ticketPrijsNumber);

        kinderenBar = (SeekBar) findViewById(R.id.ticketKindSeek);
        studentenBar = (SeekBar) findViewById(R.id.ticketStudentSeek);

        aantalTickets = 1;
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

                calcTicketPrice();

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

                calcTicketPrice();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ticketAantalSpinner = (Spinner) findViewById(R.id.ticketAantalSpinner);
        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, items);
        ticketAantalSpinner.setAdapter(adapter);
        ticketAantalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aantalTickets = ticketAantalSpinner.getSelectedItemPosition() + 1;
                aantalTicketsTV.setText("Aantal Tickets: " + aantalTickets);
                studentenBar.setMax(aantalTickets);
                kinderenBar.setMax(aantalTickets);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calcTicketPrice();

    }

    private void calcTicketPrice(){
        ticketPrijs = (aantalKinderen * 6) + (aantalStudenten * 8) + ((aantalTickets - (aantalStudenten + aantalKinderen)) * 10);
        ticketPrijsNumber.setText(getString(R.string.ticketPrijsNumber) + " " + ticketPrijs);
    }

}



