package com.beligum.thomasmore.mobielegidsen.repositories.geonames.beans;

import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepositoryResponse;

import java.util.List;

/**
 * Created by bram on 10/2/16.
 */
public class NearbyPostalcodesReponse implements RemoteRepositoryResponse
{
    //-----CONSTANTS-----
    private static final String PATH = "/findNearbyPostalCodes";

    //-----VARIABLES-----
    private List<PostalcodeResponse> postalCodes;

    //-----CONSTRUCTORS-----
    public NearbyPostalcodesReponse()
    {
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getPath()
    {
        return PATH;
    }
    public List<PostalcodeResponse> getPostalCodes()
    {
        return postalCodes;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
