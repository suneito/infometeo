package cat.babot.weather;

import cat.babot.scraper.ScraperManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Meteocat extends ScraperManager {
    private final Dia avui;
    private final Dia dema;
    private final Dia pstDema;
    private final Localitat localitat;

    public Meteocat(Localitat vila) {
        super("https://www.meteo.cat/prediccio/municipal/".concat(vila.getLocalitatCode()));
        setTargetNode();
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

    private List<String> obtainTemperatura(DiaLocate dia) {
        return obtainList("//div[@id='" + dia.toString() +"']//th[contains(text(),'Temperatura')]/following-sibling::td");
    }

    private List<String> obtainPrecipitacio(DiaLocate dia) {
        return obtainList("//div[@id='" + dia.toString() +"']//th[contains(text(),'Precipitació')]/following-sibling::td");
    }

    private List<String> obtainVent(DiaLocate dia) {
        return obtainList("//div[@id='" + dia.toString() +"']//th[contains(text(),'Vent')]/following-sibling::td");
    }

    private List<String> obtainCel(DiaLocate dia) {
        return obtainListAttr("//div[@id='" + dia + "']//th[contains(text(),'Cel')]/following-sibling::td/img", "alt");
    }

    private void insertIntoDay(List<String> stringList, Map<Integer, String> diaMap) {
        IntStream.range(0, stringList.size())
                .forEach(index -> diaMap.put(23-index, stringList.get(stringList.size()-1- index).trim()));
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
        result.append("-- Meteocat report --------\n").append(localitat.name()).append(" ").append(avui.getData()).append("\n");
        result.append("Temperatura - ").append(avui.temperatura).append("\n");
        result.append("Precipitació - ").append(avui.precipitacio).append("\n");
        result.append("Vent - ").append(avui.vent).append("\n");
        result.append("Cel - ").append(avui.cel).append("\n");
        result.append(getPeuMesInfo());
        return result.toString();
    }

    public String getPeuMesInfo() {
        return new StringBuilder()
                .append("\n<i>Més informació <a href=\"")
                .append(targetUrl).append("\">meteo.cat/vic</a></i>").toString();
    }
}
