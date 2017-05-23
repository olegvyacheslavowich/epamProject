package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.Account;
import entity.User;
import service.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends DAO<User, Integer> {

    private static final Query CREATE = new Query("INSERT INTO " +
            "user(paper, document_num, birthday, phone, email, full_name, login_id) VALUES (?,?,?,?,?,?,?)");
    private static final Query READ = new Query("SELECT * FROM user WHERE user_id = ?;");
    private static final Query READ_BY_ACCOUNT = new Query("SELECT\n" +
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
            "WHERE LOGIN = ?");
    private static final Query READ_ALL = new Query("SELECT * FROM user");

    @Override
    public Integer create(User entity) {

        Connection connection = getConnection();
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(Num.FIRST.getNum(), entity.getPaper());
            st.setLong(Num.SECOND.getNum(), entity.getDocumentNum());
            st.setDate(Num.THIRD.getNum(), entity.getBirthday());
            st.setString(Num.FOURTH.getNum(), entity.getPhone());
            st.setString(Num.FIFTH.getNum(), entity.getEmail());
            st.setString(Num.SEVENTH.getNum(), entity.getAccount().getLogin());
            st.setString(Num.SIXTH.getNum(), entity.getFullName());
            st.execute();
            rs = st.getGeneratedKeys();
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
    public User read(Integer id) {

        Connection connection = getConnection();
        User user = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ.getQuery())) {
            st.setInt(Num.FIRST.getNum(), id);
            rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setLogin(rs.getString(Num.EIGHTH.getNum()));
                user = new User.UserBuilder()
                        .id(rs.getInt(Num.FIRST.getNum()))
                        .paper((rs.getString(Num.SECOND.getNum())))
                        .documentNum(rs.getInt(Num.THIRD.getNum()))
                        .email(rs.getString(Num.SIXTH.getNum()))
                        .account(a)
                        .birthday(rs.getDate(Num.FOURTH.getNum()))
                        .phone(rs.getString(Num.FIFTH.getNum()))
                        .fullName(rs.getString(Num.SEVENTH.getNum()))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
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

        Connection connection = getConnection();
        List<User> users = new ArrayList<>();
        User user;
        ResultSet rs = null;
        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(READ_ALL.getQuery());
            while (rs.next()) {
                Account a = new Account();
                a.setLogin(rs.getString(Num.EIGHTH.getNum()));
                user = new User.UserBuilder()
                        .id(rs.getInt(Num.FIRST.getNum()))
                        .paper((rs.getString(Num.SECOND.getNum())))
                        .documentNum(rs.getInt(Num.THIRD.getNum()))
                        .email(rs.getString(Num.SIXTH.getNum()))
                        .account(a)
                        .birthday(rs.getDate(Num.FOURTH.getNum()))
                        .phone(rs.getString(Num.FIFTH.getNum()))
                        .fullName(rs.getString(Num.SEVENTH.getNum()))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return users;
    }


    public User readByAccount(Account account) {

        Connection connection = getConnection();
        User user = null;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(READ_BY_ACCOUNT.getQuery())) {
            st.setString(Num.FIRST.getNum(), account.getLogin());
            rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setLogin(rs.getString(Num.EIGHTH.getNum()));
                user = new User.UserBuilder()
                        .id(rs.getInt(Num.FIRST.getNum()))
                        .paper((rs.getString(Num.SECOND.getNum())))
                        .documentNum(rs.getInt(Num.THIRD.getNum()))
                        .email(rs.getString(Num.SIXTH.getNum()))
                        .account(a)
                        .birthday(rs.getDate(Num.FOURTH.getNum()))
                        .phone(rs.getString(Num.FIFTH.getNum()))
                        .fullName(rs.getString(Num.SEVENTH.getNum()))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return user;
    }
}
