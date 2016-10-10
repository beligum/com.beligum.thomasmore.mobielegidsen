/**
 *
 */
package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author frasi1
 *         <p>
 *         <p>
 *         <p>
 *         "coord":{"lon":-71.214539,"lat":46.812279}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates
{

    private Double lat;
    private Double lon;

    /**
     * @return the latitude
     */
    public Double getLat()
    {
        return lat;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLat(Double latitude)
    {
        this.lat = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLon()
    {
        return lon;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLon(Double longitude)
    {
        this.lon = longitude;
    }

    /**
     *
     */
    public Coordinates()
    {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Coordinates [latitude=");
        builder.append(lat);
        builder.append(", longitude=");
        builder.append(lon);
        builder.append("]");
        return builder.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lon == null) ? 0 : lon.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        if (lat == null) {
            if (other.lat != null)
                return false;
        }
        else if (!lat.equals(other.lat))
            return false;
        if (lon == null) {
            if (other.lon != null)
                return false;
        }
        else if (!lon.equals(other.lon))
            return false;
        return true;
    }

}
