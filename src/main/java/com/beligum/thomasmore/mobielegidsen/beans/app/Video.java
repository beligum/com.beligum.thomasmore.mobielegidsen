package com.beligum.thomasmore.mobielegidsen.beans.app;

import java.net.URI;

/**
 * Created by bram on 4/14/16.
 */
public class Video extends AbstractMedia
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private URI poster;

    //-----CONSTRUCTORS-----
    public Video(URI url, URI poster)
    {
        super(url);

        this.poster = poster;
    }

    //-----PUBLIC METHODS-----
    public URI getPoster()
    {
        return poster;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
