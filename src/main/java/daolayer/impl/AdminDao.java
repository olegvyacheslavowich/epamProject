package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.Account;
import entity.Admin;
import service.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao extends DAO<Admin, Integer> {

    private static final Query READ_BY_LOGIN = new Query("SELECT * FROM \"ADMIN\" WHERE \"ADMIN\".LOGIN_ID = ?");

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

        Connection connection = getConnection();
        Admin admin = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_LOGIN.getQuery())) {
            ps.setString(Num.FIRST.getNum(), entity.getAccount().getLogin());
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = new Admin();
                Account account = new Account();
                admin.setId(rs.getInt(Num.FIRST.getNum()));
                account.setLogin(rs.getString(Num.SECOND.getNum()));
                admin.setAccount(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return admin;
    }
}
