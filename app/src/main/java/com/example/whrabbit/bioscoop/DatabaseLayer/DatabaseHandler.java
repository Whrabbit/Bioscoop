package com.example.whrabbit.bioscoop.DatabaseLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.CallSuper;
import android.util.Log;

import com.example.whrabbit.bioscoop.Domain.Customer;
import com.example.whrabbit.bioscoop.Domain.RecentWatch;
import com.example.whrabbit.bioscoop.Domain.Review;
import com.example.whrabbit.bioscoop.Domain.Room;
import com.example.whrabbit.bioscoop.Domain.Screening;
import com.example.whrabbit.bioscoop.Domain.Seat;
import com.example.whrabbit.bioscoop.Domain.Ticket;

import java.util.ArrayList;

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
        private static final String RECENTWATCH_COLUMN_USERNAME = "username";


    private static final String CUSTOMER_TABLE_NAME = "customer";

        private static final String CUSTOMER_COLUMN_USERNAME = "username";
        private static final String CUSTOMER_COLUMN_FIRSTNAME = "firstname";
        private static final String CUSTOMER_COLUMN_LASTNAME = "lastname";
        private static final String CUSTOMER_COLUMN_PASSWORD = "password";
        private static final String CUSTOMER_COLUMN_AGE = "age";
        private static final String CUSTOMER_COLUMN_CITY = "city";
        private static final String CUSTOMER_COLUMN_POSTALCODE = "postalcode";
        private static final String CUSTOMER_COLUMN_STREET = "street";
        private static final String CUSTOMER_COLUMN_GENDER = "gender";
        private static final String CUSTOMER_COLUMN_EMAIL = "email";

    private static final String REVIEW_TABLE_NAME = "review";

        private static final String REVIEW_COLUMN_RATING = "rating";
        private static final String REVIEW_COLUMN_REVIEW = "review";
        private static final String REVIEW_COLUMN_USERNAME = "username";
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



    private static final String TICKET_TABLE_NAME = "ticket";

        private static final String TICKET_COLUMN_TICKETID = "ticketId";
        private static final String TICKET_COLUMN_AMOUNTSEATS = "amountseats";
        private static final String TICKET_COLUMN_TICKETPRICE = "ticketprice";
        private static final String TICKET_COLUMN_BUYDATE = "buydate";
        private static final String TICKET_COLUMN_CUSTOMERUSERNAME = "customerId";
        private static final String TICKET_COLUMN_FILMID = "filmId";


    public DatabaseHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DB_NAME, factory, DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "creating database");
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + MOVIE_TABLE_NAME + "(" +
                MOVIE_COLUMN_ID + " INTEGER PRIMARY KEY," +
                MOVIE_COLUMN_ACTORS  + " TEXT," +
                MOVIE_COLUMN_TITLE  + " TEXT," +
                MOVIE_COLUMN_DIRECTOR  + " TEXT," +
                MOVIE_COLUMN_DISCR + " TEXT," +
                MOVIE_COLUMN_PRICE  + " INTEGER," +
                MOVIE_COLUMN_RATING  + " INTEGER" + ")"
                ;

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + CUSTOMER_TABLE_NAME + "(" +
                CUSTOMER_COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                CUSTOMER_COLUMN_FIRSTNAME + " TEXT," +
                CUSTOMER_COLUMN_LASTNAME + " TEXT," +
                CUSTOMER_COLUMN_AGE + " INTEGER," +
                CUSTOMER_COLUMN_CITY + " TEXT," +
                CUSTOMER_COLUMN_POSTALCODE + " TEXT," +
                CUSTOMER_COLUMN_STREET + " TEXT," +
                CUSTOMER_COLUMN_GENDER + " TEXT," +
                CUSTOMER_COLUMN_PASSWORD + " TEXT," +
                CUSTOMER_COLUMN_EMAIL + " TEXT" +
                ")"
                ;


        String CREATE_TICKET_TABLE = "CREATE TABLE " + TICKET_TABLE_NAME + "(" +
                TICKET_COLUMN_TICKETID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TICKET_COLUMN_AMOUNTSEATS + " INTEGER," +
                TICKET_COLUMN_BUYDATE + " TEXT," +
                TICKET_COLUMN_CUSTOMERUSERNAME + " TEXT," +
                TICKET_COLUMN_TICKETPRICE + " INTEGER," +
                TICKET_COLUMN_FILMID + " INTEGER," +

                "FOREIGN KEY (" + TICKET_COLUMN_CUSTOMERUSERNAME + ") REFERENCES " +
                CUSTOMER_TABLE_NAME + "(" + CUSTOMER_COLUMN_USERNAME + ")," +

                " FOREIGN KEY (" + TICKET_COLUMN_FILMID + ") REFERENCES " +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")" +
                ");"

                ;



        String CREATE_REVIEW_TABLE = "CREATE TABLE " + REVIEW_TABLE_NAME + "(" +
                REVIEW_COLUMN_USERNAME + " TEXT," +
                REVIEW_COLUMN_REVIEW + " TEXT," +
                REVIEW_COLUMN_FILMID + " INTEGER," +
                REVIEW_COLUMN_RATING + " INTEGER," +
                "PRIMARY KEY (" + REVIEW_COLUMN_FILMID + ", " + REVIEW_COLUMN_USERNAME + ")," +


                "FOREIGN KEY (" + REVIEW_COLUMN_USERNAME + ") REFERENCES " +
                CUSTOMER_TABLE_NAME + "(" + CUSTOMER_COLUMN_USERNAME + ")," +

                " FOREIGN KEY (" + REVIEW_COLUMN_FILMID + ") REFERENCES " +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")" +
                ");"


                ;

        String CREATE_ROOM_TABLE = "CREATE TABLE " + ROOM_TABLE_NAME + "(" +
                ROOM_COLUMN_ROOMID + " INTEGER PRIMARY KEY," +
                ROOM_COLUMN_TOTALSEATS + " INTEGER," +
                ROOM_COLUMN_AVAILIBLESEATS + " INTEGER" + ")"
                ;

        String CREATE_SEAT_TABLE = "CREATE TABLE " + SEAT_TABLE_NAME + "(" +
                SEAT_COLUMN_SEATID + " INTEGER PRIMARY KEY," +
                SEAT_COLUMN_SEATNUMEBER + " INTEGER," +
                SEAT_COLUMN_ISAVAILIBLE + " BOOLEAN," +
                SEAT_COLUMN_ROOMID + " INTEGER," +

                "FOREIGN KEY (" + SEAT_COLUMN_ROOMID + ") REFERENCES " +
                ROOM_TABLE_NAME + "(" + ROOM_COLUMN_ROOMID + ")" +
                ")"
                ;

        String CREATE_SCREENING_TABLE = "CREATE TABLE " + SCREENING_TABLE_NAME + "(" +
                SCREENING_COLUMN_ROOMID + " INTEGER," +
                SCREENING_COLUMN_FILMID + " INTEGER," +

                "FOREIGN KEY (" + SCREENING_COLUMN_FILMID + ") REFERENCES " +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")," +

                "FOREIGN KEY (" + SCREENING_COLUMN_ROOMID + ") REFERENCES " +
                ROOM_TABLE_NAME + "(" + ROOM_COLUMN_ROOMID + ")" +
                ");"
                ;



        String CREATE_RECENTWATCH_TABLE = "CREATE TABLE " + RECENT_WATCH_TABLE_NAME + "(" +
                RECENTWATCH_COLUMN_USERNAME + " TEXT," +
                RECENTWATCH_COLUMN_FILMID + " INTEGER," +
                "FOREIGN KEY (" + RECENTWATCH_COLUMN_FILMID + ") REFERENCES " +
                MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_ID + ")," +

                "FOREIGN KEY (" + RECENTWATCH_COLUMN_USERNAME + ") REFERENCES " +
                CUSTOMER_TABLE_NAME + "(" + CUSTOMER_COLUMN_USERNAME+ ")" +
                ")"
                ;



        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_REVIEW_TABLE);
        db.execSQL(CREATE_TICKET_TABLE);
        db.execSQL(CREATE_ROOM_TABLE);
        db.execSQL(CREATE_SEAT_TABLE);
        db.execSQL(CREATE_RECENTWATCH_TABLE);
        db.execSQL(CREATE_SCREENING_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCustomer(Customer customer){
        ContentValues values = new ContentValues();
        values.put(CUSTOMER_COLUMN_USERNAME, customer.getUsername() );
        values.put(CUSTOMER_COLUMN_FIRSTNAME, customer.getFirstName() );
        values.put(CUSTOMER_COLUMN_LASTNAME, customer.getLastName() );
        values.put(CUSTOMER_COLUMN_EMAIL, customer.getEmail() );
        values.put(CUSTOMER_COLUMN_CITY, customer.getCity() );
        values.put(CUSTOMER_COLUMN_POSTALCODE, customer.getPostalCode() );
        values.put(CUSTOMER_COLUMN_STREET, customer.getStreet() );
        values.put(CUSTOMER_COLUMN_PASSWORD, customer.getPassword() );


        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(CUSTOMER_TABLE_NAME, null, values);
        Log.i("TAG", "added");
        db.close();


    }

    public void addReview(Review review){
        ContentValues values = new ContentValues();
        values.put(REVIEW_COLUMN_RATING, review.getRating());
        values.put(REVIEW_COLUMN_REVIEW, review.getReview());
        values.put(REVIEW_COLUMN_USERNAME, review.getCustomerUsername());
        values.put(REVIEW_COLUMN_FILMID, review.getFilmID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(REVIEW_TABLE_NAME, null, values);
        db.close();
    }


    public ArrayList getTickets(String username){





        ArrayList<Ticket> tickets = new ArrayList<>();

        String query = "SELECT * FROM " + TICKET_TABLE_NAME + " WHERE " +
                TICKET_COLUMN_CUSTOMERUSERNAME + " =' " + username + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //cursor.moveToFirst();

        while(cursor.moveToNext() ) {

            Ticket ticket = new Ticket();

            ticket.setFilmId(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_FILMID)));
            ticket.setAmountOfTickets(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_AMOUNTSEATS)));
            ticket.setPrice(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_TICKETPRICE)));
            ticket.setTicketId(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_TICKETID)));

            Log.i("TAG", "tickets query");

            tickets.add(ticket);
        }

        db.close();



        return tickets;
    }


    public String getFilmTitle(int filmId){

        String title = "";
        String query = "SELECT " + MOVIE_COLUMN_TITLE + " FROM " + MOVIE_TABLE_NAME + " WHERE " +
                MOVIE_COLUMN_ID + " = " + filmId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //cursor.moveToFirst();
        Log.i("TAG", "before while");

        while(cursor.moveToNext() ) {
            title = (cursor.getString(cursor.getColumnIndex(CUSTOMER_COLUMN_FIRSTNAME)));
            Log.i("TAG", "got customer");
        };

        db.close();

        return title;

    }

    public ArrayList getReviews(int filmID) {

        ArrayList<Review> reviews = new ArrayList<>();

        String query = "SELECT * FROM " + REVIEW_TABLE_NAME + " WHERE " +
                REVIEW_COLUMN_FILMID + "=" + filmID;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //cursor.moveToFirst();

        while(cursor.moveToNext() ) {
            Review review = new Review();

            review.setCustomerUsername(cursor.getString(cursor.getColumnIndex(REVIEW_COLUMN_USERNAME)));
            review.setFilmID(cursor.getInt(cursor.getColumnIndex(REVIEW_COLUMN_FILMID)));
            review.setRating(cursor.getFloat(cursor.getColumnIndex(REVIEW_COLUMN_RATING)));
            review.setReview(cursor.getString(cursor.getColumnIndex(REVIEW_COLUMN_REVIEW)));

            reviews.add(review);
        }

        db.close();

        return reviews;
    }

    public void addRecentWatch(RecentWatch recentWatch){
        ContentValues values = new ContentValues();
        values.put(RECENTWATCH_COLUMN_USERNAME, recentWatch.getCustomerUsername());
        values.put(RECENTWATCH_COLUMN_FILMID, recentWatch.getFilmID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(RECENT_WATCH_TABLE_NAME, null, values);
        db.close();
    }

    public void addTicket(Ticket ticket){



        ContentValues values = new ContentValues();
        values.put(TICKET_COLUMN_AMOUNTSEATS, ticket.getAmountOfTickets());
        values.put(TICKET_COLUMN_BUYDATE, ticket.getBuyDate());
        values.put(TICKET_COLUMN_CUSTOMERUSERNAME, ticket.getUsername());
        values.put(TICKET_COLUMN_FILMID, ticket.getFilmId());
        values.put(TICKET_COLUMN_TICKETPRICE, ticket.getPrice());
        //values.put(TICKET_COLUMN_TICKETID, ticket.getTicketId());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TICKET_TABLE_NAME, null, values);
        Log.i("TAG", "inserted ticket" + ticket.getTicketId());

        db.close();
    }

    public void getRecentWatch(String username) {
        String query = "SELECT * FROM " + RECENT_WATCH_TABLE_NAME + " WHERE " +
                RECENTWATCH_COLUMN_USERNAME + "=" + "\"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(cursor.moveToNext() ) {
            Log.i(TAG, cursor.getString(   cursor.getColumnIndex(RECENTWATCH_COLUMN_USERNAME)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(RECENTWATCH_COLUMN_FILMID)));
            Log.i(TAG, "--------------------------------------------");
        }
        db.close();
    }

    public void addScreening(Screening screening){
        ContentValues values = new ContentValues();
        values.put(SCREENING_COLUMN_FILMID, screening.getFilmID());
        values.put(SCREENING_COLUMN_ROOMID, screening.getRoomID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(SCREENING_TABLE_NAME, null, values);
        db.close();


    }

    public void addRoom(Room room){
        ContentValues values = new ContentValues();
        values.put(ROOM_COLUMN_AVAILIBLESEATS, room.getAvailibleSeats());
        values.put(ROOM_COLUMN_TOTALSEATS, room.getTotalSeats());
        values.put(ROOM_COLUMN_ROOMID, room.getTotalSeats());


        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ROOM_TABLE_NAME, null, values);
        db.close();


    }

    public void addSeat(Seat seat){

        ContentValues values = new ContentValues();
        values.put(SEAT_COLUMN_ISAVAILIBLE, seat.isAvailible());
        values.put(SEAT_COLUMN_SEATNUMEBER, seat.getSeatNumber());
        values.put(SEAT_COLUMN_ROOMID, seat.getRoomID());
        values.put(SEAT_COLUMN_SEATID, seat.getSeatID());


        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(SEAT_TABLE_NAME, null, values);
        db.close();

    }

    public String getPassword(String username){

        String password = "";

        String query = "SELECT " + CUSTOMER_COLUMN_PASSWORD + " FROM " + CUSTOMER_TABLE_NAME + " WHERE " +
                CUSTOMER_COLUMN_USERNAME + "=" + "\"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);



        while(cursor.moveToNext() ) {

            password =  cursor.getString(cursor.getColumnIndex(CUSTOMER_COLUMN_PASSWORD));
            Log.i("LOG",cursor.getString(cursor.getColumnIndex(CUSTOMER_COLUMN_PASSWORD)));
        }

        db.close();

        return password;
    }

    public Customer getCustomer(String username){

        Customer customer = new Customer();

        String query = "SELECT " + CUSTOMER_COLUMN_FIRSTNAME + " FROM " + CUSTOMER_TABLE_NAME + " WHERE " +
                CUSTOMER_COLUMN_USERNAME + "=" + "\"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        //cursor.moveToFirst();
        Log.i("TAG", "before while");

        while(cursor.moveToNext() ) {
            customer.setFirstName(cursor.getString(cursor.getColumnIndex(CUSTOMER_COLUMN_FIRSTNAME)));
            Log.i("TAG", "got customer");
        };

        db.close();

     return customer;

    }
}
