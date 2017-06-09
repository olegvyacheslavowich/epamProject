package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDao extends Dao<Client, Integer> {

    private static final String CREATE = "INSERT INTO CLIENT(FULL_NAME, PAPER, DOCUMENT_NUM, PHONE, BIRTHDAY, EMAIL) VALUES (?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM client WHERE client_id = ?";

    @Override
    public Integer create(Client entity) {

        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(Number.FIRST, entity.getFullName());
            ps.setString(Number.SECOND, entity.getPaper());
            ps.setLong(Number.THIRD, entity.getDocumentNum());
            ps.setString(Number.FOURTH, entity.getPhone());
            ps.setDate(Number.FIFTH, entity.getBirthday());
            ps.setString(Number.SIXTH, entity.getEmail());
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
    public Client read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Client entity) {
        return false;
    }

    @Override
    public boolean delete(Client entity) {

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
    public List<Client> readAll() {
        return null;
    }


}
