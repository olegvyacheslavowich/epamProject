package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.CreditCard;
import service.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 20_ok on 18.05.2017.
 */
public class CreditCardDao extends DAO<CreditCard, Integer> {


    private static final Query CREATE = new Query("" +
            "INSERT INTO " +
            "credit_card(card_type_id, owner_name, number, cvv_number, date)" +
            "VALUES " +
            " (?,?,?,?,?)");

    @Override
    public Integer create(CreditCard entity) {
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Num.FIRST.getNum(), entity.getCardType().getId());
            ps.setString(Num.SECOND.getNum(), entity.getOwnerName());
            ps.setLong(Num.THIRD.getNum(), entity.getNumber());
            ps.setInt(Num.FOURTH.getNum(), entity.getCvvNumber());
            ps.setDate(Num.FIFTH.getNum(), entity.getDate());
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
