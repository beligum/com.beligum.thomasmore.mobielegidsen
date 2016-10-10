package com.beligum.thomasmore.mobielegidsen.beans.app;

/**
 * Created by bram on 4/13/16.
 */
public class MapLocation
{
    private static final int DEFAULT_ZOOM_LEVEL = 17;
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private double latitude;
    private double longitude;
    private int zoomLevel;

    //-----CONSTRUCTORS-----
    public MapLocation(double latitude, double longitude, int zoomLevel)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoomLevel = zoomLevel;
    }
    public MapLocation(double latitude, double longitude)
    {
        this(latitude, longitude, DEFAULT_ZOOM_LEVEL);
    }

    //-----PUBLIC METHODS-----
    public double getLatitude()
    {
        return latitude;
    }
    public double getLongitude()
    {
        return longitude;
    }
    public int getZoomLevel()
    {
        return zoomLevel;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
