package executor.pageexecutor.impl;

import constant.Attribute;
import constant.Page;
import constant.Parameter;
import entity.Tour;
import executor.pageexecutor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 13.05.2017.
 */
public class ToursSortingExecutor extends PageExecutor {

    private String page = Page.TOURS.getPage();

    private static final String SORTING_BY_DAYS = "days";
    private static final String SORTING_BY_PRICE = "price";

    private String sortingBy;
    private List<Tour> tours;


    public ToursSortingExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public void read() {
        sortingBy = readFromRequest(Parameter.SORT.getParameter());
        tours = (List<Tour>) readFromSession(Attribute.TOURS.getAttribute());
    }

    @Override
    public void build() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void validate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write() {
        if (!isNull(sortingBy)) {
            switch (sortingBy) {
                case SORTING_BY_DAYS:
                    setToRequest(Attribute.TOURS.getAttribute(), getToursSortedByDays(tours));
                    break;
                case SORTING_BY_PRICE:
                    setToRequest(Attribute.TOURS.getAttribute(), getToursSortedByPrice(tours));
                    break;
            }
        }
    }

    @Override
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
}
