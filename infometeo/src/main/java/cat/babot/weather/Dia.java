package cat.babot.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Dia {
    private final LocalDate data;
    private static final String CELCIUS = "ºC";
    private static final String MILIMITERS = "mm";
    private static final String WIND = "km/h";
    protected final Map<Integer, String> temperatura = new HashMap<>();
    protected final Map<Integer, String> cel = new HashMap<>();
    protected final Map<Integer, String> vent = new HashMap<>();
    protected final Map<Integer, String> precipitacio = new HashMap<>();

    public Dia(LocalDate dataDia) {
        data = dataDia;
    }

    @Override public String toString() {
        StringBuilder result = new StringBuilder();

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

    public String getData() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
