package com.beligum.thomasmore.mobielegidsen.repositories.geonames;

import com.beligum.blocks.config.Settings;
import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepository;
import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepositoryResponse;
import com.beligum.thomasmore.mobielegidsen.repositories.geonames.beans.NearbyPostalcodesReponse;
import com.beligum.thomasmore.mobielegidsen.repositories.geonames.beans.PostalcodeResponse;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.*;

/**
 * Created by bram on 10/2/16.
 */
public class GeonamesRepository implements RemoteRepository
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----
    public GeonamesRepository()
    {
    }

    //-----PUBLIC METHODS-----
    public List<PostalcodeResponse> findPostalcodesNear(double latitude, double longitude, double kilometers, int maxResults) throws Exception
    {
        List<PostalcodeResponse> retVal = new ArrayList<>();

        Map<String, String> params = new HashMap<>();
        params.put("lat", String.valueOf(latitude));
        params.put("lng", String.valueOf(longitude));
        params.put("radius", String.valueOf(kilometers));
        params.put("maxRows", String.valueOf(maxResults));
        params.put("style", "SHORT");

        NearbyPostalcodesReponse response = this.queryRemoteApi(NearbyPostalcodesReponse.class, params);
        if (response != null && response.getPostalCodes() != null) {
            retVal = response.getPostalCodes();
        }

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----
    private <T extends RemoteRepositoryResponse> T queryRemoteApi(Class<T> type, Map<String, String> params) throws Exception
    {
        T retVal = null;

        ClientConfig config = new ClientConfig();
        Client httpClient = ClientBuilder.newClient(config);

        RemoteRepositoryResponse dummy = type.newInstance();
        //for details, see http://www.geonames.org/export/web-services.html
        UriBuilder builder = UriBuilder.fromUri("http://api.geonames.org/")
                                       .path(dummy.getPath())
                                       .queryParam("username", Settings.instance().getGeonamesUsername())
                                       .queryParam("type", "json");

        if (params != null) {
            for (Map.Entry<String, String> p : params.entrySet()) {
                builder.queryParam(p.getKey(), p.getValue());
            }
        }

        Response response = httpClient.target(builder.build()).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            retVal = response.readEntity(type);
        }
        else {
            throw new IOException("Error status returned while fetching remote API data;" + response);
        }

        return retVal;
    }
}
