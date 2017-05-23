package executor.impl.admin.impl;

import constant.Attribute;
import constant.Page;
import entity.HotTour;
import entity.Tour;
import executor.impl.admin.AdminExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 21.05.2017.
 */
public class BuildAdminExecutor extends AdminExecutor {
    public BuildAdminExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute() {

        String page = Page.ADMIN.getPage();

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

        return page;
    }
}
