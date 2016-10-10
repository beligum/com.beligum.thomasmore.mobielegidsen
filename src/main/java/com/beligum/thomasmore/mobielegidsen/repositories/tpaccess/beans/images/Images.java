package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.images;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private List<Image> node;

    //-----CONSTRUCTORS-----
    public Images()
    {
    }

    //-----PUBLIC METHODS-----
    public List<Image> getNode()
    {
        return node;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
