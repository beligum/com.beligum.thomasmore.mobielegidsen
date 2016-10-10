package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.contenttype;

import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepositoryResponse;

/**
 * Created by bram on 10/2/16.
 */
public class ContenttypesReponse implements RemoteRepositoryResponse
{
    //-----CONSTANTS-----
    public static final String PATH = "/contenttypes";

    //-----VARIABLES-----
    private Contenttypes contenttypes;

    //-----CONSTRUCTORS-----
    public ContenttypesReponse()
    {
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getPath()
    {
        return PATH;
    }
    public Contenttypes getContenttypes()
    {
        return contenttypes;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
