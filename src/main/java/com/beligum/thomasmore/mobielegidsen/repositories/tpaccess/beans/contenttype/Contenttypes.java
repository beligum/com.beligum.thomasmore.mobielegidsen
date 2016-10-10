package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.contenttype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contenttypes implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private List<Contenttype> contenttype;

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    public List<Contenttype> getContenttype()
    {
        return contenttype;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
