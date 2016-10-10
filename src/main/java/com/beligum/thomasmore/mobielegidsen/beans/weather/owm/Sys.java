/**
 *
 */
package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author frasi1
 *         <p>
 *         <p>
 *         <p>
 *         "sys":{"country":"CA","sunrise":1371113396,"sunset":1371170416}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys implements Serializable
{

    private static final long serialVersionUID = -1519929139285862193L;

    private String country;
    private Long sunrise;
    private Long sunset;

    /**
     *
     */
    public Sys()
    {
    }

    /**
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * @return the sunrise
     */
    public Long getSunrise()
    {
        return sunrise;
    }

    /**
     * @param sunrise the sunrise to set
     */
    public void setSunrise(Long sunrise)
    {
        this.sunrise = sunrise;
    }

    /**
     * @return the sunset
     */
    public Long getSunset()
    {
        return sunset;
    }

    /**
     * @param sunset the sunset to set
     */
    public void setSunset(Long sunset)
    {
        this.sunset = sunset;
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
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((sunrise == null) ? 0 : sunrise.hashCode());
        result = prime * result + ((sunset == null) ? 0 : sunset.hashCode());
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
        Sys other = (Sys) obj;
        if (country == null) {
            if (other.country != null)
                return false;
        }
        else if (!country.equals(other.country))
            return false;
        if (sunrise == null) {
            if (other.sunrise != null)
                return false;
        }
        else if (!sunrise.equals(other.sunrise))
            return false;
        if (sunset == null) {
            if (other.sunset != null)
                return false;
        }
        else if (!sunset.equals(other.sunset))
            return false;
        return true;
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
        builder.append("Sys [country=");
        builder.append(country);
        builder.append(", sunrise=");
        builder.append(sunrise);
        builder.append(", sunset=");
        builder.append(sunset);
        builder.append("]");
        return builder.toString();
    }

}
