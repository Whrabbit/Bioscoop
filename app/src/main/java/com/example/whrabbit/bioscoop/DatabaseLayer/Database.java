package com.example.whrabbit.bioscoop.DatabaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mika Krooswijk on 30-3-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
     private static final String TAG = "MovieDBHandler";

    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "movie.db";
    private static final String MOVIE_TABLE_NAME = "movie";
    private static final String RECENT_WATCH_TABLE_NAME = "recentwatch";
    private static final String CUSTOMER_TABLE_NAME = "customer";
    private static final String REVIEW_TABLE_NAME = "review";
    private static final String SCREENING_TABLE_NAME = "screening";
    private static final String ROOM_TABLE_NAME = "room";
    private static final String SEAT_TABLE_NAME = "seat";

    private static final String SEA_TABLE_NAME = "seat";

    public DatabaseHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DB_NAME, factory, DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_MOVIE_TABLE = "CREATE TABLE"
 + MOVIE_TABLE_NAME + "(";    }






}
