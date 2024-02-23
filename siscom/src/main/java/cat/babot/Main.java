package cat.babot;

import cat.babot.blue.Telegram;
import cat.babot.weather.Localitat;
import cat.babot.weather.Meteocat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Meteocat meteocat = new Meteocat(Localitat.VIC);
        meteocat.getWeather();
        System.out.print(meteocat.simplifyedTodayReport());
    }    }



}