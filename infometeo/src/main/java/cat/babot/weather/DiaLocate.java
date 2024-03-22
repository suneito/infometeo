package cat.babot.weather;

public enum DiaLocate {
    DEMA("tabs-dia2"),
    PSTDEMA("tabs-dia3"),
    AVUI("tabs-dia1");

    String id;

    DiaLocate(String id) {
        this.id = id;
    }

    @Override public String toString() {
        return id;
    }
}
