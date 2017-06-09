package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import constant.Parameter;
import entity.HotTour;
import entity.Tour;
import exception.DataBaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.HotTourService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class ToursSortingCommand implements ActionCommand {

    private static final String SORTING_BY_DAYS = "days";
    private static final String SORTING_BY_PRICE = "price";
    private static final String SORTING_BY_HOT = "hotTours";

    private static final Logger logger = LoggerFactory.getLogger(ToursSortingCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();

        String sortingBy = rq.getParameter(Parameter.SORT);
        List<Tour> tours = (List<Tour>) session.getAttribute(Attribute.TOURS);
        if (!Validator.isEmpty(sortingBy)) {
            switch (sortingBy) {
                case SORTING_BY_DAYS:
                    rq.setAttribute(Attribute.TOURS, getToursSortedByDays(tours));
                    logger.info("Sorted by days");
                    break;
                case SORTING_BY_PRICE:
                    rq.setAttribute(Attribute.TOURS, getToursSortedByPrice(tours));
                    logger.info("Sorted by price");
                    break;
                case SORTING_BY_HOT:
                    try {
                        rq.setAttribute(Attribute.TOURS, getHotTours());
                        logger.info("Sorted by hot");
                    } catch (DataBaseConnectionException e) {
                        logger.info("Tours page error: ", e);
                    }
                    break;
            }
        }

        return Page.TOURS;
    }

    private List<Tour> getToursSortedByDays(List<Tour> tours) {

        tours.sort(new Tour.SortingByDays());
        return tours;
    }

    private List<Tour> getToursSortedByPrice(List<Tour> tours) {

        tours.sort(new Tour.SortingByPrice());
        return tours;
    }

    private List<Tour> getHotTours() throws DataBaseConnectionException {

        HotTourService hotTourService = new HotTourService();
        List<Tour> tours = new ArrayList<>();
        List<HotTour> hotTours = hotTourService.readAll();

        for (HotTour hotTour : hotTours) {
            tours.add(hotTour.getTour());
        }
        return tours;
    }


}
