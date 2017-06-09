package service;

import constant.Number;
import daolayer.DaoFactory;
import daolayer.impl.HotTourDao;
import entity.HotTour;
import entity.Tour;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ServiceException;

import java.util.List;

/**
 * Created by 20_ok on 19.05.2017.
 */
public class HotTourService {

    public List<HotTour> readAll() throws DataBaseConnectionException {

        List<HotTour> hotTours;
        try (DaoFactory daoFactory = new DaoFactory()) {
            HotTourDao hotTourDao = daoFactory.getHotTourDao();
            hotTours = hotTourDao.readAll();
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);

        }
        return hotTours;
    }

    public int create(HotTour tour) throws DataBaseConnectionException {

        int id;
        try (DaoFactory daoFactory = new DaoFactory()) {
            HotTourDao hotTourDao = daoFactory.getHotTourDao();
            id = hotTourDao.create(tour);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return id;
    }

    void findHotTours(List<Tour> tours, List<HotTour> hotTours) {
        for (Tour tour : tours) {
            for (HotTour hotTour : hotTours) {
                if (tour.getId() == hotTour.getTour().getId()) {
                    tour.setId(Number.MINUS_ONE);
                }
            }
        }
    }
}
