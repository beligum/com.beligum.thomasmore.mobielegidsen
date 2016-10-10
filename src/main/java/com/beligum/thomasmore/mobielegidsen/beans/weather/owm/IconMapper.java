package com.beligum.thomasmore.mobielegidsen.beans.weather.owm;

import com.beligum.base.utils.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * See http://openweathermap.org/weather-conditions
 * and https://gist.github.com/tbranyen/62d974681dea8ee0caa1
 * Created by bram on 6/14/16.
 */
public class IconMapper
{
    //-----CONSTANTS-----
    public static final IconMapper INSTANCE = new IconMapper();

    public enum Icon
    {
        THUNDERSTORM_WITH_LIGHT_RAIN(200,"thunderstorm with light rain","storm-showers","11"),
        THUNDERSTORM_WITH_RAIN(201,"thunderstorm with rain","storm-showers","11"),
        THUNDERSTORM_WITH_HEAVY_RAIN(202,"thunderstorm with heavy rain","storm-showers","11"),
        LIGHT_THUNDERSTORM(210,"light thunderstorm","storm-showers","11"),
        THUNDERSTORM(211,"thunderstorm","thunderstorm","11"),
        HEAVY_THUNDERSTORM(212,"heavy thunderstorm","thunderstorm","11"),
        RAGGED_THUNDERSTORM(221,"ragged thunderstorm","thunderstorm","11"),
        THUNDERSTORM_WITH_LIGHT_DRIZZLE(230,"thunderstorm with light drizzle","storm-showers","11"),
        THUNDERSTORM_WITH_DRIZZLE(231,"thunderstorm with drizzle","storm-showers","11"),
        THUNDERSTORM_WITH_HEAVY_DRIZZLE(232,"thunderstorm with heavy drizzle","storm-showers","11"),
        LIGHT_INTENSITY_DRIZZLE(300,"light intensity drizzle","sprinkle","09"),
        DRIZZLE(301,"drizzle","sprinkle","09"),
        HEAVY_INTENSITY_DRIZZLE(302,"heavy intensity drizzle","sprinkle","09"),
        LIGHT_INTENSITY_DRIZZLE_RAIN(310,"light intensity drizzle rain","sprinkle","09"),
        DRIZZLE_RAIN(311,"drizzle rain","sprinkle","09"),
        HEAVY_INTENSITY_DRIZZLE_RAIN(312,"heavy intensity drizzle rain","sprinkle","09"),
        SHOWER_RAIN_AND_DRIZZLE(313,"shower rain and drizzle","sprinkle","09"),
        HEAVY_SHOWER_RAIN_AND_DRIZZLE(314,"heavy shower rain and drizzle","sprinkle","09"),
        SHOWER_DRIZZLE(321,"shower drizzle","sprinkle","09"),
        LIGHT_RAIN(500,"light rain","rain","10"),
        MODERATE_RAIN(501,"moderate rain","rain","10"),
        HEAVY_INTENSITY_RAIN(502,"heavy intensity rain","rain","10"),
        VERY_HEAVY_RAIN(503,"very heavy rain","rain","10"),
        EXTREME_RAIN(504,"extreme rain","rain","10"),
        FREEZING_RAIN(511,"freezing rain","rain-mix","13"),
        LIGHT_INTENSITY_SHOWER_RAIN(520,"light intensity shower rain","showers","09"),
        SHOWER_RAIN(521,"shower rain","showers","09"),
        HEAVY_INTENSITY_SHOWER_RAIN(522,"heavy intensity shower rain","showers","09"),
        RAGGED_SHOWER_RAIN(531,"ragged shower rain","showers","09"),
        LIGHT_SNOW(600,"light snow","snow","13"),
        SNOW(601,"snow","snow","13"),
        HEAVY_SNOW(602,"heavy snow","snow","13"),
        SLEET(611,"sleet","sleet","13"),
        SHOWER_SLEET(612,"shower sleet","sleet","13"),
        LIGHT_RAIN_AND_SNOW(615,"light rain and snow","rain-mix","13"),
        RAIN_AND_SNOW(616,"rain and snow","rain-mix","13"),
        LIGHT_SHOWER_SNOW(620,"light shower snow","rain-mix","13"),
        SHOWER_SNOW(621,"shower snow","rain-mix","13"),
        HEAVY_SHOWER_SNOW(622,"heavy shower snow","rain-mix","13"),
        MIST(701,"mist","sprinkle","50"),
        SMOKE(711,"smoke","smoke","50"),
        HAZE(721,"haze","day-haze","50"),
        SAND_DUST_WHIRLS(731,"sand, dust whirls","cloudy-gusts","50"),
        FOG(741,"fog","fog","50"),
        SAND(751,"sand","cloudy-gusts","50"),
        DUST(761,"dust","dust","50"),
        VOLCANIC_ASH(762,"volcanic ash","smog","50"),
        SQUALLS(771,"squalls","day-windy","50"),
        TORNADO(781,"tornado","tornado","50"),
        CLEAR_SKY(800,"clear sky","sunny","01"),
        FEW_CLOUDS(801,"few clouds","cloudy","02"),
        SCATTERED_CLOUDS(802,"scattered clouds","cloudy","03"),
        BROKEN_CLOUDS(803,"broken clouds","cloudy","04"),
        OVERCAST_CLOUDS(804,"overcast clouds","cloudy","04"),
        TORNADO_2(900,"tornado","tornado",""),
        TROPICAL_STORM(901,"tropical storm","hurricane",""),
        HURRICANE(902,"hurricane","hurricane",""),
        COLD(903,"cold","snowflake-cold",""),
        HOT(904,"hot","hot",""),
        WINDY(905,"windy","windy",""),
        HAIL(906,"hail","hail",""),
        CALM(951,"calm","sunny",""),
        LIGHT_BREEZE(952,"light breeze","cloudy-gusts",""),
        GENTLE_BREEZE(953,"gentle breeze","cloudy-gusts",""),
        MODERATE_BREEZE(954,"moderate breeze","cloudy-gusts",""),
        FRESH_BREEZE(955,"fresh breeze","cloudy-gusts",""),
        STRONG_BREEZE(956,"strong breeze","cloudy-gusts",""),
        HIGH_WIND_NEAR_GALE(957,"high wind, near gale","cloudy-gusts",""),
        GALE(958,"gale","cloudy-gusts",""),
        SEVERE_GALE(959,"severe gale","cloudy-gusts",""),
        STORM(960,"storm","thunderstorm",""),
        VIOLENT_STORM(961,"violent storm","thunderstorm",""),
        HURRICANE_2(962,"hurricane","cloudy-gusts",""),
        ;

