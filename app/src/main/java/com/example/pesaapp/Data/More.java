package com.example.pesaapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class More implements Parcelable {

    private String title, host, photo, start_date, location, description, time_from, time_to, close_date, close_time ;
    private int id,early_price , advance_price;

    public More(String title, String host, String photo, String start_date, String location, String description,
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

    public More() {
    }

    public String[] dateformat (){
        return this.start_date.split("-");
    }
    public String dayconvertot(){
        String month = dateformat()[2];
        String mm = "";
        switch (month){
            case "01":
                mm= "JAN";
                break;
            case "02":
                mm = "FEB";
                break;
            case "03":
                mm =  "MCH";
            break;
            case "04":
                mm =  "APR";
            break;
            case "05":
                mm =  "MAY";
            break;
            case "06":
                mm =  "JUNE";
            break;

            case "07":
                mm =  "JLY";
            break;
            case "08":
                mm =  "AUG";
            break;
            case "09":
                mm =  "SEP";
            break;
            case "10":
                mm =  "OCT";
            break;
            case "11":
                mm =  "NOV";
            break;
            case "DEC":
                mm =  "DEC";
            break;
            default:
        }
        return mm;
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

    protected More(Parcel in) {
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

    public static final Parcelable.Creator<More> CREATOR = new Parcelable.Creator<More>() {
        @Override
        public More createFromParcel(Parcel source) {
            return new More(source);
        }

        @Override
        public More[] newArray(int size) {
            return new More[size];
        }
    };
}

