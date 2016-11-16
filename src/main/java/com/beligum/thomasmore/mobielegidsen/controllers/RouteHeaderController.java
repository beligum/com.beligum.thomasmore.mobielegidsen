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
    private List<Map.Entry<URI, String>> parentPages = null;

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    public Map.Entry<URI, String> getCurrentTitle() throws IOException
    {
        Map.Entry<URI, String> retVal = null;

        //Note that the first in the row is the current page
        List<Map.Entry<URI, String>> allPreviousPages = this.getParentPages();
        if (allPreviousPages.size()>0) {
            retVal = allPreviousPages.get(0);
        }

        return retVal;
    }
    public Map.Entry<URI, String> getPreviousPage() throws IOException
    {
        Map.Entry<URI, String> retVal = null;

        //Note that the first in the row is the current page
        List<Map.Entry<URI, String>> allPreviousPages = this.getParentPages();
        if (allPreviousPages.size()>1) {
            retVal = allPreviousPages.get(1);
        }

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----
    private List<Map.Entry<URI, String>> getParentPages() throws IOException
    {
        if (this.parentPages==null) {
            this.parentPages = this.searchParentPages();
        }

        return this.parentPages;
    }
}
