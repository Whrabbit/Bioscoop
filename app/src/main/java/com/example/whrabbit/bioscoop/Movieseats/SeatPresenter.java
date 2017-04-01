package com.example.whrabbit.bioscoop.Movieseats;

/**
 * Created by Whrabbit on 3/31/2017.
 */

public interface SeatPresenter {
    /**
     return true : invalidate view
     false: do nothing
     */
    boolean onClickSeat(int row, int column, Seats seat);
}

