package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.ReviewAdapter;
import com.example.whrabbit.bioscoop.Domain.Ticket;
import com.example.whrabbit.bioscoop.Domain.TicketAdapter;

import java.util.ArrayList;

public class TicketListActivity extends AppCompatActivity {

    ListView ticketListView;

    private TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        final DatabaseHandler db = new DatabaseHandler(getApplicationContext(), null, null, 1);

        ArrayList<Ticket> tickets = new ArrayList<>();

        tickets = db.getTicket(((MyApplication) getApplicationContext()).getSignedInUsername());

        ticketAdapter = new TicketAdapter(getApplicationContext(), tickets);
        ticketListView.setAdapter(ticketAdapter);

        ticketAdapter.notifyDataSetChanged();

        ticketListView = (ListView) findViewById(R.id.ticketListView);
        ticketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetailedTicketActivity.class);
                startActivity(i);
            }
        });
    }

}
