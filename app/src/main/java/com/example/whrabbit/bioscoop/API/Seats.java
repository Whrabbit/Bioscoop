package com.example.whrabbit.bioscoop.API;

/**
 * Created by Whrabbit on 4/2/2017.
 */

public class Seats {

    /**
     * seat full name
     */
    public String seatName;
    /**
     * row Name
     */
    public String rowName;
    /**
     * row index
     */
    public int row;
    /**
     * column index
     */
    public int column;

    /**
     * seat status:1：available，0：sold，-1：unavailable
     */
    public int status;

    public String getSeatName() {
        return seatName;
    }

    public String getRowName() {
        return rowName;
    }

    public boolean isSold() {
        return status == 0;
    }

    public boolean isOnSale() {
        return status == 1;
    }

    public boolean isSelected() {
        return status == 2;
    }

    public void setSold() {
        status = 0;
    }
    public void setOnSale() {
        status = 1;
    }

    public void setSelected() {
        status = 2;
    }


}
