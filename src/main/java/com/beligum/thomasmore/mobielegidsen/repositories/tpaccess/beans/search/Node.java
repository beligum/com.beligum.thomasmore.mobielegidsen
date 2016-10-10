package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search;

import com.beligum.thomasmore.mobielegidsen.repositories.geonames.beans.PostalcodeResponse;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.images.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Node implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private Long nid;
    private String type;
    private String title;
    private List<NodeAddress> address;
    private List<NodeCoordinates> coordinates;
    private String field_omschrijving_nl;

    private List<Image> images;
    private String typeCode;
    private PostalcodeResponse locationInfo;

    //-----CONSTRUCTORS-----
    public Node()
    {
    }

    //-----PUBLIC METHODS-----
    public Long getNid()
    {
        return nid;
    }
    public String getType()
    {
        return type;
    }
    public String getTitle()
    {
        return title;
    }
    public List<NodeAddress> getAddress()
    {
        return address;
    }
    public List<NodeCoordinates> getCoordinates()
    {
        return coordinates;
    }
    public String getField_omschrijving_nl()
    {
        return field_omschrijving_nl;
    }

    public List<Image> getImages()
    {
        return images;
    }
    public void addImage(Image image)
    {
        if (this.images==null) {
            this.images = new ArrayList<>();
        }

        this.images.add(image);
    }
    public String getTypeCode()
    {
        return typeCode;
    }
    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }
    public PostalcodeResponse getLocationInfo()
    {
        return locationInfo;
    }
    public void setLocationInfo(PostalcodeResponse locationInfo)
    {
        this.locationInfo = locationInfo;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
