package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.CardType;
import service.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CardTypeDao extends DAO<CardType, Integer> {

    private static final Query READ = new Query("SELECT * FROM card_type WHERE CARD_TYPE.NAME = ?");


    @Override
    public Integer create(CardType entity) {
        return null;
    }

    @Override
    public CardType read(Integer id) {
        return null;
    }

    @Override
    public boolean update(CardType entity) {
        return false;
    }

    @Override
    public boolean delete(CardType entity) {
        return false;
    }

    @Override
    public List<CardType> readAll() {
        return null;
    }

    public CardType readByType(CardType type) {

        Connection connection = getConnection();
        CardType t = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ.getQuery())) {
            ps.setString(Num.FIRST.getNum(), type.getName());
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new CardType();
                t.setId(rs.getInt(Num.FIRST.getNum()));
                t.setName(rs.getString(Num.SECOND.getNum()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return t;
    }
}
