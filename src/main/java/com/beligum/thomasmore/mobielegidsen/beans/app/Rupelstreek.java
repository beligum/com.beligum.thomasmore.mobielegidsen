package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.Logger;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Region;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Route;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by bram on 4/13/16.
 */
public class Rupelstreek implements Region
{
    //-----CONSTANTS-----
    public static Region INSTANCE = new Rupelstreek();

    //-----VARIABLES-----
    private Map<String, Route> routes;
    private Video video;
    private Image quoteImage;

    //-----CONSTRUCTORS-----
    public Rupelstreek()
    {
        this.routes = new LinkedHashMap<>();

        Route schorre = new Schorre();
        Route dorpswandeling = new DorpsWandeling();
        Route natuurwandeling = new Natuurwandeling();

        this.routes.put(schorre.getSlug(), schorre);
        this.routes.put(dorpswandeling.getSlug(), dorpswandeling);
        this.routes.put(natuurwandeling.getSlug(), natuurwandeling);

        try {
            this.video = new Video(URI.create("/webhdfs/v1/assets/videos/app/regions/rupelstreek/teaser.mp4"),
                                   URI.create("/webhdfs/v1/assets/videos/app/regions/rupelstreek/teaser.jpg"));

            this.quoteImage = new Image(URI.create("/webhdfs/v1/assets/images/app/regions/rupelstreek/quote.jpg"));
        }
        catch (Exception e) {
            Logger.error("Caught error while initializing Rupelstreek instance, this shouldn't happen", e);
        }
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getName()
    {
        return "De Rupelstreek";
    }
    @Override
    public String getSlug()
    {
        return "de-rupelstreek";
    }
    @Override
    public Video getVideo()
    {
        return this.video;
    }
    @Override
    public String getTagline()
    {
        return "Ontspannen langs het water";
    }
    @Override
    public String getQuote()
    {
        return "De Rupelstreek is voornamelijk bekend om haar natuur, wandel- en fietspaden, cultuur en gastronomie";
    }
    @Override
    public Image getQuoteImage()
    {
        return this.quoteImage;
    }
    @Override
    public String getIntroduction()
    {
        return "De Rupelstreek ligt in het Scheldeland, centraal gelegen in Vlaanderen tussen de steden Gent, Antwerpen en Brussel. " +
               "Eeuwenlang werd er klei ontgonnen in de Rupelstreek. Vandaag kan je er volop genieten van de natuur. " +
               "Je kan er wandelen of fietsen langs rustige paadjes en authentieke plekken ontdekken.";
    }
    @Override
    public Map<String, Route> getRoutes()
    {
        return routes;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
