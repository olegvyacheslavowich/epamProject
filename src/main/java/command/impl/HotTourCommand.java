package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.HotTour;
import entity.Tour;
import exception.DataBaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.HotTourService;
import service.StatisticService;
import service.TourService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HotTourCommand implements ActionCommand {

    private static final double DISCOUNT = 0.20;

    private static final Logger logger = LoggerFactory.getLogger(FlightCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.ADMIN;
        HttpSession session = rq.getSession();
        TourService tourService = new TourService();
        HotTourService hotTourService = new HotTourService();
        StatisticService statisticService = new StatisticService();

        if (Validator.isEmpty(rq.getParameter(Parameter.TOUR_ID))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_SELECT_TOUR, Language.getCurrentLanguage(session)));
            logger.info("Admin page error :" + ResourceManager.getResource(Key.EX_SELECT_TOUR, Language.getCurrentLanguage(session)));
            return page;
        }

        int tourId = Integer.parseInt(rq.getParameter(Parameter.TOUR_ID));

        try {
            Tour tour = tourService.read(tourId);
            tour.setPrice(getHotPrice(tour));
            HotTour hotTour = new HotTour();
            hotTour.setTour(tour);
            tourService.update(tour);
            hotTourService.create(hotTour);
            logger.info("Hot tour was created");
            rq.setAttribute(Attribute.TOURS, tourService.readAll());
            rq.setAttribute(Attribute.STATISTIC, statisticService.getStatistic());
        } catch (DataBaseConnectionException e) {
            logger.info("Admin page error ", e);
            return page;
        }

        return page;
    }

    private int getHotPrice(Tour tour) {

        int currentPrice = tour.getPrice();
        return (int) (currentPrice - currentPrice * DISCOUNT);
    }


}
