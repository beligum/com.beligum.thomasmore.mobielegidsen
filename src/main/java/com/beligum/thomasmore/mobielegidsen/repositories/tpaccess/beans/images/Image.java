package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.images;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by bram on 10/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements Serializable
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    @JsonProperty("#")
    private Data data;

    @JsonProperty("@main")
    private Boolean main;

    private URI cachedUrl;

    //-----CONSTRUCTORS-----
    public Image()
    {
    }

    //-----PUBLIC METHODS-----
    public Data getData()
    {
        return data;
    }
    public Boolean getMain()
    {
        return main;
    }

    public URI getCachedUrl()
    {
        return cachedUrl;
    }
    public void setCachedUrl(URI cachedUrl)
    {
        this.cachedUrl = cachedUrl;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

    public static class Data
    {
        public String title;
        public String type;
        public String lastModified;
        public String description;
        public String mediaObjectId;
    }

}
