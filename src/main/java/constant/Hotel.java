package constant;

/**
 * Created by 20_ok on 11.05.2017.
 */
public enum Hotel {

    ANY_HOTEL (0),
    ONE_STAR (1),
    TWO_STARS (2),
    THREE_STARS (3),
    FOUR_STARS (4),
    FIVE_STARS (5);


    private  int hotel;

    Hotel(int constant) {
        this.hotel = constant;
    }
    public int getHotel() {
        return hotel;
    }


}
