package com.example.pesaapp.data;

public class Constants {
    public final static String REGEX = "[a-zA-Z0-9]+@[A-z]+(.[A-z])+";
    public final static  String LOCALHOST = "https://pesaapp.herokuapp.com";

    //TODO android:queryBackground="@android:color/transparent"
    //TODO android:focusedByDefault="false"
	//TODO Let the user be able to see past events attended.
	//TODO User see events they have paid for.
	//TODO Maybe add a review for attended ???
	//TODO Time sorting. Check duka app for the tech brain project
	//TODO save lists in a draft like when user leaves
	//TODO Figure out how to keep user? shared preference. Loading page to offer short period to check
	//TODO Animate the heart
	//TODO For categories try framelayout put a view with different color. you can get in adapter and refence View. this view should have different color and some opacaity
	//TODO FOR TABLET VIEW PUT IMAGEVIEW ON RIGHT AND LEFT VERTICAL LINEARLAYOUT -DATE,TITLE,HOST AND DESCRIPTION . TAKE ADVANTAGE OF SPACE
	//TODO ANDROID MIME TYPE. TO ALLOW SPECIFIC DATA ITEMS . IN XYZ PROJECT
	//TODO Early Bird Day 1 (50 left)
	//TODO Advance  Day 1 (50 left)
	//TODO Change to listfragment. Has the option for empty adapter
	
//    public final static String LOCALHOSTIMAGES = "http://192.168.137.1:8080/loadImages/";
    public final static String LOCALHOSTIMAGES = "https://pesaapp.herokuapp.com/loadImages/";

    public final static String CATEGORY_KEY = "CATEGORY_KEY" ;
    public static final String LOADALL ="LOADALL";
    public final static String EVENT_EXTRA = "EVENTS_EXTRA";
    public final static String TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public final static String ACCOUNT = "ACCOUNT";
    public final static String DATABASENAME = "Eventsdb";
    public final static String FAVOURITES = "FAVOURITE";
    public final static String PREFNAME = "longname";
    public final static String PREFKEY = "prefkey";

}
