package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.Logger;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Route;
import com.beligum.thomasmore.mobielegidsen.endpoints.WeatherEndpoint;

import java.io.IOException;

/**
 * Created by bram on 6/14/16.
 */
public abstract class AbstractRoute implements Route
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    @Override
    public WeatherEndpoint.ForecastData getWeatherForecast()
    {
        WeatherEndpoint.ForecastData retVal = null;

        try {
            retVal = new WeatherEndpoint().forecast(this.getSlug());
        }
        catch (IOException e) {
            Logger.error("Caught error while fetching forecast for " + this.getName(), e);
        }

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
