package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.FoodEntry;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.LodgingEntry;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Media;

/**
 * Created by bram on 5/20/16.
 */
public abstract class HoReCa implements FoodEntry, LodgingEntry
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String title;
    private String address;
    private String postalCode;
    private String city;
    private String phoneNumber;
    private String email;
    private String website;
    private MapLocation location;
    private Media[] media;
    private String description;

    //-----CONSTRUCTORS-----
    public HoReCa()
    {
    }
    public HoReCa(String title, String address, String postalCode, String city, String phoneNumber, String email, String website, MapLocation location, Media[] media, String description)
    {
        this.title = title;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.location = location;
        this.media = media;
        this.description = description;
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    @Override
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    @Override
    public String getPostalCode()
    {
        return postalCode;
    }
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
    @Override
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    @Override
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    @Override
    public String getWebsite()
    {
        return website;
    }
    public void setWebsite(String website)
    {
        this.website = website;
    }
    @Override
    public MapLocation getLocation()
    {
        return location;
    }
    public void setLocation(MapLocation location)
    {
        this.location = location;
    }
    @Override
    public Media[] getMedia()
    {
        return media;
    }
    public void setMedia(Media[] media)
    {
        this.media = media;
    }
    @Override
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
