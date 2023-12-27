package cat.babot.weather;

import java.util.HashMap;
import java.util.Map;

public class Dia {
    private static final String CELCIUS = "ºC";
    protected final Map<Integer, String> temperatura = new HashMap<>();
    protected final Map<Integer, String> cel = new HashMap<>();
    protected final Map<Integer, String> vent = new HashMap<>();
    protected final Map<Integer, String> precipitacio = new HashMap<>();


    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        this.cel.forEach((key, value) -> result.append(key).append(" h -> ").append(value).append(" ").append(cel).append("\n"));
        return result.toString();
    }

    public String toStringTemperatura() {
        return toString("Temperatura", temperatura, "ºC");
    }

    public String toStringCel() {
        return toString("Cel", cel, "");
    }

    public String toStringVent() {
        return toString("Vent", temperatura, "km/h");
    }

    public String toStringPrecipitacio() {
        return toString("Precipitació", precipitacio, "mm");
    }

    private String toString(String title, Map<Integer, String> values, String unitats) {
        StringBuilder result = new StringBuilder();
        result.append(title).append("\n");
        result.append("------------------").append("\n");
        values.forEach((key, value) -> result.append(key).append(" h -> ").append(value).append(" ").append(unitats).append("\n"));
        return result.toString();
    }
}
