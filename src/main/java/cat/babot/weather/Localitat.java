package cat.babot.weather;

public enum Localitat {
    TONA ("082830"),
    VIC("082981");

    String localitatCode;

    Localitat(String number) {
        localitatCode = number;
    }

    public String getLocalitatCode() {
        return this.localitatCode;
    }
}
