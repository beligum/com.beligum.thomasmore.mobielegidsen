/**
 *
 */
package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author frasi1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData implements Serializable
{

    private static final long serialVersionUID = 6449300420532969611L;

    private Clouds clouds;
    private Coordinates coord;
    private Main main;
    private Sys sys;
    private Wind wind;
    private List<Weather> weather;
    private String base;
    private Rain rain;
    private Long dt;
    private Long id;
    private String name;
    private Integer cod;

    public WeatherData()
    {
    }

    //(b) extra added
    @JsonIgnore
    public String getWiIcon()
    {
        return IconMapper.INSTANCE.getWiIconFor(this);
    }

    /**
     * @return the clouds
     */
    public Clouds getClouds()
    {
        return clouds;
    }

    /**
     * @param clouds the clouds to set
     */
    public void setClouds(Clouds clouds)
    {
        this.clouds = clouds;
    }

    /**
     * @return the coord
     */
    public Coordinates getCoord()
    {
        return coord;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(Coordinates coord)
    {
        this.coord = coord;
    }

    /**
     * @return the main
     */
    public Main getMain()
    {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(Main main)
    {
        this.main = main;
    }

    /**
     * @return the sys
     */
    public Sys getSys()
    {
        return sys;
    }

    /**
     * @param sys the sys to set
     */
    public void setSys(Sys sys)
    {
        this.sys = sys;
    }

    /**
     * @return the wind
     */
    public Wind getWind()
    {
        return wind;
    }

    /**
     * @param wind the wind to set
     */
    public void setWind(Wind wind)
    {
        this.wind = wind;
    }

    /**
     * @return the weather
     */
    public List<Weather> getWeather()
    {
        return weather;
    }

    /**
     * @param weather the weather to set
     */
    public void setWeather(List<Weather> weather)
    {
        this.weather = weather;
    }

    /**
     * @return the base
     */
    public String getBase()
    {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(String base)
    {
        this.base = base;
    }

    /**
     * @return the rain
     */
    public Rain getRain()
    {
        return rain;
    }

    /**
     * @param rain the rain to set
     */
    public void setRain(Rain rain)
    {
        this.rain = rain;
    }

    /**
     * @return the dt
     */
    public Long getDt()
    {
        return dt;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(Long dt)
    {
        this.dt = dt;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the cod
     */
    public Integer getCod()
    {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(Integer cod)
    {
        this.cod = cod;
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
        result = prime * result + ((base == null) ? 0 : base.hashCode());
        result = prime * result + ((clouds == null) ? 0 : clouds.hashCode());
        result = prime * result + ((cod == null) ? 0 : cod.hashCode());
        result = prime * result + ((coord == null) ? 0 : coord.hashCode());
        result = prime * result + ((dt == null) ? 0 : dt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((main == null) ? 0 : main.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((rain == null) ? 0 : rain.hashCode());
        result = prime * result + ((sys == null) ? 0 : sys.hashCode());
        result = prime * result + ((weather == null) ? 0 : weather.hashCode());
        result = prime * result + ((wind == null) ? 0 : wind.hashCode());
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
        WeatherData other = (WeatherData) obj;
        if (base == null) {
            if (other.base != null)
                return false;
        }
        else if (!base.equals(other.base))
            return false;
        if (clouds == null) {
            if (other.clouds != null)
                return false;
        }
        else if (!clouds.equals(other.clouds))
            return false;
        if (cod == null) {
            if (other.cod != null)
                return false;
        }
        else if (!cod.equals(other.cod))
            return false;
        if (coord == null) {
            if (other.coord != null)
                return false;
        }
        else if (!coord.equals(other.coord))
            return false;
        if (dt == null) {
            if (other.dt != null)
                return false;
        }
        else if (!dt.equals(other.dt))
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
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (rain == null) {
            if (other.rain != null)
                return false;
        }
        else if (!rain.equals(other.rain))
            return false;
        if (sys == null) {
            if (other.sys != null)
                return false;
        }
        else if (!sys.equals(other.sys))
            return false;
        if (weather == null) {
            if (other.weather != null)
                return false;
        }
        else if (!weather.equals(other.weather))
            return false;
        if (wind == null) {
            if (other.wind != null)
                return false;
        }
        else if (!wind.equals(other.wind))
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
        builder.append("WeatherData [clouds=");
        builder.append(clouds);
        builder.append(", coord=");
        builder.append(coord);
        builder.append(", main=");
        builder.append(main);
        builder.append(", sys=");
        builder.append(sys);
        builder.append(", wind=");
        builder.append(wind);
        builder.append(", weather=");
        builder.append(weather);
        builder.append(", base=");
        builder.append(base);
        builder.append(", rain=");
        builder.append(rain);
        builder.append(", dt=");
        builder.append(dt);
        builder.append(", id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", cod=");
        builder.append(cod);
        builder.append("]");
        return builder.toString();
    }

}
