package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeCoordinates
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String latitude;
    private String longitude;

    //-----CONSTRUCTORS-----
    public NodeCoordinates()
    {
    }

    //-----PUBLIC METHODS-----
    public String getLatitude()
    {
        return latitude;
    }
    public String getLongitude()
    {
        return longitude;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
