package com.example.whrabbit.bioscoop.API;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Koen Kamman on 30-3-2017.
 */

public class Film implements Parcelable {

    private String poster_path, overview, release_date, original_title, original_language, title, backdrop_path;
    private int id;
    private ArrayList<Integer> genre_ids;
    private ArrayList<String> genres;

    public Film() {
        poster_path = null;
        overview = "";
        release_date = "";
        original_title = "";
        original_language = "";
        title = "";
        id = -1;
        backdrop_path = null;
        genre_ids = new ArrayList<>();
        genres = new ArrayList<>();
    }

    private Film(Parcel in) {
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(poster_path);
        out.writeString(overview);
        out.writeString(release_date);
        out.writeString(original_title);
        out.writeString(original_language);
        out.writeString(title);
        out.writeString(backdrop_path);
        out.writeInt(id);
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {

        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }

    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
