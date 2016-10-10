package com.beligum.thomasmore.mobielegidsen.controllers;

import com.beligum.blocks.controllers.BreadcrumbController;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by bram on 9/27/16.
 */
public class RouteHeaderController extends BreadcrumbController
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    public Map.Entry<URI, String> getPreviousPage() throws IOException
    {
        Map.Entry<URI, String> retVal = null;

        //Note that the first in the row is the current page
        List<Map.Entry<URI, String>> allPreviousPages = this.searchParentPages();
        if (allPreviousPages.size()>1) {
            retVal = allPreviousPages.get(1);
        }

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
