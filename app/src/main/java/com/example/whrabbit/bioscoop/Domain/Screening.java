package com.example.whrabbit.bioscoop.Domain;

/**
 * Created by Mika Krooswijk on 3-4-2017.
 */

public class Screening {

    private int FilmID, RoomID;

    public int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int filmID) {
        FilmID = filmID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }
}
