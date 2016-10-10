package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeAddress
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String field_straatnaam;
    private String field_huisnummer;
    private String field_postcode;
    private String field_gemeente;
    private String field_deelgemeente;
    private String field_toeristische_cluster;
    private String field_toeristische_regio_tl;
    private String field_land;

    //-----CONSTRUCTORS-----
    public NodeAddress()
    {
    }

    //-----PUBLIC METHODS-----
    public String getField_straatnaam()
    {
        return field_straatnaam;
    }
    public String getField_huisnummer()
    {
        return field_huisnummer;
    }
    public String getField_postcode()
    {
        return field_postcode;
    }
    public String getField_gemeente()
    {
        return field_gemeente;
    }
    public String getField_deelgemeente()
    {
        return field_deelgemeente;
    }
    public String getField_toeristische_cluster()
    {
        return field_toeristische_cluster;
    }
    public String getField_toeristische_regio_tl()
    {
        return field_toeristische_regio_tl;
    }
    public String getField_land()
    {
        return field_land;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
