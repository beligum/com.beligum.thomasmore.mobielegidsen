package com.beligum.thomasmore.mobielegidsen.beans.app.ifaces;

import com.beligum.thomasmore.mobielegidsen.beans.app.Image;
import com.beligum.thomasmore.mobielegidsen.beans.app.Video;

import java.util.Map;

/**
 * Created by bram on 4/13/16.
 */
public interface Region
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    String getName();
    String getSlug();
    Video getVideo();
    String getTagline();
    String getQuote();
    Image getQuoteImage();
    String getIntroduction();
    Map<String, Route> getRoutes();

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
