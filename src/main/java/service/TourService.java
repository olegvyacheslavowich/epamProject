package service;

import constant.Key;
import daolayer.DaoFactory;
import daolayer.impl.TourDao;
import entity.Tour;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.NoToursException;
import exception.ServiceException;

import java.sql.Date;
import java.util.List;


public class TourService {


    public List<Tour> readToursByCountryAndDateAndHotelStars(String country, Date date, int hotelStars) throws ServiceException, DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAllByCountryAndDateAndHotelStars(country, date, hotelStars);
            if (tours.isEmpty()) {
                throw new ServiceException(Key.EX_TOURS_NOT_FOUND);
            }
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return tours;
    }

    /**
     * Метод возвращает все туры, которые существуют в базе
     *
     * @return туры
     */
    public List<Tour> readAll() throws DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAll();
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return tours;
    }

    public List<Tour> readToursByHotelStars(int hotelStars) throws ServiceException, DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAllByHotelStars(hotelStars);
            if (tours.isEmpty()) {
                throw new ServiceException(Key.EX_TOURS_NOT_FOUND);
            }
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);

        }
        return tours;
    }


    public List<Tour> readToursByCountry(String country) throws ServiceException, DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAllByCountry(country);
            if (tours.isEmpty()) {
                throw new ServiceException(Key.EX_TOURS_NOT_FOUND);
            }
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return tours;
    }

    public List<Tour> readToursByDate(Date date) throws ServiceException, DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAllByDate(date);
            if (tours.isEmpty()) {
                throw new ServiceException(Key.EX_TOURS_NOT_FOUND);
            }
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return tours;
    }


    public List<Tour> readToursByCountryDate(String country, Date date) throws ServiceException, DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAllByCountryDate(country, date);
            if (tours.isEmpty()) {
                throw new ServiceException(Key.EX_TOURS_NOT_FOUND);
            }
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return tours;
    }


    public List<Tour> readToursByCountryAndHotelStars(String country, int hotelStars) throws ServiceException, DataBaseConnectionException {

        List<Tour> tours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tours = tourDao.readAllByCountryAndHotelStars(country, hotelStars);
            if (tours.isEmpty()) {
                throw new ServiceException(Key.EX_TOURS_NOT_FOUND);
            }
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);

        }
        return tours;
    }

    public Tour read(int id) throws DataBaseConnectionException {

        Tour tour;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            tour = tourDao.read(id);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return tour;
    }


    public boolean update(Tour tour) throws DataBaseConnectionException {

        boolean result;
        try (DaoFactory daoFactory = new DaoFactory()) {
            TourDao tourDao = daoFactory.getTourDao();
            result = tourDao.update(tour);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return result;
    }
}
