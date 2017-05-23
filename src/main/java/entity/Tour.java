package entity;

import java.util.Comparator;

public class Tour {
    private int id;
    private City city;
    private Hotel hotel;
    private String description;
    private int days;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static class SortingByDays implements Comparator<Tour> {

        @Override
        public int compare(Tour o1, Tour o2) {
            return o1.getDays() - o2.getDays();
        }
    }


    public static class SortingByPrice implements Comparator<Tour> {

        @Override
        public int compare(Tour o1, Tour o2) {
            return o1.getPrice() - o2.getPrice();
        }
    }

}
