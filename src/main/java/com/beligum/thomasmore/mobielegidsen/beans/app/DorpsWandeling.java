package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.Logger;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.*;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Bram on 18/05/16.
 */
public class DorpsWandeling extends AbstractRoute
{
    //-----CONSTANTS-----
    private static final int DEFAULT_ZOOM_LEVEL = 15;
    private static final MapLocation LOCATION = new MapLocation(51.079687, 4.399300, 16);
    private static final URI POSTER = URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/algemeen/poster.jpg");

    private static final float LENGTH_KMS = 2f;
    private static final float LENGTH_HOURS = 1.5f;

    private static final URI[] IMAGES = new URI[] {
                    URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/villa-gabriella/1.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/de-rupel/2.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/3.jpg"),
                    URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/1.jpg"),
                    };

    public static Route INSTANCE = new DorpsWandeling();

    private static final Accessibility[] ACCESSIBILITY = new Accessibility[] {
                    new DefaultAccessibility("Waar begint de wandeling?",
                                             "Deze wandeling start aan poort De Beukelaer ter hoogte van Kardinaal Cardijnstraat, huisnummer 55 in Terhagen"),
                    new DefaultAccessibility("Met de auto",
                                             "Er is een parking voorzien vlakbij poort De Beukelaer."),
                    new DefaultAccessibility("Met het openbaar vervoer",
                                             "Buslijn 500 vanuit Antwerpen stopt vlakbij poort De Beukelaer."),
                    new DefaultAccessibility("Hoe ziet deze wandeling eruit?",
                                             LENGTH_KMS+" km of "+LENGTH_HOURS+" uur wandelen over voornamelijk verharde wegen."),
                    new DefaultAccessibility("Toegankelijkheid",
                                             "De wandeling is niet geschikt voor rolstoelgebruikers of kinderwagens."),
                    };

    //-----VARIABLES-----
    private Map<String, Stop> stops;

    //-----CONSTRUCTORS-----