        private int code;
        private String label;
        private String wiIcon;
        private String owmIcon;

        Icon(int code, String label, String wiIcon, String owmIcon)
        {
            this.code = code;
            this.label = label;
            this.wiIcon = wiIcon;
            this.owmIcon = owmIcon;
        }

        public int getCode()
        {
            return code;
        }
        public String getLabel()
        {
            return label;
        }
        public String getWiIcon()
        {
            return wiIcon;
        }
        public String getOwmIcon()
        {
            return owmIcon;
        }
    }

    //-----VARIABLES-----
    private Map<Integer, Icon> map = new HashMap<>();

    //-----CONSTRUCTORS-----
    private IconMapper()
    {
        for (Icon icon : Icon.values()) {
            this.map.put(icon.getCode(), icon);
        }
    }

    //-----PUBLIC METHODS-----
    public String getWiIconFor(WeatherData weatherData)
    {
        String retVal = null;

        //See https://gist.github.com/tbranyen/62d974681dea8ee0caa1
        if (weatherData!=null) {
            Icon icon = this.map.get(weatherData.getWeather().get(0).getId());

            retVal = icon.getWiIcon();

            if (!(icon.getCode() > 699 && icon.getCode() < 800) && !(icon.getCode() > 899 && icon.getCode() < 1000)) {
                retVal = "day-" + retVal;
            }

            retVal = "wi wi-" + retVal;
        }

        return retVal;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----
    /**
     * See http://openweathermap.org/weather-conditions
     * and https://gist.github.com/tbranyen/62d974681dea8ee0caa1
     *
     * @throws IOException
     */
    public static void parseOnlineIconsToEnum() throws IOException
    {
        int counter = 0;
        Files.delete(Paths.get("/home/bram/wi.java"));
        try (FileWriter fw = new FileWriter("/home/bram/wi.java", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> data = mapper.readValue(new URL("https://gist.githubusercontent.com/tbranyen/62d974681dea8ee0caa1/raw/3405bfb2a76b7cbd90fde33d8536f0cd13706955/icons.json"), Map.class);
            for (Map.Entry<String, Object> e : data.entrySet()) {
                Map<String, Object> val = (Map<String, Object>) e.getValue();
                String codeStr = e.getKey();
                int code = Integer.parseInt(codeStr);
                String label = (String) val.get("label");
                String wiIcon = (String) val.get("icon");
                String enumName = label.replaceAll(" ", "_").replaceAll("[^a-zA-Z_]", "").toUpperCase().trim();

                //we can set this to 'd' or 'n' if we want...
                final String dayNightSuffix = "";
                String owmIcon = "";
                if (code >= 200 && code <= 232) {
                    owmIcon = "11" + dayNightSuffix;
                }
                else if (code >= 300 && code <= 321) {
                    owmIcon = "09" + dayNightSuffix;
                }
                else if (code >= 500 && code <= 504) {
                    owmIcon = "10" + dayNightSuffix;
                }
                else if (code >= 511 && code <= 511) {
                    owmIcon = "13" + dayNightSuffix;
                }
                else if (code >= 520 && code <= 531) {
                    owmIcon = "09" + dayNightSuffix;
                }
                else if (code >= 600 && code <= 622) {
                    owmIcon = "13" + dayNightSuffix;
                }
                else if (code >= 701 && code <= 781) {
                    owmIcon = "50" + dayNightSuffix;
                }
                else if (code >= 800 && code <= 800) {
                    owmIcon = "01" + dayNightSuffix;
                }
                else if (code >= 801 && code <= 801) {
                    owmIcon = "02" + dayNightSuffix;
                }
                else if (code >= 802 && code <= 802) {
                    owmIcon = "03" + dayNightSuffix;
                }
                else if (code >= 803 && code <= 803) {
                    //this one is actually double?
                    owmIcon = "04" + dayNightSuffix;
                }
                else if (code >= 804 && code <= 804) {
                    owmIcon = "04" + dayNightSuffix;
                }

                out.println(enumName + "(" + code + ",\"" + label + "\",\"" + wiIcon + "\",\"" + owmIcon + "\"),");

                counter++;
            }
        }
        Logger.info("Parsed " + counter + " icons");
    }
}
