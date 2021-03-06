package com.example.pesaapp.data;

import android.os.Parcel;
import android.os.Parcelable;
public class moreparceble implements Parcelable{

    private String host, photo, start_date, location, description, time_from, time_to, close_date, close_time ;

    private String title;
    private int id,early_price, advance_price;

    public moreparceble(String title, String host, String photo, String start_date, String location, String description,
                String time_from, String time_to, String close_date, String close_time, int id, int early_price, int advance_price) {
        this.title = title;
        this.host = host;
        this.photo = photo;
        this.start_date = start_date;
        this.location = location;
        this.description = description;
        this.time_from = time_from;
        this.time_to = time_to;
        this.close_date = close_date;
        this.close_time = close_time;
        this.id = id;
        this.early_price = early_price;
        this.advance_price = advance_price;
    }

    public moreparceble() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPhoto() {

        //tp return LOCALHOST/IMAGES+PHOTO

        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStart_date() {

        // this.date.split.at

        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to() {
        return time_to;
    }

    public void setTime_to(String time_to) {
        this.time_to = time_to;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEarly_price() {
        return early_price;
    }

    public void setEarly_price(int early_price) {
        this.early_price = early_price;
    }

    public int getAdvance_price() {
        return advance_price;
    }

    public void setAdvance_price(int advance_price) {
        this.advance_price = advance_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.host);
        dest.writeString(this.photo);
        dest.writeString(this.start_date);
        dest.writeString(this.location);
        dest.writeString(this.description);
        dest.writeString(this.time_from);
        dest.writeString(this.time_to);
        dest.writeString(this.close_date);
        dest.writeString(this.close_time);
        dest.writeInt(this.id);
        dest.writeInt(this.early_price);
        dest.writeInt(this.advance_price);
    }

    protected moreparceble(Parcel in) {
        this.title = in.readString();
        this.host = in.readString();
        this.photo = in.readString();
        this.start_date = in.readString();
        this.location = in.readString();
        this.description = in.readString();
        this.time_from = in.readString();
        this.time_to = in.readString();
        this.close_date = in.readString();
        this.close_time = in.readString();
        this.id = in.readInt();
        this.early_price = in.readInt();
        this.advance_price = in.readInt();
    }

    public static final Parcelable.Creator<moreparceble> CREATOR = new Parcelable.Creator<moreparceble>() {
        @Override
        public moreparceble createFromParcel(Parcel source) {
            return new moreparceble(source);
        }

        @Override
        public moreparceble[] newArray(int size) {
            return new moreparceble[size];
        }
    };

}
