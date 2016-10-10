package com.beligum.thomasmore.mobielegidsen.repositories.tpaccess;

import com.beligum.base.utils.Logger;
import com.beligum.base.utils.json.Json;
import com.beligum.thomasmore.mobielegidsen.beans.app.HoReCa;
import com.beligum.thomasmore.mobielegidsen.beans.app.Hotel;
import com.beligum.thomasmore.mobielegidsen.beans.app.MapLocation;
import com.beligum.thomasmore.mobielegidsen.beans.app.Restaurant;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Media;
import com.beligum.thomasmore.mobielegidsen.config.Settings;
import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepository;
import com.beligum.thomasmore.mobielegidsen.repositories.RemoteRepositoryResponse;
import com.beligum.thomasmore.mobielegidsen.repositories.geonames.GeonamesRepository;
import com.beligum.thomasmore.mobielegidsen.repositories.geonames.beans.PostalcodeResponse;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.images.Image;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.images.ImagesReponse;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.Node;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.NodeAddress;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.NodeCoordinates;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.SearchReponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.CreateFlag;
import org.apache.hadoop.fs.FileContext;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Some interesting, wrongly documented paths:
 * <p>
 * https://api.tpaccess.be/api/1.0/contenttypes?access_token=YmNiZjY0OTkzZGQ1MGQ0N2I2YzkyMjI4ZmI0NzNlNWM0Mjg5YmQ3M2U2NzY4MmEwZmVhYmM3MjRjMDdmODU3NQ
 * <p>
 * <p>
 * Created by bram on 10/2/16.
 */
public class TPAccessRepository implements RemoteRepository
{
    //-----CONSTANTS-----
    private static final String BASE_PATH = "/api/1.0";
    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private static final String FORMAT_PARAM = "format";
    private static final String FORMAT_JSON = "json";
    private static final String HDFS_CACHE_ROOT_PATH = "/tpaccess/cache/";

    public static final String TYPE_CODE_ATTRACTION = "at";
    public static final String TYPE_CODE_CAMPING = "ca";
    public static final String TYPE_CODE_GUESTROOM = "gk";
    public static final String TYPE_CODE_HOTEL = "ho";
    public static final String TYPE_CODE_YOUTH_HOSTEL = "jh";
    public static final String TYPE_CODE_CAMPING_CAR_AREA = "ka";
    public static final String TYPE_CODE_MINI_CAMPING = "mi";
    public static final String TYPE_CODE_RESTAURANT_CAFE = "re";
    public static final String TYPE_CODE_VACATION_CENTRE = "vc";
    public static final String TYPE_CODE_VACATION_LODGINGS = "vl";
    public static final String TYPE_CODE_VACATION_PARK = "vp";
    public static final String TYPE_CODE_STAY_PARK = "vb";
    public static final String TYPE_CODE_LODGING = "lo";

    //-----VARIABLES-----
    private String accessToken = null;
    private Timer refreshTimer = null;
    private GeonamesRepository geonamesRepository;

    //-----CONSTRUCTORS-----
    public TPAccessRepository()
    {
    }

