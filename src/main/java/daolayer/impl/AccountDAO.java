package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.Account;
import org.apache.commons.codec.digest.DigestUtils;
import service.Query;

import java.sql.*;
import java.util.List;

/**
 * Created by 20_ok on 21.03.2017.
 */
public class AccountDAO extends DAO<Account, Integer> {


    private static final Query CREATE = new Query("INSERT INTO account VALUES (?,?);");
    private static final Query READ = new Query("SELECT * FROM Account WHERE login = ?;");
    private static final Query READ_BY_LOGIN_AND_PASSWORD = new Query("SELECT * FROM Account WHERE login = ? and password = ?");

    @Override
    public Integer create(Account entity) {

        int result = -1;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(
                CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(Num.FIRST.getNum(), entity.getLogin());
            st.setString(Num.SECOND.getNum(), DigestUtils.md5Hex(entity.getPassword()));
            st.execute();
            rs = st.getGeneratedKeys();
            rs.next();
            result = rs.getInt(Num.FIRST.getNum());
        } catch (SQLException e) {
        } finally {
            close(rs);
        }
        return result;
    }

    @Override
    public Account read(Integer id) {

        Account account = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ.getQuery())) {
            st.setInt(Num.FIRST.getNum(), id);
            rs = st.executeQuery();
            account = getAccount(account, rs);
        } catch (SQLException e) {
            e.printStackTrace();
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

        try (PreparedStatement st = connection.prepareStatement(READ_BY_LOGIN_AND_PASSWORD.getQuery())) {
            st.setString(Num.FIRST.getNum(), login);
            st.setString(Num.SECOND.getNum(), password);
            rs = st.executeQuery();
            account = getAccount(account, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return account;
    }

    private Account getAccount(Account account, ResultSet rs) throws SQLException {
        while (rs.next()) {
            account = new Account();
            account.setLogin(rs.getString(Num.FIRST.getNum()));
            account.setPassword(rs.getString(Num.SECOND.getNum()));
        }
        return account;
    }
}
