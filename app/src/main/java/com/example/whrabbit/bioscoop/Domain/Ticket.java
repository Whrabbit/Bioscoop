package com.example.whrabbit.bioscoop.Domain;

/**
 * Created by Mika Krooswijk on 5-4-2017.
 */

public class Ticket {

    private String buyDate, username;
    private int ticketId, amountOfTickets, filmId, price;

    public Ticket() {
        this.buyDate = "";
        this.username = "";
        this.ticketId = 0;
        this.amountOfTickets = 0;
        this.filmId = 0;
        this.price = 0;
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