    //-----PUBLIC METHODS-----
    public synchronized Set<Node> findHorecaNear(double latitude, double longitude, double kilometers) throws Exception
    {
        Set<Node> retVal = new HashSet<>();

        Path cachedJsonFile = null;
        FileContext cachedImageFilesystem = null;
        SearchReponse cachedResponse = null;
        String cacheFolderPath = Settings.instance().getTpAccessCacheFolder();
        if (!StringUtils.isEmpty(cacheFolderPath)) {
            Path cacheFolder = Paths.get(cacheFolderPath);
            if (!Files.exists(cacheFolder)) {
                Files.createDirectories(cacheFolder);
            }

            //we'll let them write straight to the media folder
            cachedImageFilesystem = com.beligum.blocks.media.config.Settings.instance().getDefaultMediaFileSystem();

            String args = latitude + "-" + longitude + "-" + kilometers;
            args = org.apache.commons.codec.binary.Base64.encodeBase64String(args.getBytes());
            cachedJsonFile = cacheFolder.resolve("findHorecaNear-" + args + ".json");

            if (Files.exists(cachedJsonFile)) {
                try (InputStream is = Files.newInputStream(cachedJsonFile)) {
                    cachedResponse = Json.read(is, SearchReponse.class);
                }
            }
        }

        if (cachedResponse == null) {
            final int MAX_POSTAL_CODES = 10;
            List<PostalcodeResponse> postalCodes = Settings.instance().getGeonamesRepository().findPostalcodesNear(latitude, longitude, kilometers, MAX_POSTAL_CODES);

            String[] types = {
                            //                        "afbeelding",
                            //                        "Afbeelding",

                            "at",
                            //                        "Attractie",

                            "ca",
                            //                        "Camping",

                            "gk",
                            //                        "Gastenkamer",

                            "ho",
                            //                        "Hotel",

                            "jh",
                            //                        "Jeugdverblijf",

                            "ka",
                            //                        "Kampeerautoterrein",

                            //                        "knooppunten",
                            //                        "Knooppunten",

                            "mi",
                            //                        "Minicamping",

                            //                        "nieuws",
                            //                        "Nieuws",

                            "re",
                            //                        "Restaurant, Café",

                            //                        "themaroute",
                            //                        "Routeproduct",

                            //                        "streekproducten",
                            //                        "Streekproducten",

                            //                        "ti",
                            //                        "Toeristisch Infokantoor",

                            //                        "trajecten",
                            //                        "Trajecten",

                            "vc",
                            //                        "Vakantiecentrum",

                            "vl",
                            //                        "Vakantielogies",

                            "vp",
                            //                        "Vakantiepark",

                            "vw",
                            //                        "Vakantiewoning",

                            "vb",
                            //                        "Verblijfpark",

                            "lo",
                            //                        "Logies",
            };

            Logger.info("Rebuilding TPAccess cache for " + latitude + ", " + longitude + ", " + kilometers);
            Set<Long> encounteredIds = new HashSet<>();
            for (PostalcodeResponse pc : postalCodes) {
                for (String type : types) {
                    Map<String, String> params = new HashMap<>();
                    params.put("field_gemeente", pc.getPlaceName());

                    SearchReponse resp = this.queryRemoteApi(SearchReponse.class, SearchReponse.PATH + "/" + type, params);
                    if (resp != null && resp.getNodes() != null && resp.getNodes().getNode() != null) {
                        if (cachedResponse == null) {
                            cachedResponse = resp;

                            for (Node n : resp.getNodes().getNode()) {
                                Logger.info("Found new TPAccess HORECA node; " + n.getTitle());
                                encounteredIds.add(n.getNid());

                                n.setLocationInfo(pc);
                                n.setTypeCode(type);
                                this.loadAndCacheImages(n, cachedImageFilesystem);
                            }
                        }
                        else {
                            for (Node n : resp.getNodes().getNode()) {
                                //let's filter out doubles
                                if (!encounteredIds.contains(n.getNid())) {
                                    Logger.info("Found new TPAccess HORECA node; " + n.getTitle());
                                    encounteredIds.add(n.getNid());

                                    n.setLocationInfo(pc);
                                    n.setTypeCode(type);
                                    this.loadAndCacheImages(n, cachedImageFilesystem);

                                    cachedResponse.getNodes().getNode().add(n);
                                }
                            }
                        }
                    }
                }
            }

            if (cachedJsonFile != null && cachedResponse != null) {
                try (OutputStream os = Files.newOutputStream(cachedJsonFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                    IOUtils.write(Json.write(cachedResponse), os);
                }
            }

            Logger.info("All done rebuilding TPAccess cache for " + latitude + ", " + longitude + ", " + kilometers);
        }

        if (cachedResponse != null && cachedResponse.getNodes() != null && cachedResponse.getNodes().getNode() != null) {
            for (Node n : cachedResponse.getNodes().getNode()) {
                retVal.add(n);
            }
        }

        return retVal;
    }
    public static boolean isFoodRelatedEntry(Node node)
    {
        return node!=null && (node.getTypeCode().equals(TYPE_CODE_RESTAURANT_CAFE));
    }
    public static boolean isLodgingRelatedEntry(Node node)
    {
        return node!=null && (node.getTypeCode().equals(TYPE_CODE_CAMPING)
               || node.getTypeCode().equals(TYPE_CODE_GUESTROOM)
               || node.getTypeCode().equals(TYPE_CODE_HOTEL)
               || node.getTypeCode().equals(TYPE_CODE_YOUTH_HOSTEL)
               || node.getTypeCode().equals(TYPE_CODE_CAMPING_CAR_AREA)
               || node.getTypeCode().equals(TYPE_CODE_MINI_CAMPING)
               || node.getTypeCode().equals(TYPE_CODE_VACATION_CENTRE)
               || node.getTypeCode().equals(TYPE_CODE_VACATION_LODGINGS)
               || node.getTypeCode().equals(TYPE_CODE_VACATION_PARK)
               || node.getTypeCode().equals(TYPE_CODE_STAY_PARK)
               || node.getTypeCode().equals(TYPE_CODE_LODGING))
                        ;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----
    private static HoReCa createFromTpAccess(Node tpAccessNode)
    {
        HoReCa retVal = null;

        String title = tpAccessNode.getTitle();
        String description = tpAccessNode.getField_omschrijving_nl();
        String address = null;
        String postalCode = null;
        String city = null;
        if (tpAccessNode.getAddress() != null && !tpAccessNode.getAddress().isEmpty()) {
            NodeAddress nodeAddress = tpAccessNode.getAddress().iterator().next();
            if (nodeAddress != null) {
                address = "" + (nodeAddress.getField_straatnaam() == null ? "" : nodeAddress.getField_straatnaam())
                          + " " + (nodeAddress.getField_huisnummer() == null ? "" : nodeAddress.getField_huisnummer());
                postalCode = nodeAddress.getField_postcode();
                city = nodeAddress.getField_gemeente();
            }
        }

        String phoneNumber = null;
        String email = null;
        String website = null;

        MapLocation location = null;
        if (tpAccessNode.getCoordinates() != null && !tpAccessNode.getCoordinates().isEmpty()) {
            NodeCoordinates nodeCoordinates = tpAccessNode.getCoordinates().iterator().next();
            if (nodeCoordinates != null) {
                try {
                    location = new MapLocation(Double.parseDouble(nodeCoordinates.getLatitude()), Double.parseDouble(nodeCoordinates.getLongitude()));
                }
                catch (NumberFormatException e) {
                    Logger.warn("Unable to parse coordinates of TPAccess node with ID " + tpAccessNode.getNid());
                }
            }
        }

        Media[] media = null;
        if (tpAccessNode.getImages() != null && !tpAccessNode.getImages().isEmpty()) {
            List<Media> mediaList = new ArrayList<>();
            for (Image image : tpAccessNode.getImages()) {
                mediaList.add(new com.beligum.thomasmore.mobielegidsen.beans.app.Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/1.jpg")));
            }
        }

        if (tpAccessNode.getTypeCode().equals(TPAccessRepository.TYPE_CODE_HOTEL)) {
            retVal = new Hotel(title, address, postalCode, city, phoneNumber, email, website, location, media, description);
        }
        else if (tpAccessNode.getTypeCode().equals(TPAccessRepository.TYPE_CODE_HOTEL)) {
            retVal = new Restaurant(title, address, postalCode, city, phoneNumber, email, website, location, media, description);
        }

        return retVal;
    }
    private <T extends RemoteRepositoryResponse> T queryRemoteApi(Class<T> type, String path, Map<String, String> params) throws Exception
    {
        T retVal = null;

        ClientConfig config = new ClientConfig();
        Client httpClient = ClientBuilder.newClient(config);

        if (path == null) {
            RemoteRepositoryResponse dummy = type.newInstance();
            path = dummy.getPath();
        }
        UriBuilder builder = UriBuilder.fromUri(Settings.instance().getTpAccessApiBaseUrl())
                                       .path(BASE_PATH)
                                       .path(path)
                                       .replaceQueryParam(ACCESS_TOKEN_PARAM, this.getAccessToken())
                                       .replaceQueryParam(FORMAT_PARAM, FORMAT_JSON);

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
    private void loadAndCacheImages(Node node, FileContext cachedImageFilesystem) throws Exception
    {
        //load and attach the images
        String imagePathBase = "/" + node.getTypeCode() + "/" + node.getNid();
        ImagesReponse imagesReponse = this.queryRemoteApi(ImagesReponse.class, imagePathBase + ImagesReponse.PATH, null);
        if (imagesReponse != null && imagesReponse.getNodes() != null && imagesReponse.getNodes().getNode() != null) {
            for (Image i : imagesReponse.getNodes().getNode()) {
                node.addImage(i);

                URI imageUri = UriBuilder.fromUri(Settings.instance().getTpAccessApiBaseUrl())
                                         .path(BASE_PATH)
                                         .path(imagePathBase + "/image/" + i.getData().mediaObjectId + "/original")
                                         .replaceQueryParam(ACCESS_TOKEN_PARAM, this.getAccessToken())
                                         .replaceQueryParam(FORMAT_PARAM, FORMAT_JSON)
                                         .build();

                ClientConfig config = new ClientConfig();
                Client httpClient = ClientBuilder.newClient(config);
                Response response = httpClient.target(imageUri).request().get();
                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    org.apache.hadoop.fs.Path nodeImages = new org.apache.hadoop.fs.Path(HDFS_CACHE_ROOT_PATH + node.getNid());
                    if (!cachedImageFilesystem.util().exists(nodeImages)) {
                        cachedImageFilesystem.mkdir(nodeImages, FsPermission.getDirDefault(), true);
                    }
                    org.apache.hadoop.fs.Path cachedImage = new org.apache.hadoop.fs.Path(nodeImages, i.getData().mediaObjectId);
                    i.setCachedUrl(cachedImage.toUri());
                    if (!cachedImageFilesystem.util().exists(cachedImage)) {
                        try (OutputStream os = cachedImageFilesystem.create(cachedImage, EnumSet.of(CreateFlag.CREATE, CreateFlag.OVERWRITE))) {
                            IOUtils.copy(response.readEntity(InputStream.class), os);
                        }
                    }
                }
                else {
                    throw new IOException("Error status returned while fetching remote API data;" + response);
                }

            }
        }
    }
    private String getAccessToken() throws IOException
    {
        if (this.accessToken == null) {
            try {
                OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

                URI accessTokenUrl = UriBuilder.fromUri(Settings.instance().getTpAccessApiBaseUrl())
                                               .path(Settings.instance().getTpAccessTokenPath())
                                               .build();

                OAuthClientRequest accessTokenRequest = OAuthClientRequest
                                .tokenLocation(accessTokenUrl.toString())
                                .setGrantType(GrantType.CLIENT_CREDENTIALS)
                                .setClientId(Settings.instance().getTpAccessClientId())
                                .setClientSecret(Settings.instance().getTpAccessClientSecret())
                                //.setCode(code)
                                //.setRedirectURI(redirectUrl)
                                .buildQueryMessage();

                OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.GET);

                if (oAuthResponse.getResponseCode() == Response.Status.OK.getStatusCode()) {
                    this.accessToken = oAuthResponse.getAccessToken();

                    this.refreshTimer = new java.util.Timer();
                    this.refreshTimer.schedule(
                                    new java.util.TimerTask()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            accessToken = null;
                                        }
                                    },
                                    //safety seconds minus 10
                                    (oAuthResponse.getExpiresIn() - 10) * 1000
                    );
                }
                else {
                    throw new IOException("Got error code while requesting for OAuth2 access code; " + oAuthResponse.getResponseCode());
                }
            }
            catch (Exception e) {
                throw new IOException("Error while getting OAuth2 access token", e);
            }
        }

        return this.accessToken;
    }
}
