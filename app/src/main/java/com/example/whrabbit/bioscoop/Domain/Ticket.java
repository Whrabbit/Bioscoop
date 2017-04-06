package com.example.whrabbit.bioscoop.Domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mika Krooswijk on 5-4-2017.
 */

public class Ticket implements Parcelable{

    private String buyDate, username, title;
    private int ticketId, amountOfTickets, filmId, price;

    public Ticket() {
        this.buyDate = "";
        this.username = "";
        //this.ticketId = 0;
        this.amountOfTickets = 0;
        this.filmId = 0;
        this.price = 0;
    }

    private Ticket(Parcel in) {
        buyDate = in.readString();
        username = in.readString();
        amountOfTickets = in.readInt();
        filmId = in.readInt();
        price = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(buyDate);
        out.writeString(username);
        out.writeInt(amountOfTickets);
        out.writeInt(filmId);
        out.writeInt(price);
    }

    public static final Parcelable.Creator<Ticket> CREATOR = new Parcelable.Creator<Ticket>() {

        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }

    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    public void setAmountOfTickets(int amountOfTickets) {
        this.amountOfTickets = amountOfTickets;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
