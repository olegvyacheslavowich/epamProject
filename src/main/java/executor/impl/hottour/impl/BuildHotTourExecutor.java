package executor.impl.hottour.impl;

import constant.Attribute;
import constant.Page;
import entity.HotTour;
import entity.Tour;
import exception.EmptyFieldException;
import executor.impl.hottour.HotTourExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class BuildHotTourExecutor extends HotTourExecutor {

    private static final double DISCOUNT = 0.20;

    private HotTourExecutor next;

    public BuildHotTourExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String tourId) throws EmptyFieldException {

        String page = Page.ADMIN.getPage();

        Tour tour = getTourDBService().read(Integer.parseInt(tourId));
        tour.setPrice(getHotPrice(tour));
        HotTour hotTour = new HotTour();
        hotTour.setTour(tour);

        getTourDBService().update(tour);
        getHotTourDBService().create(hotTour);

        List<Tour> tours = getTourDBService().readAll();
        List<HotTour> hotTours = getHotTourDBService().readAll();
        searchHotTours(tours, hotTours);

        int toursNumber = tours.size();
        int hotToursNumber = hotTours.size();
        int sold = getOrderDBService().readAll().size();

        setToRequest(Attribute.TOURS.getAttribute(), tours);
        setToRequest(Attribute.ALL_TOURS.getAttribute(), toursNumber);
        setToRequest(Attribute.HOT_TOURS.getAttribute(), hotToursNumber);
        setToRequest(Attribute.SOLD.getAttribute(), sold);


        if (next != null) {
            next.execute(tourId);
        }
        return page;
    }

    public void setNext(HotTourExecutor next) {
        this.next = next;
    }

    private int getHotPrice(Tour tour) {

        int currentPrice = tour.getPrice();
        return (int) (currentPrice - currentPrice * DISCOUNT);

    }
}