    public DorpsWandeling()
    {
        Stop[] STOPS_ARRAY = new Stop[0];
        try {
            STOPS_ARRAY = new Stop[] {
                            new DefaultStop("Poort de Beukelaer",
                                            "Poort de Beukelaer",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Deze historische poort vormde vroeger de toegang tot de lager gelegen kleiput met droogloodsen van steenbakkerij De Beukelaer.",
                                            "De poort van De Beukelaer was een belangrijk herkenningspunt in Terhagen. Achter de poort leiden 2 trappen naar de lager gelegen kleiput.  Meterslange rijen droogloodsen zorgden voor het drogen van de gevormde stenen.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Steenbakkerij De Beukelaer",
                                                                                "De Beukelaer was één van de belangrijkste steenbakkerijen in Terhagen. Samen met de gebroeders Landuyt ontgonnen zij de kleiputten tussen Terhagen en Boom.")
                                            },
                                            null,
                                            new MapLocation(51.080786392476384, 4.395832121372223, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/poort-de-beukelaer.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/poort-de-beukelaer.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/poort-de-beukelaer/4.jpg")),
                                                            }
                            ),
                            new DefaultStop("Gemeentehuis",
                                            "Gemeentehuis",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Het voormalige gemeentehuis van Terhagen in Vlaamse renaissancestijl (1910) staat in de schaduw van de Sint-Jozefkerk.",
                                            "De moraliserende gevelopschriften zetten de arbeiders tot werken aan. De boodschap was duidelijk: ledigheid is des duivels oorkussen ('Rust Roest') en heethoofden werden niet getolereerd ('Eerst Raad dan Daad').",
                                            null,
                                            null,
                                            new MapLocation(51.08040557456448, 4.396255910396576, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/gemeentehuis/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/gemeentehuis/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/gemeentehuis/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/gemeentehuis/4.jpg")),
                                                            }
                            ),
                            new DefaultStop("Kerk Terhagen",
                                            "Kerk Terhagen",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De parochiekerk Sint-Jozef is een neo-gotische kerk uit 1884, volledig uitgevoerd in de lokale Boomse baksteen.",
                                            "Een zware cholera-epidemie in 1859 maakte zoveel slachtoffers dat de steenbakkerijen het werk moesten neerleggen. Velen stierven zonder de ziekenzalving omdat er geen pastoor of kerk in de buurt was. De inwoners van Terhagen besloten daarom in 1860 zelf een kerk te bouwen en brachten heel wat fondsen bij elkaar. Onenigheid met de gemeente Rumst leidde ertoe dat de bouw van de kerk pas 15 jaar later startte.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Cholera epidemie",
                                                                                "In de Rupelstreek lag de kindersterfte enorm hoog. In 1859 stierf meer dan de helft van de kinderen tussen 0 en 5 jaar. In 1897 liep dit zelfs op tot bina driekwart van de kinderen (70%). De belangrijkste redenen van overlijden waren: cholera, stuipen, longziekten, roodvonk en kroep. Allemaal te wijten aan de slechte hygiënische omstandigheden in de kleine arbeiderswoningen en een gebrek aan gezonde voeding.")
                                            },
                                            null,
                                            new MapLocation(51.08009047064795, 4.396813809871674, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/kerk-terhagen.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/kerk-terhagen.jpg")),
                                                            }
                            ),
                            new DefaultStop("Museum Rupelklei",
                                            "Museum Rupelklei",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Wie meer wil weten over leven en werken in 'het geleeg' is hier welkom. Ook het geologisch verhaal wordt verteld.",
                                            "Levensgrote beelden en mooie muurschilderingen brengen het verhaal van de baksteenproductie en het leven eromheen. Uniek is de grote collectie fossielen, gevonden in de Boomse klei.",
                                            null,
                                            null,
                                            new MapLocation(51.07795040330964, 4.394681453704834, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("http://www.youtube.com/embed/cZ9ZtnpjWiM?rel=0&controls=0&&showinfo=0"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/museum-rupelklei.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/museum-rupelklei/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/museum-rupelklei/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/museum-rupelklei/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/museum-rupelklei/4.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/museum-rupelklei/5.jpg")),
                                                            }
                            ),
                            new DefaultStop("Kasteel De Bocht",
                                            "Kasteel De Bocht",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Vanaf de dijk doemt aan de overkant van de Rupeloever de imposante toren van het kasteel  De Bocht op.",
                                            "Wegens economische collaboratie werd het kasteeldomein na de tweede wereldoorlog door de Belgische overheid aangeslagen. Het ministerie van openbare werken werd de eigenaar. Het kasteel verviel al snel. Het domein, zo'n 4 hectare groot, werd gebruikt als zandwinningsgebied voor de aanleg van de E-19 en voor het dempen van een aantal verlaten kleigroeven. Zo ontstonden de recreatievijver de Bocht en de watersportbaan Hazewinkel. De toren werd recent gerestaureerd. ",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Hazewinkel",
                                                                                "Bloso-centrum Hazewinkel geniet internationale faam voor roeien en kajak. Vanuit het Bloso-centrum starten talrijke bewegwijzerde wandel-, fiets- en mountainbikeroutes.")
                                            },
                                            null,
                                            new MapLocation(51.077097718982145, 4.395478069782257, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kasteel-de-bocht/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kasteel-de-bocht/2.jpg")),
                                                            }
                            ),
                            new DefaultStop("De Rupel",
                                            "De Rupel",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De Rupel is de rivier die de 5 Rupelgemeenten verbindt: Rumst, Boom, Niel, Schelle en Hemiksem.",
                                            "De Rupel is met zijn 12 km de kortste rivier van België. Ze heeft ook geen natuurlijke bron maar ontstaat in Rumst aan het drierivierenpunt, aan de samenvloeiing van Dijle en Nete. Ter hoogte van Schelle mondt ze uit in de Schelde. De Rupel heeft gedurende eeuwen voor de bloei van de Rupelstreek gezorgd. Los- en laadkades voor de steenbakkerijen, scheepswerven, maalderijen en brouwerijen... zorgden voor heel wat tewerkstelling en activiteit. De Rupel kent door zijn getijdenwerking een uniek ecosysteem van slikken en schorren.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Rupel als transportader",
                                                                                "In de 19de en de 20ste eeuw was de Rupel een bijzonder belangrijke transportader. De steenbakkerijen en de daarmee verbonden nijverheden zoals brouwerijen en scheepswerven kenden een steile opgang. De grondstoffen en afgewerkte producten werden via de Rupel, de Vliet, de Schelde en zo langs het verdere rivierennetwerk landinwaarts verscheept. Nu zetten Steenbakkerij NV Wienerberger in Rumst en Betonfabriek NV Coeck in Niel de Rupel om duurzaamheidsredenen opnieuw in als transportader. Zo halen zij jaarlijks minstens 30.000 vrachtwagens van de baan.")
                                            },
                                            null,
                                            new MapLocation(51.075892812163794, 4.400990009307861, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/rupel.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/rupel.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/de-rupel/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/de-rupel/2.jpg")),
                                                            }
                            ),
                            new DefaultStop("Villa Gabriella",
                                            "Villa Gabriella",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "Villa Gabriella (1902) was de herenwoning van Jan Baptist Lamberts, kruidenier en eigenaar van een pantoffelfabriek in Terhagen.",
                                            "De villa werd genoemd naar de jongste dochter Gabriella. Het herenhuis in eclectische stijl is versierd met zeer mooie sgrafitto-panelen.  Jan Baptist Lamberts was bij de bevolking beter gekend als Tist de Goddo. Hij kwam meermaals op voor de rechten van de arbeiders en was daarom niet graag gezien bij de steenbakkersbazen. Berucht was de fanfare van Tist de Goddo. Het was de fanfare verboden om door de straten te trekken. De fanfare speelde dan maar op het dak van de villa.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Sgraffiti",
                                                                                "De bel-etage borstweringen zijn versierd met zeer mooie sgraffiti (ingekraste en ingekleurde lijntekeningen) in jugendstil stijl met o.a. een afbeelding van een telefoon. De taferelen symboliseren de handel en nijverheid.")
                                            },
                                            null,
                                            new MapLocation(51.07926815727101, 4.3990373611450195, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/villa-gabriella.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/villa-gabriella.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/villa-gabriella/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/villa-gabriella/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/villa-gabriella/3.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/villa-gabriella/4.jpg")),

                                                            }
                            ),
                            new DefaultStop("Pantoffelnijverheid",
                                            "Pantoffelnijverheid",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De pantoffelnijverheid is in de ganse Rupelstreek een belangrijk stukje geschiedenis. Van repen stof werden 'sloefen' gebreid om de voet comfortabeler in een laars of vooral klomp te steken.",
                                            "Het ambacht van pantoffelbreisters paste in een lange traditie van thuisarbeid. Het was een ideale groepsactiviteit tijdens de wintermaanden, wanneer er op de steengelagen geen werk was voor de vrouwen. De fabrikant leverde eenmaal per week het materiaal aan de thuiswerksters en haalde de afgewerkte sloefen op. Later ontstonden echte sloefenfabrieken, werkhuizen waar de vrouwen werden verzameld. Terhagen telde 7 pantoffelateliers, Boom 4, Niel 9. Ook Schelle en Hemiksem hadden enkele ateliers.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Het maken van een pantoffel",
                                                                                "De pantoffels werden gemaakt uit vodden. Hiervoor werden vooral de wollen Duitse legerjassen gebruikt die na WOI overbleven. De mantels werden eerst in repen gesneden, aan elkaar genaaid en op een bol gedraaid. Vervolgens werden op een houten leest  nageltjes ingeklopt. Dit vormde het basisstramien om nadien met één lange reep een sloef te rijgen. Met een rijgnaald werd dan de reep stof rond de leest tot een sloef geweven.")
                                            },
                                            null,
                                            new MapLocation(51.07951754893769, 4.400751292705536, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/pantoffelnijverheid.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/pantoffelnijverheid.jpg")),
                                                            }
                            ),
                            new DefaultStop("Geboortehuis Piet Van Aken",
                                            "Geboortehuis Piet Van Aken",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "In het begin van de Hoogstraat vallen enkele mooi gerestaureerde arbeiderswoningen in het oog, waaronder ook nr. 13, het geboortehuis van Piet Van Aken.",
                                            "De vele steenmakers woonden met hun gezinnen in  rijen of 'roten'  kleine arbeiderswoningen, tussen de steenfabrieken en de steenovens. De huisjes vormden een onderdeel van het werkcontract. Ze waren klein met weinig voorzieningen. Water haalde men aan de dorpspomp. Afvalwater werd in een open goot gekapt. De toiletten -gemakken- stonden buiten met een aantal naast elkaar. Pas in de jaren 1950, tijdens de hoogdagen van de steenbakkerijnijverheid, verhoogde het comfort en konden de arbeiders ook zelf een kleine woning kopen.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Piet Van Aken",
                                                                                "Piet Van Aken (1920-1984) was een sociaal geëngageerd schrijver, vergroeid met zijn geboortdorp Terhagen. Hij beschreef het dorp toen de klei nog gestoken werd. Hij beschreef het in de hoogdagen van de steennijverheid en hij beschreef het toen de ovens waren gedoofd en de werkloosheid de streek in zijn greep kreeg. Piet Van Aken was tijdens zijn leven al een gevierd schrijver. In zijn dorp wordt hij tot vandaag beschouwd als de schrijver 'van bij ons', 'onze Piet'.")
                                            },
                                            null,
                                            new MapLocation(51.080287621478064, 4.400646686553955, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/geboortehuis-piet-van-aken.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/geboortehuis-piet-van-aken.jpg")),
                                                            }
                            ),
                            new DefaultStop("Hoogstraat en uitkijkpunt",
                                            "Hoogstraat en uitkijkpunt",
                                            "Schommelei", "1/1", "2850", "Boom",
                                            null,
                                            "De Hoogstraat is een hoger gelegen weg die uitmondt op een panoramisch uitkijkpunt over de kleiputten. Aan weerszijden werd alle klei  weggegraven voor de baksteenproductie.",
                                            "De Hoogstraat -door de lokale bevolking 'de nieuwe steenweg' genoemd- was belegd met lange, smalle rode bakstenen. Hij verbond de dorpskern van Terhagen met enkele hoger gelegen -nu verdwenen- gehuchtjes.",
                                            new Stop.SubSection[] {
                                                            new Stop.SubSection("Verdwenen gehuchtjes",
                                                                                "Boven op de klei-cuesta lagen tot het midden van de 20ste eeuw enkele kleine gehuchtjes: Wildernis, de Mussenpotten, Eikerveld, den Drijhoek. In 1951 hebben de bewoners hun woningen voorgoed (moeten) verlaten. De steenbakkers kochten de arbeiderswoningen, winkeltjes, cafeetjes en gronden op omwille van de kostbare kleigrond. In de jaren 1970 is dit gebied volledig verdwenen onder de schoepraderen van de kleibaggers. Meer weten: vraag bij toerisme Rupelstreek de wandeling \"Verdwenen gehuchtjes\".")
                                            },
                                            null,
                                            new MapLocation(51.08682345412154, 4.401078522205353, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/hoogstraat-en-uitkijkpunt.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/hoogstraat-en-uitkijkpunt.jpg")),
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
                                            new MapLocation(51.08343181091334, 4.398007392883301, DEFAULT_ZOOM_LEVEL),
                                            new Media[] {
                                                            new Video(URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen.mp4"),
                                                                      URI.create("/webhdfs/v1/assets/videos/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/1.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/2.jpg")),
                                                            new Image(URI.create("/webhdfs/v1/assets/images/app/routes/dorpswandeling-terhagen/kleiputten-van-terhagen/3.jpg")),
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
        return "Dorpswandeling Terhagen";
    }
    @Override
    public String getSlug()
    {
        return "dorpswandeling-terhagen";
    }
    @Override
    public String getIntroduction()
    {
        return "Deze wandeling over de paden van de Rupelkleiroute loopt dwars door Terhagen en laat je enkele pareltjes van de Rupelstreek ontdekken.";
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
        return "<p>Terhagen is met baksteennijverheid vergroeid. Sporen van kleiontginning en de baksteenindustrie bepalen tot vandaag het uitzicht van het dorp en zijn omgeving. De wandeling brengt je langs talrijke relicten: steenbakkerijen, werkmanshuisjes, meesterswoningen, kleiputten en verdwenen gehuchtjes. Deze wandeling neemt je mee langs de sporen van een niet zo ver verleden. Geniet van dit steenbakkerijerfgoed en de wonderbare natuur die de kleiputten heroverde.</p>";
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
        return "color-2";
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
