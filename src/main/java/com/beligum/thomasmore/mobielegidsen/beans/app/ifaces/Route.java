package com.beligum.thomasmore.mobielegidsen.beans.app.ifaces;

import com.beligum.thomasmore.mobielegidsen.beans.app.MapLocation;
import com.beligum.thomasmore.mobielegidsen.endpoints.WeatherEndpoint;

import java.net.URI;
import java.util.Map;

/**
 * Created by bram on 4/13/16.
 */
public interface Route
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    String getName();
    String getSlug();
    String getIntroduction();
    URI getPoster();
    URI[] getImages();
    String getDescription();
    Region getRegion();
    MapLocation getLocation();
    Map<String, Stop> getStops();
    String getColor();
    Accessibility[] getAccessibility();
    String getLength();
    String getDuration();
    SurroundingEntry[] getSurroundingEntries();
    WeatherEndpoint.ForecastData getWeatherForecast();

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
