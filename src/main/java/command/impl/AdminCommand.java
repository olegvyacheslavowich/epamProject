package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.DataBaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StatisticService;
import service.TourService;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(AdminCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.ADMIN;
        TourService tourService = new TourService();
        StatisticService statisticService = new StatisticService();
        try {
            rq.setAttribute(Attribute.TOURS, tourService.readAll());
            rq.setAttribute(Attribute.STATISTIC, statisticService.getStatistic());
        } catch (DataBaseConnectionException e) {
            logger.trace("Admin page error ", e);
            return page;
        }

        return page;
    }
}
