package com.beligum.thomasmore.mobielegidsen.endpoints;

import com.beligum.base.server.R;
import com.beligum.base.utils.Logger;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Route;
import com.beligum.thomasmore.mobielegidsen.beans.weather.owm.City;
import com.beligum.thomasmore.mobielegidsen.beans.weather.owm.Forecast;
import com.beligum.thomasmore.mobielegidsen.beans.weather.owm.WeatherData;
import com.beligum.thomasmore.mobielegidsen.config.CacheKeys;
import com.beligum.thomasmore.mobielegidsen.config.Settings;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Created by bram on 6/14/16.
 */
@Path("/weather")
public class WeatherEndpoint
{
    //-----CONSTANTS-----
    public enum ForecastTerm
    {
        TODAY,
        TODAY_PLUS_1,
        TODAY_PLUS_2
    }
    private static final long ONE_DAY_MILLIS = (1000*60*60*24);

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    @GET
    @Path("/forecast")
    @Produces(MediaType.APPLICATION_JSON)
    public ForecastData forecast(@QueryParam("route") String routeSlug) throws IOException
    {
        ForecastData retVal = null;

        retVal = this.getForecastDataFor(routeSlug);

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----
    private ForecastData getForecastDataFor(String routeSlug) throws IOException
    {
        ForecastData retVal = null;

        Map<String, ForecastData> routeForecastData = (Map<String, ForecastData>) R.cacheManager().getApplicationCache().get(CacheKeys.ROUTE_FORECAST_DATA);
        if (routeForecastData != null) {
            retVal = routeForecastData.get(routeSlug);
        }
        else {
            //initialize the cache
            R.cacheManager().getApplicationCache().put(CacheKeys.ROUTE_FORECAST_DATA, new HashMap<>());
        }

        long oneHourAgo = System.currentTimeMillis() - (1000 * 60 * 60);
        if (retVal == null || retVal.getCreationStamp() < oneHourAgo) {
            Forecast forecast = this.getForecastFor(routeSlug);
            if (forecast != null) {
                //TODO this is probably not right, since System.currentTimeMillis() is the UTC epoch time, not the local time?
                Calendar todayNoonDate = Calendar.getInstance();
                todayNoonDate.setTime(new Date(System.currentTimeMillis()));
                todayNoonDate.set(Calendar.HOUR_OF_DAY, 12);
                todayNoonDate.set(Calendar.MINUTE, todayNoonDate.getMinimum(Calendar.MINUTE));
                todayNoonDate.set(Calendar.SECOND, todayNoonDate.getMinimum(Calendar.SECOND));
                todayNoonDate.set(Calendar.MILLISECOND, todayNoonDate.getMinimum(Calendar.MILLISECOND));

                long todayNoon = todayNoonDate.getTimeInMillis();
                long todayNoonPlus1 = todayNoon + ONE_DAY_MILLIS;
                long todayNoonPlus2 = todayNoonPlus1 + ONE_DAY_MILLIS;

                Map<ForecastTerm, WeatherData> data = new LinkedHashMap<>();
                List<WeatherData> list = forecast.getList();
                for (WeatherData e : list) {
                    //if we pass the current timestamp and we haven't stored anything for today, do it now
                    //Note: Openweathermap timestamps are in seconds, not millis
                    if (e.getDt() > todayNoon/1000 && !data.containsKey(ForecastTerm.TODAY)) {
                        data.put(ForecastTerm.TODAY, e);
                    }
                    else if (e.getDt() > todayNoonPlus1/1000 && !data.containsKey(ForecastTerm.TODAY_PLUS_1)) {
                        data.put(ForecastTerm.TODAY_PLUS_1, e);
                    }
                    else if (e.getDt() > todayNoonPlus2/1000 && !data.containsKey(ForecastTerm.TODAY_PLUS_2)) {
                        data.put(ForecastTerm.TODAY_PLUS_2, e);
                    }
                }

                //we should end up with as much entries as the enum
                if (data.size()==ForecastTerm.values().length) {
                    retVal = new ForecastData(forecast.getCreationStamp(), forecast.getCity(), data);

                    //update the cache
                    ((Map<String, ForecastData>) R.cacheManager().getApplicationCache().get(CacheKeys.ROUTE_FORECAST_DATA)).put(routeSlug, retVal);
                }
            }
        }

        return retVal;
    }
    private Forecast getForecastFor(String routeSlug) throws IOException
    {
        Forecast retVal = null;

        Route route = Settings.instance().getRegion().getRoutes().get(routeSlug);
        if (route==null) {
            Logger.error("Encountered request for unknown route '"+routeSlug+"', returning null");
        }
        else {
            Map<String, Forecast> routeForecasts = (Map<String, Forecast>) R.cacheManager().getApplicationCache().get(CacheKeys.ROUTE_FORECASTS);
            if (routeForecasts != null) {
                retVal = routeForecasts.get(routeSlug);
            }
            else {
                //initialize the cache
                R.cacheManager().getApplicationCache().put(CacheKeys.ROUTE_FORECASTS, new HashMap<>());
            }

            boolean updateCache = false;
            if (retVal == null) {
                retVal = this.fetchOpenweathermapForecastFor(route);
                updateCache = true;
            }

            if (retVal != null) {
                //check for updates
                long oneHourAgo = System.currentTimeMillis() - (1000 * 60 * 60);
                if (retVal.getCreationStamp() < oneHourAgo) {
                    Logger.info("Updating forcast for '" + routeSlug + "' because it's too old");
                    retVal = this.fetchOpenweathermapForecastFor(route);
                    updateCache = true;
                }
            }

            if (updateCache) {
                ((Map<String, Forecast>) R.cacheManager().getApplicationCache().get(CacheKeys.ROUTE_FORECASTS)).put(routeSlug, retVal);
            }
        }

        return retVal;
    }
    private Forecast fetchOpenweathermapForecastFor(Route route) throws IOException
    {
        Forecast retVal = null;

        ClientConfig config = new ClientConfig();
        Client httpClient = ClientBuilder.newClient(config);
        //for details, see http://openweathermap.org/forecast5
        UriBuilder builder = UriBuilder.fromUri("http://api.openweathermap.org/data/2.5/forecast")
                                       .queryParam("appid", Settings.instance().getWeatherApiKey())
                                       .queryParam("units", Settings.instance().getWeatherApiUnits())
                                       //TODO maybe make this dynamic?
                                       .queryParam("lang", Settings.instance().getWeatherApiLang())

                                       .queryParam("lat", route.getLocation().getLatitude())
                                       .queryParam("lon", route.getLocation().getLongitude());

        URI target = builder.build();
        Response response = httpClient.target(target).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            //See https://github.com/sfragata/weather
            retVal = response.readEntity(Forecast.class);
        }
        else {
            throw new IOException("Error status returned while fetching Openweathermap forecast for '" + route.getName() + "'; " + response);
        }

        return retVal;
    }

    //-----PRIVATE CLASSES-----
    public class ForecastData
    {
        private long creationStamp;
        private City city;
        private Map<ForecastTerm, WeatherData> data;

        public ForecastData(long creationStamp, City city,
                            Map<ForecastTerm, WeatherData> data)
        {
            this.creationStamp = creationStamp;
            this.city = city;
            this.data = data;
        }

        public long getCreationStamp()
        {
            return creationStamp;
        }
        public City getCity()
        {
            return city;
        }
        public WeatherData getToday()
        {
            return this.getData(ForecastTerm.TODAY.ordinal());
        }
        public WeatherData getTodayPlus1()
        {
            return this.getData(ForecastTerm.TODAY_PLUS_1.ordinal());
        }
        public WeatherData getTodayPlus2()
        {
            return this.getData(ForecastTerm.TODAY_PLUS_2.ordinal());
        }

        private WeatherData getData(int offset)
        {
            WeatherData retVal = null;

            if (offset>=0 && offset<ForecastTerm.values().length) {
                retVal = this.data.get(ForecastTerm.values()[offset]);
            }
            else {
                Logger.error("Invalid forecast offset, returning null; "+offset);
            }

            return retVal;
        }
    }
}
