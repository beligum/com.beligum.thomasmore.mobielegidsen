package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.images;

import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepositoryResponse;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.Error;

/**
 * Created by bram on 10/2/16.
 */
public class ImagesReponse implements RemoteRepositoryResponse
{
    //-----CONSTANTS-----
    public static final String PATH = "/images";

    //-----VARIABLES-----
    private Error error;
    private Images nodes;

    //-----CONSTRUCTORS-----
    public ImagesReponse()
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
    public Images getNodes()
    {
        return nodes;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
