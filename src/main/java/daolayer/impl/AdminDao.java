package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Account;
import entity.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao extends Dao<Admin, Integer> {

    private static final String READ_BY_LOGIN = "SELECT * FROM \"ADMIN\" WHERE \"ADMIN\".LOGIN_ID = ?";

    @Override
    public Integer create(Admin entity) {
        return null;
    }

    @Override
    public Admin read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Admin entity) {
        return false;
    }

    @Override
    public boolean delete(Admin entity) {
        return false;
    }

    @Override
    public List<Admin> readAll() {
        return null;
    }

    public Admin readByLogin(Admin entity) {

        Admin admin = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_LOGIN)) {
            ps.setString(Number.FIRST, entity.getAccount().getLogin());
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = new Admin();
                Account account = new Account();
                admin.setId(rs.getInt(Number.FIRST));
                account.setLogin(rs.getString(Number.SECOND));
                admin.setAccount(account);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            close(rs);
        }

        return admin;
    }
}
