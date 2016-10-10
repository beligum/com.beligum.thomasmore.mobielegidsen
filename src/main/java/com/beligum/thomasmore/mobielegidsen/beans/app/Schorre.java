package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.Logger;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.*;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by bram on 4/13/16.
 */
public class Schorre extends AbstractRoute
{
    //-----CONSTANTS-----
    private static final int DEFAULT_ZOOM_LEVEL = 15;
    private static final URI POSTER = URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/algemeen/poster.jpg");

    private static final float LENGTH_KMS = 5f;
    private static final float LENGTH_HOURS = 1f;

    private static final URI[] IMAGES = new URI[] {
                    URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/one-world-brug/2.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/droogloodsen/4.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/oude-bagger/1.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/algemeen/tina.jpg"),
                    };
    private static final MapLocation LOCATION = new MapLocation(51.0895, 4.382, 14);

    private static final Accessibility[] ACCESSIBILITY = new Accessibility[] {
                    new DefaultAccessibility("Waar begint de wandeling?",
                                             "Deze wandeling start in het congrescentum De Pitte. Dit is het centrale gebouw midden in het " +
                                             "domein waar je ook de onthaalbalie van De Schorre vindt. (Je wandelt eerst twee bruggetjes over, " +
                                             "volg de pijlen in het domein)."),

                    new DefaultAccessibility("Met de fiets",
                                             "Vanuit alle richtingen bereik je De Schorre makkelijk en snel met de fiets. Kom je via de Rupeldijk, " +
                                             "dan kies je best voor de fietstunnel onder de Kapelstraat (via fietsknooppunt 26). " +
                                             "<a href=\"http://www.fietsnet.be/\" target=\"_blank\">Hier</a> kan je " +
                                             "eenvoudig je fietsroute uitstippelen (geef knooppunt 26 of 24 als eindbestemming in)."),

                    new DefaultAccessibility("Met de trein",
                                             "Het station van Boom ligt op de lijn Antwerpen-Puurs. " +
                                             "Plan <a href=\"http://www.belgianrail.be/\" target=\"_blank\">hier</a> je route met de trein. Vanaf het " +
                                             "station is het 25 min. wandelen naar De Schorre. Je kan ook bus 182 of 295 richting Antwerpen " +
                                             "nemen tot halte De Schorre. Let op: in het weekend en op feestdagen stoppen er geen treinen in " +
                                             "Boom. Dan neem je best de trein tot Antwerpen-Centraal of Mechelen en neem je buslijn 500 tot " +
                                             "halte Schorre."),

                    new DefaultAccessibility("Met De Lijn",
                                             "Er bevinden zich twee haltes nabij De Schorre:" +
                                             "<ul>" +
                                             "<li>Halte 'Boom De Schorre' bevindt zich aan de hoofdingang van het domein in de " +
                                             "Schommelei. Lijnen <a href=\"https://www.delijn.be/nl/lijnen/lijn//1/132/8/BUS\" target=\"_blank\">132</a>, " +
                                             "<a href=\"https://www.delijn.be/nl/lijnen/lijn//1/182/7/BUS\" target=\"_blank\">182</a>, " +
                                             "<a href=\"https://www.delijn.be/nl/lijnen/lijn//1/295/7/BUS\" target=\"_blank\">295</a> en " +
                                             "<a href=\"https://www.delijn.be/nl/lijnen/lijn/1/298/7\" target=\"_blank\">298</a> " +
                                             "bedienen deze halte.</li>" +
                                             "<li>Halte 'Boom Schorre' bevindt zich in de Kapelstraat. Lijn 500 bedient deze halte.</li>" +
                                             "</ul>" +
                                             "<a href=\"https://www.delijn.be/\" target=\"_blank\">Hier</a> vind je dienstregelingen en een reisroute op maat."),

                    new DefaultAccessibility("Met de auto",
                                             "Geef in je GPS ‘Schommelei, Boom’ in. De Schorre is bereikbaar vanaf de A12 en E19:" +
                                             "<ul>" +
                                             "<li>Vanaf E19: neem afrit 7 Kontich en volg de pijlen richting Boom en De Schorre.</li>" +
                                             "<li>Vanaf A12: neem afrit 9 Boom en volg de pijlen richting De Schorre.</li>" +
                                             "</ul>" +
                                             "Parkeren kan op P1 (Schommelei, aan de hoofdingang) en P2 (Kapelstraat). Voor autocars en " +
                                             "personen met een beperking zijn parkeerplaatsen voorzien op P1."
                    ),

                    new DefaultAccessibility("Hoe ziet deze wandeling eruit?",
                                             LENGTH_KMS+" km of "+LENGTH_HOURS+" uur wandelen over voornamelijk verharde wegen."),

                    new DefaultAccessibility("Toegankelijkheid",
                                             "De wandeling is geschikt voor rolstoelgebruikers of kinderwagens."),

                    };

