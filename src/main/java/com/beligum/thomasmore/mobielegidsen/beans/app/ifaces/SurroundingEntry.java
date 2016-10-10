package com.beligum.thomasmore.mobielegidsen.beans.app.ifaces;

import com.beligum.thomasmore.mobielegidsen.beans.app.*;

import java.net.URI;

/**
 * Created by bram on 5/20/16.
 */
public interface SurroundingEntry
{
    //-----INSTANCES-----
    SurroundingEntry[] SURROUNDING_ENTRIES_RUPELSTREEK = new SurroundingEntry[]
                    {
                                    //SLAPEN
                                    new Hotel("B&B Biendomo",
                                              "Kneukelputlei 6",
                                              "2840", "Reet",
                                              "+32 470 65 20 31",
                                              "greet@biendomo.be",
                                              "http://www.biendomo.be",
                                              new MapLocation(51.1020085, 4.4191372, 17),
                                              new Media[]{
                                                              new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/bb-biendomo/1.jpg"))
                                              },
                                              "Vlakbij de samenvloeiing van Dijle, Nete en Zenne, kan je genieten van een mooie brasserie met groot buitenterras en zicht op de Rupel. Een zakelijk diner, maaltijden uit de tijd van toen, een pannenkoek of een streekbier? Het wordt er allemaal geserveerd in een familiale sfeer. Jong en oud: ieder is er welkom zijn."),


                                    new Hotel("B&B Klinkaart",
                                              "Populierenlaan 32",
                                              "2840", "Terhagen",
                                              "+32 474 72 87 92",
                                              "info@klinkaart.be",
                                              "http://www.klinkaart.be",
                                              new MapLocation(51.0764806, 4.3975562, 17),
                                              new Media[]{
                                                              new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/bb-klinkaart/1.jpg"))
                                              },
                                              "Klinkaart is een kleine gezellige B&B in het hart van de Rupelstreek, aan de oever van de Rupel in het oude steenbakkersgehucht Terhagen. Wij bieden in onze woning één kamer aan met kitchenette, badkamer, kleine living met comfortabele slaapsofa en een buitenterras. Er is plaats voor 4 personen. Van hieruit kan je de Rupelstreek volop ontdekken."),

                                    new Hotel("De plukboerderij en B&B",
                                              "Steenwinkelstraat 499",
                                              "2627", "Schelle",
                                              "+32 475 65 16 23",
                                              "b&b@plukboerderij.be",
                                              "http://www.plukboerderij.be",
                                              new MapLocation(51.124636, 4.362012, 17),
                                              new Media[]{
                                                              new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/bb-plukboerderij/1.jpg"))
                                              },
                                              "Onze B&B, ingericht in een landelijke omgeving met grote moestuin, beschikt over vier sfeervolle  kamers:  drie tweepersoonskamers en één vierpersoonskamer. Alle gasten kunnen vrij gebruikmaken van de grote open  benedenruimte met verschillende zithoeken en werkplekken. Kinderspeelgoed en gezelschapsspelen zijn voorhanden. Buiten kan je genieten op de verschillende terrassen."),

                                    new Hotel("Hotel Domus",
                                              "Antwerpsestraat 15",
                                              "2850", "Boom",
                                              "+32 3 440 90 00",
                                              "info@hoteldomus.be",
                                              "http://www.hoteldomus.be",
                                              new MapLocation(51.0867784, 4.3637081, 17),
                                              new Media[]{
                                                              new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/hotel-domus/1.jpg"))
                                              },
                                              "De standaard, standaard deluxe en deluxe kamers zijn kwaliteitsvol gestyled met een eigen identiteit en voorzien van de moderne luxe: internet flatscreen tv, kingsize bedden... Het op maat gemaakt design meubilair zorgt voor een huiselijke sfeer. Hotel Domus biedt ontbijt en brunch aan; diner op aanvraag."),


                                    //ETEN
                                    new Restaurant("Brasserie De Schorre",
                                                   "Schommelei 1 bus 2",
                                                   "2850", "Boom",
                                                   "+32 3 288 41 50",
                                                   "ingrid@brasseriedeschorre.com",
                                                   "http://www.brasseriedeschorre.com",
                                                   new MapLocation(51.087926, 4.374185, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/brasserie-de-schorre/1.jpg"))
                                                   },
                                                   "Brasserie De Schorre werd recent (in 2013) vernieuwd en verwelkomt u vandaag in een stijlvolle, hedendaagse omgeving." +
                                                   "Bij mooi weer bedienen we u graag op het schitterende terras, waar het aangenaam toeven is."),


                                    new Restaurant("Brasserie 3ri4en",
                                                   "Visserstraat 91",
                                                   "2840", "Rumst",
                                                   "+32 3 888 79 43",
                                                   "info@3rivieren.be",
                                                   "http://www.drierivieren.com",
                                                   new MapLocation(51.074092, 4.421589, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/brasserie-3ri4en/1.jpg"))
                                                   },
                                                   "Vlakbij de samenvloeiing van Dijle, Nete en Zenne, kan je genieten van een mooie brasserie met groot buitenterras en zicht op de Rupel. Een zakelijk diner, maaltijden uit de tijd van toen, een pannenkoek of een streekbier? Het wordt er allemaal geserveerd in een familiale sfeer. Jong en oud: ieder is er welkom zijn."),

                                    new Restaurant("Pastorale",
                                                   "Laarstraat 22",
                                                   "2840", "Rumst",
                                                   "+32 3 844 65 26",
                                                   "info@depastorale.be",
                                                   "http://www.depastorale.be",
                                                   new MapLocation(51.1007813, 4.4153375, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/restaurant-pastorale/1.jpg"))
                                                   },
                                                   "Chefkok Bart De Pooter biedt in zijn 2-sterrenrestaurant Pastorale een gastronomische en verfijnde keuken aan, afgestemd op de seizoenen en met natuurlijke ingrediënten. Je kan er terecht voor mooie uitgebalanceerde menu's of lunchen of dineren à la carte. Hogere prijsklasse."),

                                    new Cafe("t Steencaycken",
                                                   "Hoek 76",
                                                   "2850", "Boom",
                                                   "+32 3 880 53 63",
                                                   "info@steencaycken.be",
                                                   "http://www.steencaycken.be",
                                                   new MapLocation(51.0829701, 4.3765497, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/cafe-steencaycken/1.jpg"))
                                                   },
                                                   "t Steencaycken is een sociaal tewerkstellingsproject. Het is een gezellig, rustiek aandoend eetcafé aan de Rupeldijk in Boom. Je kan er voor democratische prijzen lekker eten, drinken en de lokale keuken proeven. Bierproevers kunnen er ook terecht voor heel wat streekbieren."),

                                    new Restaurant("Den Boom",
                                                   "Antwerpsestraat 35 B",
                                                   "2850", "Boom",
                                                   "+32 3 290 00 73",
                                                   "info@restaurantdenboom.com",
                                                   "http://www.restaurantdenboom.com",
                                                   new MapLocation(51.0874927, 4.3656102, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/restaurant-den-boom/1.jpg"))
                                                   },
                                                   "Elke dag neemt ons ervaren en gemotiveerd team de uitdaging aan om voortreffelijke menu’s te serveren van kwaliteitsingrediënten. Tijdens de zomermaanden kunnen onze gasten heerlijk genieten op het rustige terras met vijverpartij."),

                                    new Restaurant("De Smoutstoop",
                                                   "Kerkhofstraat 42",
                                                   "2850", "Boom",
                                                   "+32 3 844 81 91",
                                                   "",
                                                   "http://www.desmoutstoop.com",
                                                   new MapLocation(51.0963534, 4.381559, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/restaurant-de-smoutstoop/1.jpg"))
                                                   },
                                                   "In een rustieke omgeving met open haard kan u genieten van de kwaliteitskeuken van taverne-restaurant de Smoutstoop. Geniet niet alleen van de heerlijke verwennerijen die de koks serveren maar ook van de unieke, gezellige sfeer binnen of op het buitenterras in de tuin. Ook grote groepen zijn hier welkom."),

                                    new Restaurant("Trattoria",
                                                   "Kaai",
                                                   "2850", "Boom",
                                                   "+32 3 843 33 12",
                                                   "info@trattoriakaai.be",
                                                   "http://www.trattoriakaai.be",
                                                   new MapLocation(51.084215, 4.365926, 17),
                                                   new Media[]{
                                                                   new Image(URI.create("/webhdfs/v1/assets/images/app/routes/horeca/restaurant-trattoria-kaai/1.jpg"))
                                                   },
                                                   "Gelegen aan de aanlegsteiger voor jachten en pleziervaartuigen van Boom, biedt Trattoria Kaai in een design interieur heerlijk eten en drinken aan naar Italiaanse traditie en voor een eerlijke prijs! Het ruime terras met zicht op het veer naar Klein-Willebroek is een ideale stopplaats tijdens een wandeling of fietstocht in de omgeving van de Rupel."),


                    };




    //-----CONSTANTS-----
    enum Type {
        FOOD,
        LODGING
    }

    //-----VARIABLES-----

    //-----CONSTRUCTORS-----

    //-----PUBLIC METHODS-----
    String getTitle();
    Type getType();
    String getAddress();
    String getPostalCode();
    String getCity();
    String getPhoneNumber();
    String getEmail();
    String getWebsite();
    MapLocation getLocation();
    Media[] getMedia();
    String getDescription();

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
