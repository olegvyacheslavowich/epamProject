package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Account;
import entity.CardType;
import entity.CreditCard;
import entity.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoneyDao extends Dao<Money, Integer> {

    private static final String READ_BY_LOGIN =
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
                    "WHERE ACCOUNT.LOGIN = ?";

    private static final String READ =
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
                    "WHERE MONEY.MONEY_ID = ?";

    private static final String UPDATE =
            "UPDATE MONEY SET MONEY.MONEY = ? WHERE MONEY.MONEY_ID = ?";

    private static final String CREATE =
            "INSERT INTO money(credit_card_id, login_id) VALUES (?,?)";

    @Override
    public Integer create(Money entity) {

        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Number.FIRST, entity.getCreditCard().getId());
            ps.setString(Number.SECOND, entity.getAccount().getLogin());
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
    public Money read(Integer id) {

        Money money = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ)) {
            ps.setInt(Number.FIRST, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                money = new Money();
                Account account = new Account();
                CardType cardType = new CardType();
                CreditCard creditCard = new CreditCard();
                creditCard.setOwnerName(rs.getString(Number.FIRST));
                creditCard.setNumber(rs.getLong(Number.SECOND));
                creditCard.setCvvNumber(rs.getInt(Number.THIRD));
                creditCard.setDate(rs.getDate(Number.FOURTH));
                cardType.setName(rs.getString(Number.FIFTH));
                money.setMoney(rs.getInt(Number.SIXTH));
                money.setId(rs.getInt(Number.SEVENTH));
                creditCard.setCardType(cardType);
                money.setAccount(account);
                money.setCreditCard(creditCard);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }

        return money;
    }

    @Override
    public boolean update(Money entity) {

        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setInt(Number.FIRST, entity.getMoney());
            ps.setInt(Number.SECOND, entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            logger.info(e.getMessage());

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

        List<Money> moneys = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_LOGIN)) {
            ps.setString(Number.FIRST, account.getLogin());
            rs = ps.executeQuery();
            while (rs.next()) {
                Money money = new Money();
                Account a = new Account();
                CardType cardType = new CardType();
                CreditCard creditCard = new CreditCard();
                creditCard.setOwnerName(rs.getString(Number.FIRST));
                creditCard.setNumber(rs.getLong(Number.SECOND));
                creditCard.setCvvNumber(rs.getInt(Number.THIRD));
                creditCard.setDate(rs.getDate(Number.FOURTH));
                cardType.setName(rs.getString(Number.FIFTH));
                money.setMoney(rs.getInt(Number.SIXTH));
                a.setLogin(rs.getString(Number.SEVENTH));
                money.setId(rs.getInt(Number.EIGHTH));
                creditCard.setCardType(cardType);
                money.setAccount(a);
                money.setCreditCard(creditCard);
                moneys.add(money);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }

        return moneys;
    }
}
