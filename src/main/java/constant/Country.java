package constant;

/**
 * Created by 20_ok on 12.05.2017.
 */
public enum Country {

    ANY_COUNTRY("Любая"),
    TURKEY("Турция"),
    RUSSIA("Россия"),
    KAZAKHSTAN("Казахстан"),
    UKRAINE("Украина"),
    GERMANY("Германия");

    private String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
