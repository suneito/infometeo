package cat.babot;

import cat.babot.blue.Telegram;
import cat.babot.weather.Meteocat;

import java.io.IOException;

public class Main {
    public static final String TONA = "082830";
    public static final String VIC = "082981";


    public static void main(String[] args) throws IOException {
        Telegram telegram = new Telegram(
                "bot6424157408",
                "AAHBjEE4LaGylXYAiiiHSMmyV59eQZwNeqU",
                "-1002134142785");
        Meteocat meteocat = new Meteocat(VIC);
        meteocat.getWeather();
        telegram.sendMsg(meteocat.getAvui().toStringCel());
        telegram.sendMsg(meteocat.getAvui().toStringTemperatura());
        telegram.sendMsg(meteocat.getAvui().toStringVent());
        telegram.sendMsg(meteocat.getAvui().toStringPrecipitacio());
    }



}