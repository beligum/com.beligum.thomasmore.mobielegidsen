package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author frasi1
 *         <p>
 *         <p>
 *         "wind":{"speed":1.54,"gust":3.6,"deg":302}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind implements Serializable
{

    private static final long serialVersionUID = -1562373382967667216L;

    private Double speed;
    private Double gust;
    private Double deg;

    public Wind()
    {
    }

    /**
     * @return the speed
     */
    public Double getSpeed()
    {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Double speed)
    {
        this.speed = speed;
    }

    /**
     * @return the gust
     */
    public Double getGust()
    {
        return gust;
    }

    /**
     * @param gust the gust to set
     */
    public void setGust(Double gust)
    {
        this.gust = gust;
    }

    /**
     * @return the deg
     */
    public Double getDeg()
    {
        return deg;
    }

    /**
     * @param deg the deg to set
     */
    public void setDeg(Double deg)
    {
        this.deg = deg;
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
        result = prime * result + ((deg == null) ? 0 : deg.hashCode());
        result = prime * result + ((gust == null) ? 0 : gust.hashCode());
        result = prime * result + ((speed == null) ? 0 : speed.hashCode());
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
        Wind other = (Wind) obj;
        if (deg == null) {
            if (other.deg != null)
                return false;
        }
        else if (!deg.equals(other.deg))
            return false;
        if (gust == null) {
            if (other.gust != null)
                return false;
        }
        else if (!gust.equals(other.gust))
            return false;
        if (speed == null) {
            if (other.speed != null)
                return false;
        }
        else if (!speed.equals(other.speed))
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
        builder.append("Wind [speed=");
        builder.append(speed);
        builder.append(", gust=");
        builder.append(gust);
        builder.append(", deg=");
        builder.append(deg);
        builder.append("]");
        return builder.toString();
    }

}
