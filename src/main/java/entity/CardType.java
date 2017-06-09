package entity;

/**
 * Created by 20_ok on 14.05.2017.
 */
public class CardType {

    private int id;
    private String name;

    public CardType() {
    }

    public CardType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CardType(String name) {
        this.name = name;
    }

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
}
