package com.beligum.thomasmore.mobielegidsen.config;

import com.beligum.base.server.R;
import com.beligum.thomasmore.mobielegidsen.beans.app.Rupelstreek;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Region;
import com.beligum.thomasmore.mobielegidsen.repositories.geonames.GeonamesRepository;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.TPAccessRepository;

import java.net.URI;

/**
 * Created by bram on 6/14/16.
 */
public class Settings
{
    //-----CONSTANTS-----
    private static Settings instance;
    private static final String COMMON_PREFIX = "thomasmore.mobielegidsen";

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----
    private Settings()
    {
    }

    //-----PUBLIC METHODS-----
    public static Settings instance()
    {
        if (Settings.instance == null) {
            Settings.instance = new Settings();
        }
        return Settings.instance;
    }
    public Region getRegion()
    {
        Region retVal = (Region) R.cacheManager().getApplicationCache().get(CacheKeys.REGION);
        if (retVal == null) {
            R.cacheManager().getApplicationCache().put(CacheKeys.REGION, retVal = new Rupelstreek());
        }

        return retVal;
    }

    public String getWeatherApiKey()
    {
        return R.configuration().getString(COMMON_PREFIX + ".weather.api.key");
    }
    public String getWeatherApiLang()
    {
        return R.configuration().getString(COMMON_PREFIX + ".weather.api.lang");
    }
    public String getWeatherApiUnits()
    {
        return R.configuration().getString(COMMON_PREFIX + ".weather.api.units");
    }

    public String getMapquestApiKey()
    {
        return R.configuration().getString(COMMON_PREFIX + ".map.mapquest-key");
    }
    public String getMapboxApiKey()
    {
        return R.configuration().getString(COMMON_PREFIX + ".map.mapbox-key");
    }
    public String getMapRouterEndpoint()
    {
        return R.configuration().getString(COMMON_PREFIX + ".map.router.endpoint");
    }
    public String getMapRouterKey()
    {
        return R.configuration().getString(COMMON_PREFIX + ".map.router.key");
    }
    public String getMapRouterVehicle()
    {
        return R.configuration().getString(COMMON_PREFIX + ".map.router.vehicle");
    }

    public URI getTpAccessApiBaseUrl()
    {
        return URI.create(R.configuration().getString(COMMON_PREFIX + ".tp-access.api-base-url"));
    }
    public String getTpAccessTokenPath()
    {
        return R.configuration().getString(COMMON_PREFIX + ".tp-access.token-path");
    }
    public String getTpAccessClientId()
    {
        return R.configuration().getString(COMMON_PREFIX + ".tp-access.client-id");
    }
    public String getTpAccessClientSecret()
    {
        return R.configuration().getString(COMMON_PREFIX + ".tp-access.client-secret");
    }
    public String getTpAccessCacheFolder()
    {
        return R.configuration().getString(COMMON_PREFIX + ".tp-access.cache-folder");
    }
    public TPAccessRepository getTpAccessRepository()
    {
        TPAccessRepository retVal = (TPAccessRepository) R.cacheManager().getApplicationCache().get(CacheKeys.TP_ACCESS_REPOSITORY);
        if (retVal == null) {
            R.cacheManager().getApplicationCache().put(CacheKeys.TP_ACCESS_REPOSITORY, retVal = new TPAccessRepository());
        }

        return retVal;
    }
    public GeonamesRepository getGeonamesRepository()
    {
        GeonamesRepository retVal = (GeonamesRepository) R.cacheManager().getApplicationCache().get(CacheKeys.GEONAMES_REPOSITORY);
        if (retVal == null) {
            R.cacheManager().getApplicationCache().put(CacheKeys.GEONAMES_REPOSITORY, retVal = new GeonamesRepository());
        }

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
