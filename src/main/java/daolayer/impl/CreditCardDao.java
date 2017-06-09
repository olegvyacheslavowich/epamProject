package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.CreditCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CreditCardDao extends Dao<CreditCard, Integer> {


    private static final String CREATE =
            "INSERT INTO " +
                    "credit_card(card_type_id, owner_name, number, cvv_number, date)" +
                    "VALUES " +
                    " (?,?,?,?,?)";

    @Override
    public Integer create(CreditCard entity) {

        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Number.FIRST, entity.getCardType().getId());
            ps.setString(Number.SECOND, entity.getOwnerName());
            ps.setLong(Number.THIRD, entity.getNumber());
            ps.setInt(Number.FOURTH, entity.getCvvNumber());
            ps.setDate(Number.FIFTH, entity.getDate());
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
    public CreditCard read(Integer id) {
        return null;
    }

    @Override
    public boolean update(CreditCard entity) {
        return false;
    }

    @Override
    public boolean delete(CreditCard entity) {
        return false;
    }

    @Override
    public List<CreditCard> readAll() {
        return null;
    }

}
