package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.*;
import service.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20_ok on 08.05.2017.
 */
public class VoucherDao extends DAO<Voucher, Integer> {


    private static final Query CREATE = new Query("INSERT INTO VOUCHER(TOUR_ID,FLIGHT_TO_ID, FLIGHT_FROM_ID, CLIENT_NUMBER, PRICE) VALUES (?,?,?,?,?)");
    private static final Query DELETE = new Query("DELETE FROM voucher WHERE voucher_id = ?");
    private static final Query UPDATE = new Query("UPDATE voucher SET " +
            "TOUR_ID = ?,FLIGHT_TO_ID = ?, FLIGHT_FROM_ID = ?, CLIENT_NUMBER = ?, PRICE = ? WHERE voucher_id = ?");


    @Override
    public Integer create(Voucher entity) {
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Num.FIRST.getNum(), entity.getTour().getId());
            ps.setInt(Num.SECOND.getNum(), entity.getFlightTo().getId());
            ps.setInt(Num.THIRD.getNum(), entity.getFlightFrom().getId());
            ps.setInt(Num.FOURTH.getNum(), entity.getClientNumber());
            ps.setInt(Num.FIFTH.getNum(), entity.getPrice());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(Num.FIRST.getNum());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return result;
    }

    @Override
    public Voucher read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Voucher entity) {

        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), entity.getTour().getId());
            ps.setInt(Num.SECOND.getNum(), entity.getFlightTo().getId());
            ps.setInt(Num.THIRD.getNum(), entity.getFlightFrom().getId());
            ps.setInt(Num.FOURTH.getNum(), entity.getClientNumber());
            ps.setInt(Num.FIFTH.getNum(), entity.getPrice());
            ps.setInt(Num.SIXTH.getNum(), entity.getId());

            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Voucher entity) {

        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;


    }

    @Override
    public List<Voucher> readAll() {
        return null;
    }

}
