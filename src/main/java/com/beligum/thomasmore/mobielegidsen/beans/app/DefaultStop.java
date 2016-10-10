package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.toolkit.StringFunctions;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Media;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Stop;

/**
 * Created by bram on 4/13/16.
 */
public class DefaultStop implements Stop
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String name;
    private String title;
    private String slug;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String phoneNumber;
    private String shortDescription;
    private String generalDescription;
    private SubSection[] subSections;
    private String tags;
    private MapLocation location;
    private Media[] media;

    //-----CONSTRUCTORS-----
    public DefaultStop(String name, String title, String streetName, String streetNumber, String postalCode, String city, String phoneNumber, String shortDescription, String generalDescription, SubSection[] subSections, String tags, MapLocation location, Media[] media)
    {
        this.name = name;
        this.title = title;
        this.slug = StringFunctions.prepareSeoValue(name);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.shortDescription = shortDescription;
        this.generalDescription = generalDescription;
        this.subSections = subSections;
        this.tags = tags;
        this.location = location;
        this.media = media;
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getName()
    {
        return name;
    }
    @Override
    public String getTitle()
    {
        return title;
    }
    @Override
    public String getSlug()
    {
        return slug;
    }
    @Override
    public String getStreetName()
    {
        return streetName;
    }
    @Override
    public String getStreetNumber()
    {
        return streetNumber;
    }
    @Override
    public String getPostalCode()
    {
        return postalCode;
    }
    @Override
    public String getCity()
    {
        return city;
    }
    @Override
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    @Override
    public String getShortDescription()
    {
        return shortDescription;
    }
    @Override
    public String getGeneralDescription()
    {
        return generalDescription;
    }
    @Override
    public SubSection[] getSubSections()
    {
        return subSections;
    }
    @Override
    public String getTags()
    {
        return tags;
    }
    @Override
    public MapLocation getLocation()
    {
        return location;
    }
    @Override
    public Media[] getMedia()
    {
        return media;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
