package executor.impl.tourssorting;

import constant.Attribute;
import constant.Page;
import constant.Parameter;
import entity.HotTour;
import entity.Tour;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ToursSortingExecutor extends PageExecutor {

    private String page = Page.TOURS.getPage();

    private static final String SORTING_BY_DAYS = "days";
    private static final String SORTING_BY_PRICE = "price";
    private static final String SORTING_BY_HOT = "hotTours";

    private String sortingBy;
    private List<Tour> tours;


    public ToursSortingExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public void read() {
        sortingBy = readFromRequest(Parameter.SORT.getParameter());
        tours = (List<Tour>) readFromSession(Attribute.TOURS.getAttribute());
    }

    public void write() {
        if (!isNull(sortingBy)) {
            switch (sortingBy) {
                case SORTING_BY_DAYS:
                    setToRequest(Attribute.TOURS.getAttribute(), getToursSortedByDays(tours));
                    break;
                case SORTING_BY_PRICE:
                    setToRequest(Attribute.TOURS.getAttribute(), getToursSortedByPrice(tours));
                    break;
                case SORTING_BY_HOT:
                    setToRequest(Attribute.TOURS.getAttribute(), getHotTours());
                    break;
            }
        }
    }

    public String returnPage() {
        return page;
    }

    private List<Tour> getToursSortedByDays(List<Tour> tours) {

        tours.sort(new Tour.SortingByDays());
        return tours;
    }

    private List<Tour> getToursSortedByPrice(List<Tour> tours) {

        tours.sort(new Tour.SortingByPrice());
        return tours;
    }

    private List<Tour> getHotTours() {

        List<Tour> tours = new ArrayList<>();
        List<HotTour> hotTours = getHotTourDBService().readAll();

        for (HotTour hotTour : hotTours) {
            tours.add(hotTour.getTour());
        }
        return tours;
    }
}
