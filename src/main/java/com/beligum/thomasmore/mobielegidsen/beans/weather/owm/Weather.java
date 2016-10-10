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
 *         "weather":[{"id":804,"main":"Clouds","description":"overcast clouds",
 *         "icon":"04d"}]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable
{

    private static final long serialVersionUID = -3987785858239685639L;

    private Integer id;
    private String main;
    private String description;
    private String icon;

    public Weather()
    {
    }

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the main
     */
    public String getMain()
    {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(String main)
    {
        this.main = main;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the icon
     */
    public String getIcon()
    {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
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
                 + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((icon == null) ? 0 : icon.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((main == null) ? 0 : main.hashCode());
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
        Weather other = (Weather) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (icon == null) {
            if (other.icon != null)
                return false;
        }
        else if (!icon.equals(other.icon))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (main == null) {
            if (other.main != null)
                return false;
        }
        else if (!main.equals(other.main))
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
        builder.append("Weather [id=");
        builder.append(id);
        builder.append(", main=");
        builder.append(main);
        builder.append(", description=");
        builder.append(description);
        builder.append(", icon=");
        builder.append(icon);
        builder.append("]");
        return builder.toString();
    }

}
