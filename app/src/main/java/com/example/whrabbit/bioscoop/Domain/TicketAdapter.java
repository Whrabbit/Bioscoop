package com.example.whrabbit.bioscoop.Domain;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.whrabbit.bioscoop.DatabaseLayer.DatabaseHandler;
import com.example.whrabbit.bioscoop.R;

import java.util.ArrayList;

/**
 * Created by Mika Krooswijk on 5-4-2017.
 */

public class TicketAdapter extends ArrayAdapter<Ticket> {

    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
        super(context, 0, tickets);
    }

    public View getView(int position, View convertView, ViewGroup parent) {




        Ticket ticket = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ticket_row, parent, false);

        }



        TextView filmTicketTitle = (TextView) convertView.findViewById(R.id.filmTicketTitle);
        filmTicketTitle.setText( "" +  ticket.getTitle());


        TextView filmTicketAmount = (TextView) convertView.findViewById(R.id.filmTicketAmount);
        filmTicketAmount.setText("" + ticket.getAmountOfTickets());


        TextView filmTicketDate = (TextView) convertView.findViewById(R.id.filmTicketDate);
        filmTicketDate.setText("" + ticket.getBuyDate());






        return convertView;
    }
}


