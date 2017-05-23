package service.dbservice.impl;

import constant.Key;
import daolayer.impl.TourDAO;
import entity.Tour;
import exception.NoToursException;
import service.ResourceManager;

import java.sql.Date;
import java.util.List;


public class TourDBService {

    private TourDAO dao = new TourDAO();

    /**
     * метод ищет туры по стране, дате вылета и классу отеля
     *
     * @return найденные туры
     * @throws NoToursException нет туров по заданным параметрам
     **/
    public List<Tour> readToursByCountryAndDateAndHotelStars(String country, Date date, int hotelStars) throws NoToursException {

        List<Tour> tours = dao.readAllByCountryAndDateAndHotelStars(country, date, hotelStars);
        if (tours.isEmpty()) {
            throw new NoToursException(ResourceManager.getResource(Key.EX_TOURS_NOT_FOUND.getKey()));
        }

        return tours;
    }

    /**
     * Метод возвращает все туры, которые существуют в базе
     *
     * @return туры
     */
    public List<Tour> readAll() {

        return dao.readAll();
    }

    /**
     * метод ищет туры по классу отеля
     *
     * @return найденные туры
     * @throws NoToursException нет туров по заданным параметрам
     */
    public List<Tour> readToursByHotelStars(int hotelStars) throws NoToursException {

        List<Tour> tours = dao.readAllByHotelStars(hotelStars);
        if (tours.isEmpty()) {
            throw new NoToursException(ResourceManager.getResource(Key.EX_TOURS_NOT_FOUND.getKey()));
        }

        return tours;
    }

    /**
     * метод ищет туры по стране тура
     *
     * @return найденные туры
     * @throws NoToursException нет туров по заданным параметрам
     */
    public List<Tour> readToursByCountry(String country) throws NoToursException {

        List<Tour> tours = dao.readAllByCountry(country);
        if (tours.isEmpty()) {
            throw new NoToursException(ResourceManager.getResource(Key.EX_TOURS_NOT_FOUND.getKey()));
        }

        return tours;
    }

    /**
     * @return найденные туры
     * @throws NoToursException нет туров по заданным параметрам
     */
    public List<Tour> readToursByDate(Date date) throws NoToursException {

        List<Tour> tours = dao.readAllByDate(date);
        if (tours.isEmpty()) {
            throw new NoToursException(ResourceManager.getResource(Key.EX_TOURS_NOT_FOUND.getKey()));

        }

        return tours;
    }

    /**
     * метод ищет тур по стране тура и дате вылета
     *
     * @return найденные туры
     * @throws NoToursException нет туров по заданным параметрам
     */
    public List<Tour> readToursByCountryDate(String country, Date date) throws NoToursException {

        List<Tour> tours = dao.readAllByCountryDate(country, date);
        if (tours.isEmpty()) {
            throw new NoToursException(ResourceManager.getResource(Key.EX_TOURS_NOT_FOUND.getKey()));
        }

        return tours;
    }


    /**
     * метод ищет туры по стране и классу отеля
     *
     * @return найденные туры
     * @throws NoToursException нет туров по заданным параметрам
     */
    public List<Tour> readToursByCountryAndHotelStars(String country, int hotelStars) throws NoToursException {

        List<Tour> tours = dao.readAllByCountryAndHotelStars(country, hotelStars);
        if (tours.isEmpty()) {
            throw new NoToursException(ResourceManager.getResource(Key.EX_TOURS_NOT_FOUND.getKey()));
        }

        return tours;
    }

    public Tour read(int id) {
        return dao.read(id);
    }


    public boolean update(Tour tour) {
        return dao.update(tour);
    }
}
