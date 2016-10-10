package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.Logger;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.*;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Bram on 18/05/16.
 */
public class Natuurwandeling extends AbstractRoute
{

    //-----CONSTANTS-----
    private static final int DEFAULT_ZOOM_LEVEL = 15;
    private static final MapLocation LOCATION = new MapLocation(51.04849, 4.2375, 14);
    private static final URI POSTER = URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/algemeen/poster.jpg");

    private static final float LENGTH_KMS = 7.5f;
    private static final float LENGTH_HOURS = 2.5f;

    private static final URI[] IMAGES = new URI[] {
                    URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/algemeen/pad.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/fauna-en-flora/5.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/algemeen/luchtfoto.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/algemeen/paddestoel.jpg"),
                    };

    private static final Accessibility[] ACCESSIBILITY = new Accessibility[] {
                    new DefaultAccessibility("Waar begint de wandeling?",
                                             "Deze wandeling start aan poort De Beukelaer ter hoogte van Kardinaal Cardijnstraat, huisnummer 55 in Terhagen"),
                    new DefaultAccessibility("Met de auto",
                                             "Er is een parking voorzien vlakbij poort De Beukelaer."),
                    new DefaultAccessibility("Met het openbaar vervoer",
                                             "Buslijn 500 vanuit Antwerpen stopt vlakbij poort De Beukelaer."),
                    new DefaultAccessibility("Hoe ziet deze wandeling eruit?",
                                             LENGTH_KMS+" km of "+LENGTH_HOURS+" uur wandelen over voornamelijk onverharde wegen."),
                    new DefaultAccessibility("Toegankelijkheid",
                                             "Niet geschikt voor kinderwagens of rolstoelgebruikers. Let op: ‘s zomers kunnen er veel muggen zitten."),
                    };

    //-----VARIABLES-----
    private Map<String, Stop> stops;

    //-----CONSTRUCTORS-----

