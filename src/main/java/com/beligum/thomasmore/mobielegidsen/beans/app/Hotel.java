package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Media;

/**
 * Created by bram on 5/20/16.
 */
public class Hotel extends HoReCa
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----
    public Hotel()
    {
    }
    public Hotel(String title, String address, String postalCode, String city, String phoneNumber, String email, String website, MapLocation location,
                 Media[] media, String description)
    {
        super(title, address, postalCode, city, phoneNumber, email, website, location, media, description);
    }
    @Override
    public Type getType()
    {
        return Type.LODGING;
    }

    //-----PUBLIC METHODS-----

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
