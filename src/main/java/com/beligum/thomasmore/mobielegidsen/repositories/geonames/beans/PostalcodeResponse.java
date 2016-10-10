package com.beligum.thomasmore.mobielegidsen.repositories.geonames.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalcodeResponse
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private double lng;
    private double lat;
    private double distance;
    private String countryCode;
    private String postalCode;
    private String placeName;

    //-----CONSTRUCTORS-----
    public PostalcodeResponse()
    {
    }

    //-----PUBLIC METHODS-----
    public double getLng()
    {
        return lng;
    }
    public double getLat()
    {
        return lat;
    }
    public double getDistance()
    {
        return distance;
    }
    public String getCountryCode()
    {
        return countryCode;
    }
    public String getPostalCode()
    {
        return postalCode;
    }
    public String getPlaceName()
    {
        return placeName;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
