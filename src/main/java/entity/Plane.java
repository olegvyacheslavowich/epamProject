package entity;

import java.sql.Time;

/**
 * Created by 20_ok on 02.05.2017.
 */
public class Plane {

    private String name;
    private City departureCity;
    private Time departureTime;
    private City arrivalCity;
    private Time travelTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Time getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Time arrivalTime) {
        this.travelTime = arrivalTime;
    }


}
