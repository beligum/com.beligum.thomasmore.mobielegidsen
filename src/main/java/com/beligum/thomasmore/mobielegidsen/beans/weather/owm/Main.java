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
 *         <p>
 *         "main":{"temp":21.48,"humidity":38,"pressure":1007,"temp_min":21.11,
 *         "temp_max":21.67}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main implements Serializable
{

    private static final long serialVersionUID = -7675389339518036588L;

    private Double temp;
    private Integer humidity;
    private Integer pressure;
    private Double temp_min;
    private Double temp_max;

    public Main()
    {
    }

    /**
     * @return the temp
     */
    public Double getTemp()
    {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(Double temp)
    {
        this.temp = temp;
    }

    /**
     * @return the humidity
     */
    public Integer getHumidity()
    {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(Integer humidity)
    {
        this.humidity = humidity;
    }

    /**
     * @return the pressure
     */
    public Integer getPressure()
    {
        return pressure;
    }

    /**
     * @param pressure the pressure to set
     */
    public void setPressure(Integer pressure)
    {
        this.pressure = pressure;
    }

    /**
     * @return the temp_min
     */
    public Double getTemp_min()
    {
        return temp_min;
    }

    /**
     * @param temp_min the temp_min to set
     */
    public void setTemp_min(Double temp_min)
    {
        this.temp_min = temp_min;
    }

    /**
     * @return the temp_max
     */
    public Double getTemp_max()
    {
        return temp_max;
    }

    /**
     * @param temp_max the temp_max to set
     */
    public void setTemp_max(Double temp_max)
    {
        this.temp_max = temp_max;
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
        result = prime * result
                 + ((humidity == null) ? 0 : humidity.hashCode());
        result = prime * result
                 + ((pressure == null) ? 0 : pressure.hashCode());
        result = prime * result + ((temp == null) ? 0 : temp.hashCode());
        result = prime * result
                 + ((temp_max == null) ? 0 : temp_max.hashCode());
        result = prime * result
                 + ((temp_min == null) ? 0 : temp_min.hashCode());
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
        Main other = (Main) obj;
        if (humidity == null) {
            if (other.humidity != null)
                return false;
        }
        else if (!humidity.equals(other.humidity))
            return false;
        if (pressure == null) {
            if (other.pressure != null)
                return false;
        }
        else if (!pressure.equals(other.pressure))
            return false;
        if (temp == null) {
            if (other.temp != null)
                return false;
        }
        else if (!temp.equals(other.temp))
            return false;
        if (temp_max == null) {
            if (other.temp_max != null)
                return false;
        }
        else if (!temp_max.equals(other.temp_max))
            return false;
        if (temp_min == null) {
            if (other.temp_min != null)
                return false;
        }
        else if (!temp_min.equals(other.temp_min))
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
        builder.append("Main [temp=");
        builder.append(temp);
        builder.append(", humidity=");
        builder.append(humidity);
        builder.append(", pressure=");
        builder.append(pressure);
        builder.append(", temp_min=");
        builder.append(temp_min);
        builder.append(", temp_max=");
        builder.append(temp_max);
        builder.append("]");
        return builder.toString();
    }

}
