package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.Client;
import service.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDao extends DAO<Client, Integer> {

    private static final Query CREATE = new Query("INSERT INTO CLIENT(FULL_NAME, PAPER, DOCUMENT_NUM, PHONE, BIRTHDAY, EMAIL) VALUES (?,?,?,?,?,?)");
    private static final Query DELETE = new Query("DELETE FROM client WHERE client_id = ?");

    @Override
    public Integer create(Client entity) {

        Connection connection = getConnection();
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(Num.FIRST.getNum(), entity.getFullName());
            ps.setString(Num.SECOND.getNum(), entity.getPaper());
            ps.setLong(Num.THIRD.getNum(), entity.getDocumentNum());
            ps.setString(Num.FOURTH.getNum(), entity.getPhone());
            ps.setDate(Num.FIFTH.getNum(), entity.getBirthday());
            ps.setString(Num.SIXTH.getNum(), entity.getEmail());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(Num.FIRST.getNum());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return result;
    }

    @Override
    public Client read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Client entity) {
        return false;
    }

    @Override
    public boolean delete(Client entity) {

        Connection connection = getConnection();
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public List<Client> readAll() {
        return null;
    }


}
