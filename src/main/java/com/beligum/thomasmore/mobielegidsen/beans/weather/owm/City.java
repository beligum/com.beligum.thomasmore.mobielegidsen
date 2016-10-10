package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by bram on 6/14/16.
 *
 * See http://openweathermap.org/forecast5#parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private Integer id;
    private String name;
    private Coordinates coord;
    private String country;
    private Integer population;

    //-----CONSTRUCTORS-----
    public City()
    {
    }

    //-----PUBLIC METHODS-----
    public Integer getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public Coordinates getCoord()
    {
        return coord;
    }
    public String getCountry()
    {
        return country;
    }
    public Integer getPopulation()
    {
        return population;
    }
    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
