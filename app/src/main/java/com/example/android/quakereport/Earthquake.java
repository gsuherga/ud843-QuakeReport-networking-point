package com.example.android.quakereport;

/**
 * Created by jesus on 30/05/17.
 */

public class Earthquake {

    // Place of the earthquake
    private String mplace;

    // Magnitude of the earthquake
    private double mMagnitude;

    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    //Web for more information about the eartquake
    private String mweb;

    /*
    * Create a new Earthquake data object.
    *
    * @param magnitude is the magnitude of the earthquake
    * @param place, the place when it took place.
    * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
   *  earthquake happened
    * */
    public Earthquake(double magnitude, String place, long timeInMilliseconds, String web) {
        mMagnitude = magnitude;
        mplace=place;
        mTimeInMilliseconds = timeInMilliseconds;
        mweb = web;

    }

    /**
     * Get the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the place of the earthquake
     */
    public String getplace (){
        return mplace;
    }

    /**
     * Return the time of the earthquake.
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /*
    Return the web with more information about the earthquake
     */

    public String getMweb(){
        return mweb;
    }
}
