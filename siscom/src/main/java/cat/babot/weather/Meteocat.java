package cat.babot.weather;

import cat.babot.scraper.ScraperManager;
import org.w3c.dom.NodeList;

import java.util.Map;
import java.util.stream.IntStream;

public class Meteocat extends ScraperManager {
    private Dia avui;
    private Dia dema;
    private Dia pstDema;

    public Meteocat(String vila) {
        super("https://www.meteo.cat/prediccio/municipal/".concat(vila));
        avui = new Dia();
        dema = new Dia();
        pstDema = new Dia();
    }

    public void getWeather() {
        obtainDay(avui, DiaLocate.AVUI);
        obtainDay(dema, DiaLocate.DEMA);
        obtainDay(pstDema, DiaLocate.PSTDEMA);
    }

    private void obtainDay(Dia dia, DiaLocate avui) {
        insertIntoDay(obtainTemperatura(avui), dia.temperatura);
        insertIntoDay(obtainCel(avui), dia.cel);
        insertIntoDay(obtainVent(avui), dia.vent);
        insertIntoDay(obtainPrecipitacio(avui), dia.precipitacio);
    }

    private NodeList obtainTemperatura(DiaLocate dia) {
        return obtainList("//div[@id='" + dia.toString() +"']//th[contains(text(),'Temperatura')]/following-sibling::td");
    }

    private NodeList obtainPrecipitacio(DiaLocate dia) {
        return obtainList("//div[@id='" + dia.toString() +"']//th[contains(text(),'Precipitació')]/following-sibling::td");
    }

    private NodeList obtainVent(DiaLocate dia) {
        return obtainList("//div[@id='" + dia.toString() +"']//th[contains(text(),'Vent')]/following-sibling::td");
    }

    private NodeList obtainCel(DiaLocate dia) {
        return obtainList("//div[@id='" + dia + "']//th[contains(text(),'Cel')]/following-sibling::td/img/@alt");
    }

    private void insertIntoDay(NodeList list, Map<Integer, String> diaMap) {
        IntStream.range(0, list.getLength())
                .forEach(index -> diaMap.put(23-index, list.item(list.getLength()-1-index).getTextContent().trim()));
    }

    public Dia getAvui() {
        return avui;
    }

    public Dia getDema() {
        return dema;
    }

    public Dia getPstDema() {
        return pstDema;
    }
}
