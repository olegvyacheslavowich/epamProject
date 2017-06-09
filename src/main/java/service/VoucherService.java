package service;

import daolayer.DaoFactory;
import daolayer.impl.VoucherDao;
import entity.Voucher;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;

public class VoucherService {


    public boolean update(Voucher voucher) throws DataBaseConnectionException {

        boolean result;
        try (DaoFactory factory = new DaoFactory()) {
            VoucherDao voucherDao = factory.getVoucherDao();
            result = voucherDao.update(voucher);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return result;
    }

    public boolean delete(Voucher voucher) throws DataBaseConnectionException {

        boolean result;
        try (DaoFactory factory = new DaoFactory()) {
            VoucherDao voucherDao = factory.getVoucherDao();
            result = voucherDao.delete(voucher);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);

        }
        return result;
    }

    /**
     * добавляет путевку в БД и возвращает id добавленной путевки
     *
     * @param voucher путевка, которую нужно добавить
     * @return id добавленной путевки
     */
    public int create(Voucher voucher) throws DataBaseConnectionException {

        int id;
        try (DaoFactory factory = new DaoFactory()) {
            VoucherDao voucherDao = factory.getVoucherDao();
            id = voucherDao.create(voucher);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return id;
    }

}
