package com.example.whrabbit.bioscoop.DatabaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mika Krooswijk on 30-3-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
     private static final String TAG = "MovieDBHandler";

    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "movie.db";



    private static final String MOVIE_TABLE_NAME = "movie";

        private static final String MOVIE_COLUMN_TITLE = "title";
        private static final String MOVIE_COLUMN_ACTORS = "actor";
        private static final String MOVIE_COLUMN_RATING = "rating";
        private static final String MOVIE_COLUMN_DISCR = "description";
        private static final String MOVIE_COLUMN_DIRECTOR = "director";
        private static final String MOVIE_COLUMN_ID = "_filmId";
        private static final String MOVIE_COLUMN_PRICE = "price";

    private static final String RECENT_WATCH_TABLE_NAME = "recentwatch";

        private static final String RECENTWATCH_COLUMN_FILMID = "filmId";
        private static final String RECENTWATCH_COLUMN_CUSTOMERID = "customerId";


    private static final String CUSTOMER_TABLE_NAME = "customer";

        private static final String CUSTOMER_COLUMN_FIRSTNAME = "firstname";
        private static final String CUSTOMER_COLUMN_LASTNAME = "lastname";
        private static final String CUSTOMER_COLUMN_AGE = "age";
        private static final String CUSTOMER_COLUMN_CITY = "city";
        private static final String CUSTOMER_COLUMN_POSTALCODE = "postalcode";
        private static final String CUSTOMER_COLUMN_STREET = "street";
        private static final String CUSTOMER_COLUMN_GENDER = "gender";
        private static final String CUSTOMER_COLUMN_CUSTOMERID = "_customerId";

    private static final String REVIEW_TABLE_NAME = "review";

        private static final String REVIEW_COLUMN_TITLE = "title";
        private static final String REVIEW_COLUMN_RATING = "rating";
        private static final String REVIEW_COLUMN_REVIEW = "review";
        private static final String REVIEW_COLUMN_REVIEWID = "_reviewId";
        private static final String REVIEW_COLUMN_CUSTOMERID = "customerId";
        private static final String REVIEW_COLUMN_FILMID = "filmId";

    private static final String SCREENING_TABLE_NAME = "screening";

        private static final String SCREENING_COLUMN_FILMID = "filmId";
        private static final String SCREENING_COLUMN_ROOMID = "roomId";

    private static final String ROOM_TABLE_NAME = "room";

        private static final String ROOM_COLUMN_TOTALSEATS = "totalSeats";
        private static final String ROOM_COLUMN_AVAILIBLESEATS = "availibleSeats";
        private static final String ROOM_COLUMN_ROOMID = "_roomId";


    private static final String SEAT_TABLE_NAME = "seat";

        private static final String SEAT_COLUMN_SEATID = "_seatId";
        private static final String SEAT_COLUMN_SEATNUMEBER = "seatNumber";
        private static final String SEAT_COLUMN_ISAVAILIBLE = "isAvailible";
        private static final String SEAT_COLUMN_ROOMID = "roomId";


    public DatabaseHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DB_NAME, factory, DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "creating database");
        String CREATE_MOVIE_TABLE = "CREATE TABLE" + MOVIE_TABLE_NAME + "(" +
                MOVIE_COLUMN_ID + "INTEGER PRIMATY KEY," +
                MOVIE_COLUMN_ACTORS  + "TEXT," +
                MOVIE_COLUMN_TITLE  + "TEXT," +
                MOVIE_COLUMN_DIRECTOR  + "TEXT," +
                MOVIE_COLUMN_DISCR + "TEXT," +
                MOVIE_COLUMN_PRICE  + "INTEGER," +
                MOVIE_COLUMN_RATING  + "INTEGER," + ")"
                ;

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE" + CUSTOMER_TABLE_NAME + "(" +
                CUSTOMER_COLUMN_CUSTOMERID + "INTEGR PRIMARY KEY" +
                CUSTOMER_COLUMN_FIRSTNAME + "TEXT" +
                CUSTOMER_COLUMN_LASTNAME + "TEXT" +
                CUSTOMER_COLUMN_AGE + "INTEGER" +
                CUSTOMER_COLUMN_CITY + "TEXT" +
                CUSTOMER_COLUMN_POSTALCODE + "TEXT" +
                CUSTOMER_COLUMN_STREET + "TEXT" +
                CUSTOMER_COLUMN_GENDER + "TEXT" + ")"
                ;
        String CREATE = "CREATE TABLE" + REVIEW_TABLE_NAME + "(" +
                REVIEW_COLUMN_CUSTOMERID + "INTEGR PRIMARY KEY" +
                REVIEW_COLUMN_REVIEW + "TEXT" +
                REVIEW_COLUMN_TITLE + "TEXT" +
                REVIEW_COLUMN_FILMID + "INTEGER" +
                REVIEW_COLUMN_RATING + "INTEGER" +

                "FOREIGN KEY" + REVIEW_COLUMN_CUSTOMERID + "REFERENCES" +
                CUSTOMER_TABLE_NAME + "(" + CUSTOMER_COLUMN_CUSTOMERID + ")" +

                "FOREIGN KEY" + REVIEW_COLUMN_FILMID + "REFERENCES" +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")" +
                ")"
                ;

        String CREATE_REVIEW_TABLE = "CREATE TABLE" + REVIEW_TABLE_NAME + "(" +
                REVIEW_COLUMN_CUSTOMERID + "INTEGR PRIMARY KEY" +
                REVIEW_COLUMN_REVIEW + "TEXT" +
                REVIEW_COLUMN_TITLE + "TEXT" +
                REVIEW_COLUMN_FILMID + "INTEGER" +
                REVIEW_COLUMN_RATING + "INTEGER" + ")"
                ;

        String CREATE_ROOM_TABLE = "CREATE TABLE" + ROOM_TABLE_NAME + "(" +
                ROOM_COLUMN_ROOMID + "INTEGR PRIMARY KEY" +
                ROOM_COLUMN_TOTALSEATS + "INTEGER" +
                ROOM_COLUMN_AVAILIBLESEATS + "INTEGER" + ")"
                ;

        String CREATE_SEAT_TABLE = "CREATE TABLE" + SEAT_TABLE_NAME + "(" +
                SEAT_COLUMN_SEATID + "INTEGR PRIMARY KEY" +
                SEAT_COLUMN_SEATNUMEBER + "INTEGER" +
                SEAT_COLUMN_ISAVAILIBLE + "BOOLEAN" +
                SEAT_COLUMN_ROOMID + "INTEGER" +

                "FOREIGN KEY" + SEAT_COLUMN_ROOMID + "REFERENCES" +
                ROOM_TABLE_NAME + "(" + ROOM_COLUMN_ROOMID + ")" +

                ")"
                ;

        String CREATE_SCREENING_TABLE = "CREATE TABLE" + SCREENING_TABLE_NAME + "(" +
                SCREENING_COLUMN_ROOMID + "INTEGER" +
                SCREENING_COLUMN_FILMID + "INTEGER" +

                "FOREIGN KEY" + SCREENING_COLUMN_FILMID + "REFERENCES" +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")" +

                "FOREIGN KEY" + SCREENING_COLUMN_ROOMID + "REFERENCES" +
                ROOM_TABLE_NAME + "(" + ROOM_COLUMN_ROOMID + ")" +

                ")"
                ;

        String CREATE_RECENTWATCH_TABLE = "CREATE TABLE" + RECENT_WATCH_TABLE_NAME + "(" +
                RECENTWATCH_COLUMN_CUSTOMERID + "INTEGER" +
                RECENTWATCH_COLUMN_FILMID + "INTEGER" +
                "FOREIGN KEY" + RECENTWATCH_COLUMN_FILMID + "REFERENCES" +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")" +

                "FOREIGN KEY" + RECENTWATCH_COLUMN_CUSTOMERID + "REFERENCES" +
                CUSTOMER_TABLE_NAME + "(" + CUSTOMER_COLUMN_CUSTOMERID+ ")" +

                ")"


                ;


        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_REVIEW_TABLE);
        db.execSQL(CREATE_ROOM_TABLE);
        db.execSQL(CREATE_SEAT_TABLE);
        db.execSQL(CREATE_RECENTWATCH_TABLE);
        db.execSQL(CREATE_SCREENING_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addMovie(){

    }




}
