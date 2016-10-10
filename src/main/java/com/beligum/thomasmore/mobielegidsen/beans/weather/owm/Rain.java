/**
 *
 */
package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author frasi1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain
{

    private Integer _3h;

    /**
     * @return the _3h
     */
    public Integer get3h()
    {
        return _3h;
    }

    /**
     * @param _3h the _3h to set
     */
    public void set3h(Integer _3h)
    {
        this._3h = _3h;
    }

    /**
     *
     */
    public Rain()
    {
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
        result = prime * result + ((_3h == null) ? 0 : _3h.hashCode());
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
        Rain other = (Rain) obj;
        if (_3h == null) {
            if (other._3h != null)
                return false;
        }
        else if (!_3h.equals(other._3h))
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
        builder.append("Rain [_3h=");
        builder.append(_3h);
        builder.append("]");
        return builder.toString();
    }

}
