package com.example.whrabbit.bioscoop.Domain;

/**
 * Created by Mika Krooswijk on 3-4-2017.
 */

public class Room {

    private int totalSeats, availibleSeats, roomID, filmID;

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailibleSeats() {
        return availibleSeats;
    }

    public void setAvailibleSeats(int availibleSeats) {
        this.availibleSeats = availibleSeats;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }
}
