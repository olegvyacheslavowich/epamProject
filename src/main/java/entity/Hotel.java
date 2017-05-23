package entity;

/**
 * Created by 20_ok on 06.05.2017.
 */
public class Hotel {

    private int id;
    private String name;
    private City city;
    private String description;
    private long starsNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public long getStarsNumber() {
        return starsNumber;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setStarsNumber(long starsNumber) {
        this.starsNumber = starsNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
