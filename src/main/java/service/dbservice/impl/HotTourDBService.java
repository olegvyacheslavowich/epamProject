package service.dbservice.impl;

import daolayer.impl.HotTourDao;
import entity.HotTour;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 19.05.2017.
 */
public class HotTourDBService extends DBService {

    private HotTourDao dao = new HotTourDao();

    public HotTourDBService(HttpSession session) {
        super(session);
    }

    public List<HotTour> readAll() {
        return dao.readAll();
    }

    public int create(HotTour tour) {
        return dao.create(tour);

    }
}
