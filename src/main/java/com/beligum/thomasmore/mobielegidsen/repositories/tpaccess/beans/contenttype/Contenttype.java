package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.contenttype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contenttype implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String type;
    private String name;

    //-----CONSTRUCTORS-----
    public Contenttype()
    {
    }

    //-----PUBLIC METHODS-----
    public String getType()
    {
        return type;
    }
    public String getName()
    {
        return name;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
