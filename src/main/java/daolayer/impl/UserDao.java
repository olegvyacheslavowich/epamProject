package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Account;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDao extends Dao<User, Integer> {

    private static final String CREATE = "INSERT INTO " +
            "user(paper, document_num, birthday, phone, email, full_name, login_id) VALUES (?,?,?,?,?,?,?)";
    private static final String READ = "SELECT * FROM user WHERE user_id = ?;";
    private static final String READ_BY_ACCOUNT = "SELECT\n" +
            "  USER_ID,\n" +
            "  PAPER,\n" +
            "  DOCUMENT_NUM,\n" +
            "  BIRTHDAY,\n" +
            "  PHONE,\n" +
            "  EMAIL,\n" +
            "  FULL_NAME,\n" +
            "  ACCOUNT.LOGIN,\n" +
            "  ACCOUNT.PASSWORD\n" +
            "FROM USER\n" +
            "  INNER JOIN ACCOUNT ON USER.LOGIN_ID = ACCOUNT.LOGIN\n" +
            "WHERE LOGIN = ?";
    private static final String READ_ALL = "SELECT * FROM user";

    @Override
    public Integer create(User entity) {

        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(Number.FIRST, entity.getPaper());
            st.setLong(Number.SECOND, entity.getDocumentNum());
            st.setDate(Number.THIRD, entity.getBirthday());
            st.setString(Number.FOURTH, entity.getPhone());
            st.setString(Number.FIFTH, entity.getEmail());
            st.setString(Number.SEVENTH, entity.getAccount().getLogin());
            st.setString(Number.SIXTH, entity.getFullName());
            st.execute();
            rs = st.getGeneratedKeys();
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
    public User read(Integer id) {

        User user = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ)) {
            st.setInt(Number.FIRST, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setLogin(rs.getString(Number.EIGHTH));
                user = new User.UserBuilder()
                        .id(rs.getInt(Number.FIRST))
                        .paper((rs.getString(Number.SECOND)))
                        .documentNum(rs.getInt(Number.THIRD))
                        .email(rs.getString(Number.SIXTH))
                        .account(a)
                        .birthday(rs.getDate(Number.FOURTH))
                        .phone(rs.getString(Number.FIFTH))
                        .fullName(rs.getString(Number.SEVENTH))
                        .build();
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return user;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public List<User> readAll() {

        List<User> users = new ArrayList<>();
        User user;
        ResultSet rs = null;
        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(READ_ALL);
            while (rs.next()) {
                Account a = new Account();
                a.setLogin(rs.getString(Number.EIGHTH));
                user = new User.UserBuilder()
                        .id(rs.getInt(Number.FIRST))
                        .paper((rs.getString(Number.SECOND)))
                        .documentNum(rs.getInt(Number.THIRD))
                        .email(rs.getString(Number.SIXTH))
                        .account(a)
                        .birthday(rs.getDate(Number.FOURTH))
                        .phone(rs.getString(Number.FIFTH))
                        .fullName(rs.getString(Number.SEVENTH))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }

        return users;
    }

    public User readByAccount(Account account) {

        User user = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ_BY_ACCOUNT)) {
            st.setString(Number.FIRST, account.getLogin());
            rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setLogin(rs.getString(Number.EIGHTH));
                user = new User.UserBuilder()
                        .id(rs.getInt(Number.FIRST))
                        .paper((rs.getString(Number.SECOND)))
                        .documentNum(rs.getInt(Number.THIRD))
                        .email(rs.getString(Number.SIXTH))
                        .account(a)
                        .birthday(rs.getDate(Number.FOURTH))
                        .phone(rs.getString(Number.FIFTH))
                        .fullName(rs.getString(Number.SEVENTH))
                        .build();
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return user;
    }
}
