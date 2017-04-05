package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whrabbit.bioscoop.API.DetailApiConnector;
import com.example.whrabbit.bioscoop.API.Film;
import com.example.whrabbit.bioscoop.API.TMDBConnectorListener;
import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.ReviewAdapter;
import com.example.whrabbit.bioscoop.Domain.Ticket;
import com.example.whrabbit.bioscoop.Domain.TicketAdapter;

import java.util.ArrayList;

public class TicketListActivity extends AppCompatActivity implements TMDBConnectorListener {

    ListView ticketListView;

    private TicketAdapter ticketAdapter ;
    private ArrayList<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);





        ticketListView = (ListView) findViewById(R.id.ticketListView);

        final DatabaseHandler db = new DatabaseHandler(getApplicationContext(), null, null, 1);

        tickets = new ArrayList<>();

        tickets = db.getTickets(((MyApplication) getApplicationContext()).getSignedInUsername());








        ticketAdapter = new TicketAdapter(getApplicationContext(), tickets);
        ticketListView.setAdapter(ticketAdapter);

        ticketAdapter.notifyDataSetChanged();


        ticketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetailedTicketActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onFilmsAvailable(Film film) {

    }
}
