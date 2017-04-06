package com.example.whrabbit.bioscoop;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.Domain.Customer;
import com.example.whrabbit.bioscoop.Domain.Ticket;

public class DetailedTicketActivity extends AppCompatActivity {

    private TextView detailTicketTitle, detailBuyerName, detailBuyerAge, detailBuyerTicketAmount;
    private Bundle extra;
    private Ticket ticket;

    private String username;
    private Customer user;

    private DatabaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_ticket);

        dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);

        detailTicketTitle = (TextView) findViewById(R.id.detailTicketTitle);
        detailBuyerName = (TextView) findViewById(R.id.detailBuyerName);
        //detailBuyerAge = (TextView) findViewById(R.id.detailBuyerAge);
        detailBuyerTicketAmount = (TextView) findViewById(R.id.detailBuyerSeat);

        detailTicketTitle.setText("");
        detailBuyerName.setText("");
        //detailBuyerAge.setText("");
        detailBuyerTicketAmount.setText("");
        username = "";

        extra = getIntent().getExtras();
        ticket = extra.getParcelable("t");
        String title = extra.getString("title");

        username = ((MyApplication) this.getApplicationContext()).getSignedInUsername();
        user = dbh.getCustomer(username);

        detailTicketTitle.setText(title);
        detailBuyerName.setText(ticket.getUsername());
        detailBuyerTicketAmount.setText(getString(R.string.detailBuyerSeat) + " " + ticket.getAmountOfTickets());
        //detailBuyerAge.setText("" + user.getAge());



    }
}
