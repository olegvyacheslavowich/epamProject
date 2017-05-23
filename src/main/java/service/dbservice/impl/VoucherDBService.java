package service.dbservice.impl;

import daolayer.impl.VoucherDao;
import entity.Voucher;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 18.05.2017.
 */
public class VoucherDBService extends DBService {

    VoucherDao dao = new VoucherDao();

    public VoucherDBService(HttpSession session) {
        super(session);
    }

    public boolean update(Voucher voucher) {
        return dao.update(voucher);
    }

    public boolean delete(Voucher voucher) {
        return dao.delete(voucher);
    }

    /**
     * добавляет путевку в БД и возвращает id добавленной путевки
     *
     * @param voucher путевка, которую нужно добавить
     * @return id добавленной путевки
     */
    public int create(Voucher voucher) {
        return dao.create(voucher);
    }

}