    public Natuurwandeling()
    {
        Stop[] STOPS_ARRAY = new Stop[0];
        try {
            STOPS_ARRAY = new Stop[] {
                            new DefaultStop("Poort de Beukelaer",
                                            "Poort de Beukelaer",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Deze historische poort vormde vroeger de toegang tot de lager gelegen kleiput met droogloodsen van steenbakkerij De Beukelaer.",
                                            "De poort van De Beukelaer was een belangrijk herkenningspunt in Terhagen. Achter de poort leiden 2 trappen naar de lager gelegen kleiput.  Meterslange rijen droogloodsen zorgden voor het drogen van de gevormde stenen. ",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Steenbakkerij De Beukelaer",
                                                                                "De Beukelaer was één van de belangrijkste steenbakkerijen in Terhagen. Samen met de gebroeders Landuyt ontgonnen zij de kleiputten tussen Terhagen en Boom.")
                                            },
                                            null,
                                            new MapLocation(51.080788, 4.395820, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/poort-de-beukelaer.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/poort-de-beukelaer.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/4.jpg")),
                                                            }
                            ),
                            new DefaultStop("Pioniersbos",
                                            "Pioniersbos",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De Rupelstreek was jarenlang synoniem voor baksteenindustrie en kleiontgining. De enorme kleiputten zijn vandaag deels door de natuur heroverd en uitgegroeid tot waardevolle natuurgebieden.",
                                            "Het bosgebied in de Rupelstreek bestaat vandaag voornamelijk uit pioniersbos. Pioneirsbos is bos dat bestaat uit lichtminnende boomsoorten die hun zaad via de wind verspreiden.  Op die manier werden de met grond opgehoogde kleiputten, door berken, wilgen, essen en abelen gekoloniseerd.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Vliegenzwam",
                                                                                "Een veelvoorkomende paddenstoel in het bos is de vliegenzwam. Door zijn rode hoed en witte stippen is hij de best gekende paddenstoel. Dat de vliegenzwam veelvuldig voorkomt in het bos is niet zo verwonderlijk. Vliegenzwammen en berken leven in symbiose met elkaar. Beide houden van zure humusrijke grond. Onder de grond zijn de schimmeldraden van de paddenstoel vergroeid met de haarwortels van de boom. De paddenstoel levert vitamienen in ruil voor suikers van de boomwortels.")
                                            },
                                            null,
                                            new MapLocation(51.08201813160048, 4.398211240768433, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/pioniersbos.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/pioniersbos.jpg")),
                                                            }
                            ),
                            new DefaultStop("Hoogstraat en uitkijkpunt",
                                            "Hoogstraat en uitkijkpunt",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De Hoogstraat is een hoger gelegen weg die uitmondt op een panoramisch uitkijkpunt over de kleiputten. Aan weerszijden werd alle klei  weggegraven voor de baksteenproductie.",
                                            "De Hoogstraat -door de lokale bevolking 'de nieuwe steenweg' genoemd- was belegd met lange, smalle rode bakstenen. Hij verbond de dorpskern van Terhagen  met enkele hoger gelegen -nu verdwenen- gehuchtjes.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Verdwenen gehuchtjes",
                                                                                "Boven op de klei-cuesta lagen tot het midden van de 20ste eeuw enkele kleine gehuchtjes: Wildernis, de Mussenpotten, Eikerveld, den Drijhoek. In 1951 hebben de bewoners hun  typische volkswijk voorgoed (moeten) verlaten. De steenbakkers kochten de arbeiderswoningen, winkeltjes, cafeetjes en gronden op omwille van de kostbare kleigrond. In de jaren 1970 is dit gebied volledig verdwenen onder de schoepraderen van de kleibaggers. Vraag bij toerisme Rupelstreek de wandeling \"Verdwenen gehuchtjes\".")
                                            },
                                            null,
                                            new MapLocation(51.086823, 4.401092, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/hoogstraat-en-uitkijkpunt.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/hoogstraat-en-uitkijkpunt.jpg")),
                                                            }
                            ),
                            new DefaultStop("Moerasgebiedjes",
                                            "Moerasgebiedjes",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Rond de baggerputten vind je pioniersbossen, rietvelden, grote vijvers en tientallen kleinere poelen. Het zijn ideale biotopen voor riet- en watervogels.",
                                            "Dit gebied is ontstaan na opvulling van de kleiputten met zandoverschotten van de uitgraving van de sluis van Wintam. De poelen zijn een geliefdkoosd biotoop voor enkele van onze zeldzame amfibieën: de kamsalamander en de rugstreeppad.  Provincie Antwerpen, steenfabriek Wienerberger en Natuurpunt werken samen voor de instandhouding van deze amfibieën. Ook  zangvogels zoals  eerder zeldzame blauwborst voelen zich hier thuis.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Kamsalamander",
                                                                                "De kamsalamander is de grootste inheemse watersalamander. Mannetjes lijken in de paartijd met hun hoge, gekartelde rugkam wel een klein waterdraakje. Elke kamsalamander heeft een uniek vlekkenpatroon op de buik. Zo'n zijn ze individueel te onderscheiden.  Deze wonderlijke diertjes zijn uiterst bedreigd ondermeer door het verdwijnen van hun habitat.")
                                            },
                                            null,
                                            new MapLocation(51.08937418337083, 4.398736953735352, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/moerasgebiedjes.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/moerasgebiedjes.jpg")),
                                                            }
                            ),
                            new DefaultStop("Fauna en Flora",
                                            "Fauna en Flora",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Ook vandaag wordt de Boomse klei ontgonnen. Dit biedt een uniek habitat voor enkele diersoorten. ",
                                            "De firma Wienerberger is een moderne steenbakkerij en wereldspeler. De industriele activiteit creëert een bijzonder landschap waar ondermeer de oeverzwaluw en de rugstreeppad zich thuisvoelt.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Oeverzwaluw",
                                                                                "De oeverzwaluw is te vinden in waterrijke gebieden. Ten opzichte van andere zwaluwsoorten is hij eerder klein van formaat. Hij broedt in tunnels die hij uitgraaft in zand of grind in steile oevers, afgravingen en gronddepots.  De oeverzwaluw nestelt in kolonies van tot wel meer dan 50 broedende paartjes.  Door het gebrek aan steile broedwanden gaan de aantallen sterk achteruit maar hier vindt hij een mooie leefomgeving.")
                                            },
                                            null,
                                            new MapLocation(51.09084154709013, 4.4000619649887085, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/fauna-en-flora.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/natuurwandeling/fauna-en-flora.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/fauna-en-flora/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/fauna-en-flora/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/fauna-en-flora/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/fauna-en-flora/4.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/fauna-en-flora/5.jpg")),
                                                            }
                            ),
                            new DefaultStop("Kleine Steylen",
                                            "Kleine Steylen",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "In de Kleine Steylen is het oorspronkelijk landschap van de Rupelstreek zichtbaar.",
                                            "Onder je voeten ligt een kleipakket van 40 meter diep. Het landbouwgebied ligt op de cuestarug. Je ziet verschillende kleine landschapselementen: grachten, bomenrijen (knotwilg, populier, zomereik en es), hagen, houtkanten en poelen.  Dit gebied is bijzonder waardevol als biotoop. Natuurpunt plaatste hier nestkasten voor torenvalken, steenuilen en kerkuilen. De bloemrijke bermen trekken insecten, amfibieën, vogels en kleine zoogdieren aan.",
                                            null,
                                            null,
                                            new MapLocation(51.09480707097512, 4.392634928226471, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/kleine-steylen/1.jpg")),
                                                            }
                            ),
                            new DefaultStop("De Schorre",
                                            "De Schorre",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Het Provinciaal Recreatiedomein De Schorre is een omgevormde kleiput.",
                                            "Grote delen van De Schorre waren vroeger eigendom van de steenbakkerijen. Op het domein zijn tal van relichten terug te vinden, die verwijzen naar het steenbakkerijverleden: kleibaggers, gebouwen, spoorwegramp,... . Ongeveer 1/3 van het domein werd behouden als natuurzone. Hier vind je pioniersvegetatie die spontaan is gegroeid, nadat de steenbakkers hun activiteiten in de kleiput hadden gestopt.",
                                            null,
                                            null,
                                            new MapLocation(51.09318316772909, 4.385996460914612, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/productieproces-1900-1975.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/productieproces-1900-1975.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/de-schorre/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/de-schorre/2.jpg")),
                                                            }
                            ),
                            new DefaultStop("Fietsbelevingspunt en ringoven",
                                            "Fietsbelevingspunt en ringoven",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Het fietsbelevingspunt vormt via een tunnel onder de Kapelstraat, de verbinding tussen de Rupeldijk en het recreatiedomein De Schorre. Het is een gezellig pleintje waar nog de oude restanten van een ringoven en schouw staan. In mei vindt hier jaarlijks de Kunstenaarsmarkt plaats.",
                                            "Tegen 2018 wil de provincie dit gezellig pleintje renoveren tot een ontmoetingsplek voor recreatieve fietsers en wielerfanaten, met onder meer een toeristische balie, een wielercollectie, fietsenverhuur, een fietshersteldienst, elektrische oplaadpunten en een themacafé. De oude ringoven en andere steenbakkersrelicten worden in de nieuwe plannen geïntegreerd.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Ringoven",
                                                                                "Een ringoven is een ringvormige gang met toegangsdeuren langs de zijkanten -om de bakstenen in en uit te zetten- en stookgaten bovenaan. Dit nieuwe type oven uit het einde van de 19de eeuw zorgde ervoor dat er continu kon gebakken worden. Het vuur verplaatste zich doorheen de ring. Een ringoven werd nooit gedoofd, tenzij voor herstelwerkzaamheden.")
                                            },
                                            null,
                                            new MapLocation(51.08314537163425, 4.381699562072754, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("http://www.youtube.com/embed/nI3IOSkS7Nw?rel=0&controls=0&&showinfo=0"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/de-schorre/fietsbelevingspunt-en-ringoven.jpg"))
                                            }
                            ),
                            new DefaultStop("De Rupel",
                                            "De Rupel",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De Rupel is de rivier die de 5 Rupelgemeenten verbindt: Rumst, Boom, Niel, Schelle en Hemiksem.",
                                            "De Rupel is met zijn 12 km de kortste rivier van België. Ze heeft ook geen natuurlijke bron maar ontstaat in Rumst aan het drierivierenpunt, aan de samenvloeiing van Dijle en Nete. Ter hoogte van Schelle mondt ze uit in de Schelde. De Rupel heeft gedurende eeuwen voor de bloei van de Rupelstreek gezorgd. Los- en laadkades voor de steenbakkerijen, scheepswerven, maalderijen en brouwerijen... zorgden voor heel wat tewerkstelling en activiteit. De Rupel kent door zijn getijdenwerking ook een uniek ecosysteem van slikken en schorren.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Rupel als transportader",
                                                                                "In de 19de en de 20ste eeuw was de Rupel een bijzonder belangrijke transportader. De steenbakkerijen en de daarmee verbonden nijverheden zoals brouwerijen en scheepswerven kenden een steile opgang.  De grondstoffen en afgewerkte producten werden via de Rupel, de Vliet, de Schelde en zo langs het verdere rivierennetwerk landinwaarts verscheept." +
                                                                                "Nu zetten Steenbakkerij NV Wienerberger in Rumst en Betonfabriek NV Coeck in Niel de Rupel om duurzaamheidsredenen opnieuw in als transportader, voornamelijk voor palletvervoer. Zo halen zij jaarlijks minstens 30.000 vrachtwagens van de baan.")
                                            },
                                            null,
                                            new MapLocation(51.081959157150536, 4.384145736694336, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/rupel.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/rupel.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/de-rupel/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/de-rupel/2.jpg")),
                                            }
                            ),
                            new DefaultStop("Tunnel",
                                            "Tunnel",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Om de kleiputten en de verschillende bedrijfsgebouwen met elkaar te verbinden werden in de Rupelstreek meer dan 30 tunnels aangelegd onder het wegennet. Enkele van hen zijn mooi gerestaureerd en vormen nu een recreatieve verbinding voor fietsers en wandelaars.",
                                            "Door het afgraven van de kleiwand kwamen straten hoger te liggen. Om de zware klei en de gedroogde stenen makkelijker naar de productieplaatsen en steenovens langs de Rupel te brengen, werden onder de weg tunnels gegraven.",
                                            null,
                                            null,
                                            new MapLocation(51.08292632863582, 4.388456046581268, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/tunnel/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/tunnel/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/tunnel/3.jpg")),
                                                            }
                            ),
                            new DefaultStop("Kleiputten van Terhagen",
                                            "Kleiputten van Terhagen",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De kleiputten van Terhagen vormen een uniek industrieel landschap om te ontdekken.",
                                            "Het grondgebied Terhagen was bijna uitsluitend in handen van steenbakkers die er de Boomse klei ontgonnen. De kleiputten hebben diepen sporen nagelaten in het landschap. Door de opkomst van spontane natuur zijn het nu natuur- en wandelgebieden.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Steenbakkerij - productieproces",
                                                                                "De productie van baksteen verloopt steeds volgens dezelfde vier stappen, of ze nu manueel of machinaal worden gemaakt. Eerst wordt de klei afgegraven en vermengd met o.a. water en zand om het juiste mengsel te verkrijgen. Vervolgens wordt van dit nog natte mengsel steen gevormd. De gevormde stenen worden gelijkmatig gedroogd en vervolgens op hoge temperatuur gebakken.")
                                            },
                                            null,
                                            new MapLocation(51.083536276561965, 4.397873282432556, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/3.jpg")),
                                                            }
                            ),
                            new DefaultStop("Paarden",
                                            "Paarden",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Het industrieel proces kon vroeger niet zonder werkdieren. Paarden speelden een belangrijke rol als lastdier.",
                                            "Mensen en paarden hebben van oudsher een sterke band. Paarden werden in de negentiende eeuw als trek- en lastdier in de landbouw en de industrie ingezet. In dit industriegebied hadden de mensen het echter niet breed. Daarom werden de paarden na het einde van hun nuttige leven geslacht en geconsumeerd. Omdat het vlees door de ouderdom taai was, ontstond een typisch Vlaams volksgerecht: 'schep'; paardenstoofveels dat ook nu nog in de Rupelstreek met smaak wordt gegeten.",
                                            null,
                                            null,
                                            new MapLocation(51.08313863186515, 4.396886229515076, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/paarden/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/paarden/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/natuurwandeling/paarden/3.jpg")),
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

    @Override
    public String getName()
    {
        return "Natuurwandeling";
    }
    @Override
    public String getSlug()
    {
        return "natuurwandeling";
    }
    @Override
    public String getIntroduction()
    {
        return "Een tweede lus langs de Rupelkleiroute die het bos induikt en alle geheimen van de kleiontginning en de baksteenindustrie prijsgeeft.";
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
        return "<p>De kleiontginning liet een geschonden landschap achter. Kleiwinningsputten werden uitgegraven en gedeeltelijk weer opgevuld. Op sommige plekken wordt nog steeds klei ontgonnen. Dat veroorzaakt een dynamiek met een specifieke fauna en flora. Er zijn moerassen waar watervogels, libellen en amfibieën goed gedijen. Maar ook zeldzame soorten als de rugstreeppad en de oeverzwaluw vinden er hun plek. De wandeling heeft zowel aandacht voor de bijzondere natuur als de vroegere bakkersteennijverheid.</p>";
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
        return "color-3";
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
}
