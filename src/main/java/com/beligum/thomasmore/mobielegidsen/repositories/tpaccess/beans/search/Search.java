package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private List<Node> node;

    //-----CONSTRUCTORS-----
    public Search()
    {
    }

    //-----PUBLIC METHODS-----
    public List<Node> getNode()
    {
        return node;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
