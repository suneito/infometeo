package cat.babot;

import cat.babot.blue.Telegram;
import cat.babot.weather.Localitat;
import cat.babot.weather.Meteocat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Meteocat report");
        Meteocat meteocat = new Meteocat(Localitat.VIC);
        meteocat.getWeather();
        System.out.print(meteocat.simplifyedTodayReport());
    }
}