    public static Route INSTANCE = new Schorre();

    //-----VARIABLES-----
    private Map<String, Stop> stops;

    //-----CONSTRUCTORS-----
    public Schorre()
    {
        Stop[] STOPS_ARRAY = new Stop[0];
        try {
            STOPS_ARRAY = new Stop[] {
                            new DefaultStop("Startpunt wandeling De Schorre",
                                            "Startpunt wandeling De Schorre",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Dit is de start van onze wandeling. Onze gids in het bovenstaande filmpje legt je in een notendop uit wat De Schorre zo speciaal maakt.",
                                            null,
                                            null,
                                            null,
                                            new MapLocation(51.087721448164324, 4.3819355964660645, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/reconversie-de-schorre.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/reconversie-de-schorre.jpg")),
                                                            }
                            ),
                            new DefaultStop("Ramp Steenbakkerij",
                                            "Ramp Steenbakkerij",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 16",
                                            "Tussen het groen ligt een betonnen pijlerconstructie verscholen. Het vormt een feëriek decor voor het jaarlijks evenement Putteke Winter.",
                                            "De opgaande ramp onderstut door pijlers is nog een overblijfsel van de ramp van Steenbakkerij Anverreet. Over de ramp lopen sporen waarlangs de wagonnetjes de klei uit de put naar de machinehal brachten. De machinehal is verdwenen, maar verderop is er nog een oude bagger van Anverreet bewaard in één van de waterpartijen.",
                                            null,
                                            "erfgoed, steenbakkerijnijverheid",
                                            new MapLocation(51.087040793866144, 4.386935234069824, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/ramp-steenbakkerij/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/ramp-steenbakkerij/2.jpg")),
                                                            }
                            ),
                            new DefaultStop("Oude Bagger",
                                            "Oude Bagger",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "",
                                            "Aan de rand van deze vroegere kleiput staat nog een oude bagger. In de laatste periode van de kleiopgravingen gebruikte men zulke baggers om de klei van de wanden van de put te schrapen.",
                                            null,
                                            null,
                                            null,
                                            new MapLocation(51.08931858773361, 4.3883514404296875, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/oude-bagger/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/oude-bagger/2.jpg")),
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/impressie-van-de-handmatige-ontginning-fotoreeks.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/impressie-van-de-handmatige-ontginning-fotoreeks.jpg")),
                                                            }
                            ),
                            new DefaultStop("Deltaweide",
                                            "Deltaweide",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 31",
                                            "Van bovenaf heb je hier een goed uitzicht op De Schorre en de ruime omgeving. De deltaweide heet zo omdat hier deltavliegers en parapenters komen oefenen. Tot voor enkele decennia werd hier nog klei afgestoken. In de zomer is hier het hoofdpodium van Tomorrowland.",
                                            null,
                                            null,
                                            "parapent, deltavliegen, teambuilding, steenbakkerijnijverheid",
                                            new MapLocation(51.09285298955352, 4.3861788511276245, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/deltaweide/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/deltaweide/2.jpg")),
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/productieproces-1900-1975.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/productieproces-1900-1975.jpg")),
                                                            }
                            ),
                            new DefaultStop("One World brug",
                                            "One World brug",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 00",
                                            "One World by the People of Tomorrow is een kunstinstallatie van Arne Quinze in Provinciaal Recreatiedomein De Schorre. Ze werd in 2015 ontworpen naar aanleiding van 10 jaar Tomorrowland.",
                                            "De kunstinstallatie is een permanente brug die een functie vervult als wandel- en fietspad doorheen het domein. Ze is geïnspireerd door de Nike van Samothrache, een beeld uit de klassieke oudheid dat de overwinning van de vrijheid voorstelt. De kunstinstallatie verbindt op een symbolische manier personen van over de hele wereld. Iedereen -waar ook ter wereld- kon een persoonlijke boodschap laten vereeuwigen in één van 210.000 planken van de installatie.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("One World brug vervangt stuk historisch erfgoed.",
                                                                                "De brug die de verschillende gebieden van het domein verbindt, is op de plaats gekomen van de voormalige brug of ramp  van steenbakkerij Verstrepen. Hierlangs trok men de klei uit de kleiput met wagonnetjes naar de machinehal. De brug was dringend aan vervanging toe.")
                                            },
                                            "kunstwerk, Arne Quinze, Tomorrowland",
                                            new MapLocation(51.09063601789197, 4.382901191711426, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/one-world-brug/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/one-world-brug/2.jpg")),
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/de-schorre-fotoreeks.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/de-schorre-fotoreeks.jpg")),
                                                            }
                            ),
                            new DefaultStop("Machinehal",
                                            "Machinehal",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 00",
                                            "Een hedendaags congrescentrum in industriële look, uitgerust met de nieuwste multimedia, met een aula voor 120 personen en verschillende vergaderzalen. Een verzorgde catering en combinatiemogelijkheden met buitenactiviteiten zijn een extra troef.",
                                            "Congrescentrum de Pitte is ingericht in de vroegere machinehal van Steenbakkerij Verstrepen. Hier werd tot ca. 1970 de klei gekneed, geperst tot strengen en versneden tot baksteen. De basisstructuur is overeind gebleven bij deze hedendaagse renovatie: stalen structuren, rode baksteen en grote open ruimten zorgen voor een eigentijds loftgevoel en een trendy look. Je kunt er terecht voor congressen, vergaderingen, workshops en netwerkevenementen. De ruime parkeergelegenheid, rustgevende omgeving, verzorgde catering en combinatiemogelijkheden met buitenactiviteiten, maken van congrescentrum De Pitte jouw ideale partner!",
                                            null,
                                            "Congrescentrum, erfgoed, steenbakkerijnijverheid",
                                            new MapLocation(51.088216769500264, 4.3816620111465445, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/machinehal/1.jpg")),
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/productieproces-1975-1990.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/productieproces-1975-1990.jpg")),
                                                            }
                            ),
                            new DefaultStop("Droogloodsen",
                                            "Droogloodsen",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "In deze vlakte stonden vroeger de droogloodsen. Hier werden de kleivormen gedroogd vooraleer ze in de oven gebakken werden. De hele Rupelstreek stond vroeger bijna vol met dergelijke loodsen. De meeste van dergelijke loodsen waren vrij open zodat de wind er makkelijk doorheen kon blazen om beter te drogen. Hier en daar waren er ook droogloodsen waar men met warme lucht de stenen droogde om het proces sneller te latern verlopen. ",
                                            null,
                                            null,
                                            null,
                                            new MapLocation(51.08687568314563, 4.3763190507888785, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/portret-leona.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/portret-leona.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/droogloodsen/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/droogloodsen/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/droogloodsen/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/droogloodsen/4.jpg")),
                                                            }
                            ),
                            new DefaultStop("Afval",
                                            "Afval",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 05",
                                            "De schoorstenen in de Rupelstreek getuigen van een eens florerende baksteenindustrie. Om die stenen te vervaardigen moest men grote hoeveelheden klei delven en dat tast het landschap enorm aan.",
                                            "De schoorstenen in de Rupelstreek getuigen van een eens florerende baksteenindustrie. Om die stenen te vervaardigen moest men grote hoeveelheden klei delven en dat tast het landschap enorm aan.",
                                            null,
                                            "afval",
                                            new MapLocation(51.083441920502544, 4.374532699584961, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/afval.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/afval.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/afval/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/afval/2.jpg")),
                                                            }
                            ),
                            new DefaultStop("Steenkaaien",
                                            "Steenkaaien",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 05",
                                            "De steiger en het ponton werden aangelegd aan de oude 'steenkaai' van Steenbakkerij Van Herck. Hier werd de baksteen getransporteerd naar Antwerpen om van daaruit te vertrekken naar het binnenland, Nederland, Engeland of zelfs de Verenigde Staten.",
                                            "De steiger en het ponton werden met subsidies van Toerisme Vlaanderen aangelegd als toegang tot het Nautisch Bezoekerscentrum. In 2015 waren ze dringend aan renovatie toe. Het ponton werd hersteld; de brug verbreed voor meer comfort voor de passagiers.",
                                            null,
                                            "passagiersvaart, aanlegsteiger",
                                            new MapLocation(51.082783107653015, 4.376506805419922, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/rivier-de-rupel.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/rivier-de-rupel.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/steenkaaien/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/de-schorre/steenkaaien/2.jpg")),
                                                            }
                            ),
                            new DefaultStop("Ringoven en fietsbelevingspunt",
                                            "Ringoven en fietsbelevingspunt",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            "+32 3 880 76 16",
                                            "Je ziet hier nog de overblijfselen van een zogenaamde ringoven. Dit was een van de types ovens waar vroeger de bakstenen in gebakken werden. Men vulde de oven met klei in de vorm van bakstenen, met daartussen brandbaar materiaal. Dit stak men dan in brand om zo de stenen te bakken. Het vuur ging helemaal rond in de oven gedurende enkele weken totdat alle stenen gebakken waren.",
                                            "Tegen 2018 wil de provincie dit gezellig pleintje renoveren tot een ontmoetingsplek voor recreatieve fietsers en wielerfanaten, met onder meer een toeristische balie, een wielercollectie, fietsenverhuur, een fietshersteldienst, elektrische oplaadpunten en een themacafé. De oude ringoven en andere steenbakkersrelicten worden in de nieuwe plannen geïntegreerd. ",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Relicten ringoven De Roeck",
                                                                                "Relicten van een ringoven. Maar voor de helft bewaard, het zuidelijke deel is gesloopt. Een ringoven is een ringvormige gang met toegangsdeuren langs de zijkanten –om de bakstenen in en uit te zetten- en stookgaten bovenaan. Dit nieuwe type oven uit het einde van de 19de eeuw zorgde ervoor dat er continu kon gebakken worden. Het vuur verplaatste zich doorheen de ring. Een ringoven werd nooit gedoofd, tenzij voor herstelwerkzaamheden.")
                                            },
                                            "erfgoed, steenbakkerijnijverheid",
                                            new MapLocation(51.08330375592542, 4.3820321559906, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("http://www.youtube.com/embed/nI3IOSkS7Nw?rel=0&controls=0&&showinfo=0"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/fietsbelevingspunt-en-ringoven.jpg"))
                                                            }
                            ),
                            };
        }
        catch (Exception e) {
            Logger.error("Caught error while creating stop list for " + this.getName(), e);
        }

        this.stops = new LinkedHashMap<>();
        for (Stop stop : STOPS_ARRAY) {
            this.stops.put(stop.getSlug(), stop);
        }
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getName()
    {
        return "De Schorre";
    }
    @Override
    public String getSlug()
    {
        return "de-schorre";
    }
    @Override
    public String getIntroduction()
    {
        return "De Schorre is een put vol pit. Je vindt er een brede waaier aan recreatie- en sportmogelijkheden. Het is een prachtig provinciaal domein om te wandelen en te fietsen!";
    }
    @Override
    public URI getPoster()
    {
        return POSTER;
    }
    @Override
    public URI[] getImages()
    {
        return IMAGES;
    }
    @Override
    public String getDescription()
    {
        return "<p>Provinciaal Recreatiedomein De Schorre is een geslaagde reconversie van een 75 ha grote kleiput naar een gebied met actieve recreatiemogelijkheden. Watersport, avonturentoren, sportvelden, speeltuin en wandelpaden met mooie uitzichtpunten zijn er de troeven. Een derde van het gebied is natuurgebied en een geliefde verzamelplaats voor waterjuffers, libellen, kikkers en watervogels.</p>";
    }
    @Override
    public Region getRegion()
    {
        return Rupelstreek.INSTANCE;
    }
    @Override
    public MapLocation getLocation()
    {
        return LOCATION;
    }
    @Override
    public Map<String, Stop> getStops()
    {
        return stops;
    }
    @Override
    public String getColor()
    {
        return "color-1";
    }
    @Override
    public Accessibility[] getAccessibility()
    {
        return ACCESSIBILITY;
    }
    @Override
    public String getLength()
    {
        return LENGTH_KMS+" km";
    }
    @Override
    public String getDuration()
    {
        return "ca. "+LENGTH_HOURS+" uur";
    }
    @Override
    public SurroundingEntry[] getSurroundingEntries()
    {
        return SurroundingEntry.SURROUNDING_ENTRIES_RUPELSTREEK;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
