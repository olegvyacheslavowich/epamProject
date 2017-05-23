package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.Account;
import entity.CardType;
import entity.CreditCard;
import entity.Money;
import service.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoneyDao extends DAO<Money, Integer> {

    private static final Query READ_BY_LOGIN = new Query("" +
            "SELECT\n" +
            "  OWNER_NAME,\n" +
            "  NUMBER,\n" +
            "  CVV_NUMBER,\n" +
            "  DATE,\n" +
            "  CARD_TYPE.NAME,\n" +
            "  MONEY,\n" +
            "LOGIN," +
            "MONEY.MONEY_ID \n" +
            "FROM MONEY\n" +
            "  INNER JOIN CREDIT_CARD ON MONEY.CREDIT_CARD_ID = CREDIT_CARD.CREDIT_CARD_ID\n" +
            "  INNER JOIN CARD_TYPE ON CREDIT_CARD.CARD_TYPE_ID = CARD_TYPE.CARD_TYPE_ID\n" +
            "  INNER JOIN ACCOUNT ON MONEY.LOGIN_ID = ACCOUNT.LOGIN\n" +
            "WHERE ACCOUNT.LOGIN = ?");

    private static final Query READ = new Query("" +
            "SELECT\n" +
            "  OWNER_NAME,\n" +
            "  NUMBER,\n" +
            "  CVV_NUMBER,\n" +
            "  DATE,\n" +
            "  CARD_TYPE.NAME,\n" +
            "  MONEY," +
            "MONEY.MONEY_ID\n" +
            "FROM MONEY\n" +
            "  INNER JOIN CREDIT_CARD ON MONEY.CREDIT_CARD_ID = CREDIT_CARD.CREDIT_CARD_ID\n" +
            "  INNER JOIN CARD_TYPE ON CREDIT_CARD.CARD_TYPE_ID = CARD_TYPE.CARD_TYPE_ID\n" +
            "WHERE MONEY.MONEY_ID = ?");

    private static final Query UPDATE = new Query("" +
            "UPDATE MONEY SET MONEY.MONEY = ? WHERE MONEY.MONEY_ID = ?");

    private static final Query CREATE = new Query("" +
            "INSERT INTO money(credit_card_id, login_id) VALUES (?,?)");

    @Override
    public Integer create(Money entity) {

        Connection connection = getConnection();
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Num.FIRST.getNum(), entity.getCreditCard().getId());
            ps.setString(Num.SECOND.getNum(), entity.getAccount().getLogin());
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
    public Money read(Integer id) {


        Connection connection = getConnection();
        Money money = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), id);
            rs = ps.executeQuery();
            while (rs.next()) {
                money = new Money();
                Account account = new Account();
                CardType cardType = new CardType();
                CreditCard creditCard = new CreditCard();
                creditCard.setOwnerName(rs.getString(Num.FIRST.getNum()));
                creditCard.setNumber(rs.getLong(Num.SECOND.getNum()));
                creditCard.setCvvNumber(rs.getInt(Num.THIRD.getNum()));
                creditCard.setDate(rs.getDate(Num.FOURTH.getNum()));
                cardType.setName(rs.getString(Num.FIFTH.getNum()));
                money.setMoney(rs.getInt(Num.SIXTH.getNum()));
                money.setId(rs.getInt(Num.SEVENTH.getNum()));
                creditCard.setCardType(cardType);
                money.setAccount(account);
                money.setCreditCard(creditCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return money;
    }

    @Override
    public boolean update(Money entity) {

        Connection connection = getConnection();
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), entity.getMoney());
            ps.setInt(Num.SECOND.getNum(), entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public boolean delete(Money entity) {
        return false;
    }

    @Override
    public List<Money> readAll() {
        return null;
    }

    public List<Money> readByLogin(Account account) {

        Connection connection = getConnection();
        List<Money> moneys = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_LOGIN.getQuery())) {
            ps.setString(Num.FIRST.getNum(), account.getLogin());
            rs = ps.executeQuery();
            while (rs.next()) {
                Money money = new Money();
                Account a = new Account();
                CardType cardType = new CardType();
                CreditCard creditCard = new CreditCard();
                creditCard.setOwnerName(rs.getString(Num.FIRST.getNum()));
                creditCard.setNumber(rs.getLong(Num.SECOND.getNum()));
                creditCard.setCvvNumber(rs.getInt(Num.THIRD.getNum()));
                creditCard.setDate(rs.getDate(Num.FOURTH.getNum()));
                cardType.setName(rs.getString(Num.FIFTH.getNum()));
                money.setMoney(rs.getInt(Num.SIXTH.getNum()));
                a.setLogin(rs.getString(Num.SEVENTH.getNum()));
                money.setId(rs.getInt(Num.EIGHTH.getNum()));
                creditCard.setCardType(cardType);
                money.setAccount(a);
                money.setCreditCard(creditCard);
                moneys.add(money);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return moneys;
    }
}
