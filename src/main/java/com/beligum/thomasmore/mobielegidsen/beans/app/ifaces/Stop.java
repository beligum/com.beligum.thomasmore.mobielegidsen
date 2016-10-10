package com.beligum.thomasmore.mobielegidsen.beans.app.ifaces;

import com.beligum.thomasmore.mobielegidsen.beans.app.MapLocation;

/**
 * Created by bram on 4/13/16.
 */
public interface Stop
{
    //-----CONSTANTS-----
    class SubSection
    {
        private String title;
        private String description;

        public SubSection(String title, String description)
        {
            this.title = title;
            this.description = description;
        }

        public String getTitle()
        {
            return title;
        }
        public String getDescription()
        {
            return description;
        }
    }

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    String getName();
    String getTitle();
    String getSlug();
    String getStreetName();
    String getStreetNumber();
    String getPostalCode();
    String getCity();
    String getPhoneNumber();
    String getShortDescription();
    String getGeneralDescription();
    SubSection[] getSubSections();
    String getTags();
    MapLocation getLocation();
    Media[] getMedia();

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
