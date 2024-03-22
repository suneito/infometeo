package cat.babot;

import cat.babot.blue.Telegram;
import cat.babot.weather.Localitat;
import cat.babot.weather.Meteocat;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Meteocat meteocat = new Meteocat(Localitat.VIC);
        meteocat.getWeather();
        System.out.print(meteocat.simplifyedTodayReport());

//        Document doc = Jsoup.connect("https://www.meteo.cat/prediccio/municipal/082981").get();
//        Elements newsHeadlines = doc.selectXpath("//div[@id='tabs-dia1']//th[contains(text(),'Cel')]/following-sibling::td/img/@alt");
//        for (Element headline : newsHeadlines) {
//            System.out.print(headline.text());
//        }
    }
}