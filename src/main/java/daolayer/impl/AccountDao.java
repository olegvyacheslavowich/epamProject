package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AccountDao extends Dao<Account, String> {

    private static final String CREATE = "INSERT INTO account VALUES (?,?);";
    private static final String READ = "SELECT * FROM Account WHERE login = ?;";
    private static final String READ_BY_LOGIN_AND_PASSWORD = "SELECT * FROM Account WHERE login = ? and password = ?";

    @Override
    public String create(Account entity) {

        String result = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(Number.FIRST, entity.getLogin());
            st.setString(Number.SECOND, DigestUtils.md5Hex(entity.getPassword()));
            st.execute();
            rs = st.getGeneratedKeys();
            rs.next();
            result = rs.getString(Number.FIRST);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            close(rs);
        }
        return result;
    }

    @Override
    public Account read(String id) {

        Account account = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ)) {
            st.setString(Number.FIRST, id);
            rs = st.executeQuery();
            account = getAccount(rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            close(rs);
        }
        return account;
    }

    @Override
    public boolean update(Account entity) {
        return false;
    }

    @Override
    public boolean delete(Account entity) {
        return false;
    }

    @Override
    public List<Account> readAll() {
        return null;
    }

    public Account readByLoginAndPassword(String login, String password) {

        Account account = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ_BY_LOGIN_AND_PASSWORD)) {
            st.setString(Number.FIRST, login);
            st.setString(Number.SECOND, password);
            rs = st.executeQuery();
            account = getAccount(rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            close(rs);
        }
        return account;
    }

    private Account getAccount(ResultSet rs) throws SQLException {
        Account account = null;
        while (rs.next()) {
            account = new Account();
            account.setLogin(rs.getString(Number.FIRST));
            account.setPassword(rs.getString(Number.SECOND));
        }
        return account;
    }
}
