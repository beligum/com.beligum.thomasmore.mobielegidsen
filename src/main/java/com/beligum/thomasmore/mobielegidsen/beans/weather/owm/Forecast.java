package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bram on 6/14/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private Long creationStamp;
    private City city;
    private Integer cnt;
    private List<WeatherData> list;

    //-----CONSTRUCTORS-----
    public Forecast()
    {
        this.creationStamp = System.currentTimeMillis();
    }

    //-----PUBLIC METHODS-----
    public Long getCreationStamp()
    {
        return creationStamp;
    }
    public City getCity()
    {
        return city;
    }
    public Integer getCnt()
    {
        return cnt;
    }
    public List<WeatherData> getList()
    {
        return list;
    }
    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
