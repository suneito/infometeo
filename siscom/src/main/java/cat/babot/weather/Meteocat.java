package cat.babot.weather;

import cat.babot.scraper.ScraperManager;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.IntStream;

public class Meteocat extends ScraperManager {
    private Dia avui;
    private Dia dema;
    private Dia pstDema;
    private Localitat localitat;

    public Meteocat(Localitat vila) {
        super("https://www.meteo.cat/prediccio/municipal/".concat(vila.getLocalitatCode()));
        localitat = vila;
        LocalDate today = LocalDate.now();
        avui = new Dia(today);
        dema = new Dia(today.plusDays(1));
        pstDema = new Dia(today.plusDays(2));
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

    public String simplifyedTodayReport() {
        StringBuilder result = new StringBuilder();
        result.append(localitat.name()).append(" ").append(avui.getData()).append("\n");
        result.append("data").append("\n");
        result.append(getPeuMesInfo());
        return result.toString();
    }

    public String getPeuMesInfo() {
        return new StringBuilder()
                .append("<i>Més informació <a href=\"")
                .append(targetUrl).append("\">meteo.cat/vic</a></i>").toString();
    }
}
