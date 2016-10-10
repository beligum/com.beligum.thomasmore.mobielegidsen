package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search;

import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepositoryResponse;

/**
 * Created by bram on 10/2/16.
 */
public class SearchReponse implements RemoteRepositoryResponse
{
    //-----CONSTANTS-----
    public static final String PATH = "/search";

    //-----VARIABLES-----
    private Error error;
    private Search nodes;

    //-----CONSTRUCTORS-----
    public SearchReponse()
    {
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getPath()
    {
        return PATH;
    }
    public Error getError()
    {
        return error;
    }
    public Search getNodes()
    {
        return nodes;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
