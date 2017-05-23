package entity;

import java.sql.Date;

/**
 * Created by 20_ok on 08.05.2017.
 */
public class Voucher {

    private int id;
    private Tour tour;
    private Flight flightTo;
    private Flight flightFrom;
    private int clientNumber;
    private int price;

    public Voucher() {
    }

    public Voucher(Tour tour, Flight flightTo, Flight flightFrom, int clientNumber, int price) {
        this.tour = tour;
        this.flightTo = flightTo;
        this.flightFrom = flightFrom;
        this.clientNumber = clientNumber;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(Flight flightTo) {
        this.flightTo = flightTo;
    }

    public Flight getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(Flight flightFrom) {
        this.flightFrom = flightFrom;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
