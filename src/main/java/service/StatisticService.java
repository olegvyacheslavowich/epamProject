package service;

import entity.HotTour;
import entity.Statistic;
import entity.Tour;
import exception.DataBaseConnectionException;

import java.util.List;

/**
 * Created by 20_ok on 6/7/2017.
 */
public class StatisticService {

    public Statistic getStatistic() throws DataBaseConnectionException {

        TourService tourService = new TourService();
        HotTourService hotTourService = new HotTourService();
        OrderService orderService = new OrderService();

        Statistic statistic = new Statistic();
        List<Tour> tours = tourService.readAll();
        List<HotTour> hotTours = hotTourService.readAll();
        hotTourService.findHotTours(tours, hotTours);
        statistic.setHotToursNumber(hotTours.size());
        statistic.setSold(orderService.readAll().size());
        statistic.setToursNumber(tours.size());

        return statistic;
    }

}

