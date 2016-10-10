package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String message;
    private Integer code;

    //-----CONSTRUCTORS-----
    public Error()
    {
    }

    //-----PUBLIC METHODS-----
    public String getMessage()
    {
        return message;
    }
    public Integer getCode()
    {
        return code;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
