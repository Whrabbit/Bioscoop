package com.example.whrabbit.bioscoop.Movieseats;

/**
 * Created by Whrabbit on 3/31/2017.
 */

import java.io.Serializable;

public interface Seats extends Serializable{
    //  seat full name
    String getSeatName();

    //  row Name
    String getRowName();

    //  seat status:available
    boolean isOnSale();

    //  seat status:sold
    boolean isSold();

    //  seat status:selected
    boolean isSelected();
}
