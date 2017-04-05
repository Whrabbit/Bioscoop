package com.example.whrabbit.bioscoop;

import android.app.Application;

/**
 * Created by mark on 4-4-2017.
 */

public class MyApplication extends Application {

    private String SignedInUsername;

    public String getSignedInUsername() {
        return SignedInUsername;
    }

    public void setSignedInUsername(String SignedInUsername) {
        this.SignedInUsername = SignedInUsername;
    }
}
