package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.CardType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CardTypeDao extends Dao<CardType, Integer> {

    private static final String READ = "SELECT * FROM card_type WHERE CARD_TYPE.NAME = ?";


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

        CardType t = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ)) {
            ps.setString(Number.FIRST, type.getName());
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new CardType();
                t.setId(rs.getInt(Number.FIRST));
                t.setName(rs.getString(Number.SECOND));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }

        return t;
    }
}
