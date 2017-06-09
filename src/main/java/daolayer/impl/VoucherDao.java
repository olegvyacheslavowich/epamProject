package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Voucher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VoucherDao extends Dao<Voucher, Integer> {

    private static final String CREATE = "INSERT INTO VOUCHER(TOUR_ID,FLIGHT_TO_ID, FLIGHT_FROM_ID, CLIENT_NUMBER, PRICE) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM voucher WHERE voucher_id = ?";
    private static final String UPDATE = "UPDATE voucher SET " +
            "TOUR_ID = ?,FLIGHT_TO_ID = ?, FLIGHT_FROM_ID = ?, CLIENT_NUMBER = ?, PRICE = ? WHERE voucher_id = ?";


    @Override
    public Integer create(Voucher entity) {

        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Number.FIRST, entity.getTour().getId());
            ps.setInt(Number.SECOND, entity.getFlightTo().getId());
            ps.setInt(Number.THIRD, entity.getFlightFrom().getId());
            ps.setInt(Number.FOURTH, entity.getClientNumber());
            ps.setInt(Number.FIFTH, entity.getPrice());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(Number.FIRST);
        } catch (SQLException e) {
            logger.info(e.getMessage());

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
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setInt(Number.FIRST, entity.getTour().getId());
            ps.setInt(Number.SECOND, entity.getFlightTo().getId());
            ps.setInt(Number.THIRD, entity.getFlightFrom().getId());
            ps.setInt(Number.FOURTH, entity.getClientNumber());
            ps.setInt(Number.FIFTH, entity.getPrice());
            ps.setInt(Number.SIXTH, entity.getId());

            result = ps.execute();
        } catch (SQLException e) {
            logger.info(e.getMessage());

        }
        return result;
    }

    @Override
    public boolean delete(Voucher entity) {

        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(Number.FIRST, entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return result;


    }

    @Override
    public List<Voucher> readAll() {
        return null;
    }


}
