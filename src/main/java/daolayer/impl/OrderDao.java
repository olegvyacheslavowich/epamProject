package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDao extends Dao<Order, Integer> {

    private static final String CREATE = "INSERT INTO ORDERS (CLIENT_ID, VOUCHER_ID, LOGIN_ID, MONEY_ID) VALUES (?,?,?,?)";
    private static final String READ_ALL_BY_ACCOUNT_ID = "SELECT\n" +
            "  ORDERS.ORDER_ID,\n" +
            "  CLIENT.CLIENT_ID,\n" +
            "  CLIENT.FULL_NAME,\n" +
            "  CLIENT.PAPER,\n" +
            "  CLIENT.DOCUMENT_NUM,\n" +
            "  CLIENT.PHONE,\n" +
            "  CLIENT.BIRTHDAY,\n" +
            "  CLIENT.EMAIL,\n" +
            "  VOUCHER.VOUCHER_ID,\n" +
            "  VOUCHER.CLIENT_NUMBER,\n" +
            "  VOUCHER.PRICE,\n" +
            "  FLIGHT_TO.DATE,\n" +
            "  PLANE_TO.NAME,\n" +
            "  PLANE_TO.DEPARTURE_TIME,\n" +
            "  FLIGHT_FROM.DATE,\n" +
            "  PLANE_FROM.NAME,\n" +
            "  PLANE_FROM.DEPARTURE_TIME,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  CITIES.NAME,\n" +
            "  COUNTRIES.NAME,\n" +
            "  DEPARTURE_CITY.NAME," +
            "ORDERS.MONEY_ID," +
            "TOUR.TOUR_ID," +
            "FLIGHT_TO.FLIGHT_ID,\n" +
            "FLIGHT_FROM.FLIGHT_ID\n" +
            "FROM ORDERS\n" +
            "  INNER JOIN CLIENT ON ORDERS.CLIENT_ID = CLIENT.CLIENT_ID\n" +
            "  INNER JOIN VOUCHER ON ORDERS.VOUCHER_ID = VOUCHER.VOUCHER_ID\n" +
            "  INNER JOIN TOUR ON VOUCHER.TOUR_ID = TOUR.TOUR_ID\n" +
            "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "  INNER JOIN ACCOUNT ON ORDERS.LOGIN_ID = ACCOUNT.LOGIN\n" +
            "  INNER JOIN FLIGHT AS FLIGHT_TO ON VOUCHER.FLIGHT_TO_ID = FLIGHT_TO.FLIGHT_ID\n" +
            "  INNER JOIN FLIGHT AS FLIGHT_FROM ON VOUCHER.FLIGHT_FROM_ID = FLIGHT_FROM.FLIGHT_ID\n" +
            "  INNER JOIN PLANE AS PLANE_TO ON FLIGHT_TO.PLANE_ID = PLANE_TO.NAME\n" +
            "  INNER JOIN PLANE AS PLANE_FROM ON FLIGHT_FROM.PLANE_ID = PLANE_FROM.NAME\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN CITIES AS DEPARTURE_CITY ON PLANE_TO.DEPARTURE_CITY_ID = DEPARTURE_CITY.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.COUNTRY_ID = COUNTRIES.COUNTRY_ID\n" +
            "WHERE ACCOUNT.LOGIN = ?";

    private static final String READ_ALL = "SELECT\n" +
            "  ORDERS.ORDER_ID,\n" +
            "  CLIENT.CLIENT_ID,\n" +
            "  CLIENT.FULL_NAME,\n" +
            "  CLIENT.PAPER,\n" +
            "  CLIENT.DOCUMENT_NUM,\n" +
            "  CLIENT.PHONE,\n" +
            "  CLIENT.BIRTHDAY,\n" +
            "  CLIENT.EMAIL,\n" +
            "  VOUCHER.VOUCHER_ID,\n" +
            "  VOUCHER.CLIENT_NUMBER,\n" +
            "  VOUCHER.PRICE,\n" +
            "  FLIGHT_TO.DATE,\n" +
            "  PLANE_TO.NAME,\n" +
            "  PLANE_TO.DEPARTURE_TIME,\n" +
            "  FLIGHT_FROM.DATE,\n" +
            "  PLANE_FROM.NAME,\n" +
            "  PLANE_FROM.DEPARTURE_TIME,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  CITIES.NAME,\n" +
            "  COUNTRIES.NAME,\n" +
            "  DEPARTURE_CITY.NAME," +
            "ORDERS.MONEY_ID," +
            "TOUR.TOUR_ID," +
            "FLIGHT_TO.FLIGHT_ID,\n" +
            "FLIGHT_FROM.FLIGHT_ID\n" +
            "FROM ORDERS\n" +
            "  INNER JOIN CLIENT ON ORDERS.CLIENT_ID = CLIENT.CLIENT_ID\n" +
            "  INNER JOIN VOUCHER ON ORDERS.VOUCHER_ID = VOUCHER.VOUCHER_ID\n" +
            "  INNER JOIN TOUR ON VOUCHER.TOUR_ID = TOUR.TOUR_ID\n" +
            "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "  INNER JOIN ACCOUNT ON ORDERS.LOGIN_ID = ACCOUNT.LOGIN\n" +
            "  INNER JOIN FLIGHT AS FLIGHT_TO ON VOUCHER.FLIGHT_TO_ID = FLIGHT_TO.FLIGHT_ID\n" +
            "  INNER JOIN FLIGHT AS FLIGHT_FROM ON VOUCHER.FLIGHT_FROM_ID = FLIGHT_FROM.FLIGHT_ID\n" +
            "  INNER JOIN PLANE AS PLANE_TO ON FLIGHT_TO.PLANE_ID = PLANE_TO.NAME\n" +
            "  INNER JOIN PLANE AS PLANE_FROM ON FLIGHT_FROM.PLANE_ID = PLANE_FROM.NAME\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN CITIES AS DEPARTURE_CITY ON PLANE_TO.DEPARTURE_CITY_ID = DEPARTURE_CITY.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.COUNTRY_ID = COUNTRIES.COUNTRY_ID";

    private static final String READ = "SELECT\n" +
            "  ORDERS.ORDER_ID,\n" +
            "  CLIENT.CLIENT_ID,\n" +
            "  CLIENT.FULL_NAME,\n" +
            "  CLIENT.PAPER,\n" +
            "  CLIENT.DOCUMENT_NUM,\n" +
            "  CLIENT.PHONE,\n" +
            "  CLIENT.BIRTHDAY,\n" +
            "  CLIENT.EMAIL,\n" +
            "  VOUCHER.VOUCHER_ID,\n" +
            "  VOUCHER.CLIENT_NUMBER,\n" +
            "  VOUCHER.PRICE,\n" +
            "  FLIGHT_TO.DATE,\n" +
            "  PLANE_TO.NAME,\n" +
            "  PLANE_TO.DEPARTURE_TIME,\n" +
            "  FLIGHT_FROM.DATE,\n" +
            "  PLANE_FROM.NAME,\n" +
            "  PLANE_FROM.DEPARTURE_TIME,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  CITIES.NAME,\n" +
            "  COUNTRIES.NAME,\n" +
            "  DEPARTURE_CITY.NAME," +
            "ORDERS.MONEY_ID," +
            "TOUR.TOUR_ID,\n" +
            "FLIGHT_TO.FLIGHT_ID,\n" +
            "FLIGHT_FROM.FLIGHT_ID\n" +
            "FROM ORDERS\n" +
            "  INNER JOIN CLIENT ON ORDERS.CLIENT_ID = CLIENT.CLIENT_ID\n" +
            "  INNER JOIN VOUCHER ON ORDERS.VOUCHER_ID = VOUCHER.VOUCHER_ID\n" +
            "  INNER JOIN TOUR ON VOUCHER.TOUR_ID = TOUR.TOUR_ID\n" +
            "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "  INNER JOIN ACCOUNT ON ORDERS.LOGIN_ID = ACCOUNT.LOGIN\n" +
            "  INNER JOIN FLIGHT AS FLIGHT_TO ON VOUCHER.FLIGHT_TO_ID = FLIGHT_TO.FLIGHT_ID\n" +
            "  INNER JOIN FLIGHT AS FLIGHT_FROM ON VOUCHER.FLIGHT_FROM_ID = FLIGHT_FROM.FLIGHT_ID\n" +
            "  INNER JOIN PLANE AS PLANE_TO ON FLIGHT_TO.PLANE_ID = PLANE_TO.NAME\n" +
            "  INNER JOIN PLANE AS PLANE_FROM ON FLIGHT_FROM.PLANE_ID = PLANE_FROM.NAME\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN CITIES AS DEPARTURE_CITY ON PLANE_TO.DEPARTURE_CITY_ID = DEPARTURE_CITY.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.COUNTRY_ID = COUNTRIES.COUNTRY_ID\n" +
            "WHERE ORDERS.ORDER_ID = ?";

    private static final String DELETE = "DELETE FROM orders WHERE order_id = ?";

    @Override
    public Integer create(Order entity) {

        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Number.FIRST, entity.getClient().getId());
            ps.setInt(Number.SECOND, entity.getVoucher().getId());
            ps.setString(Number.THIRD, entity.getAccount().getLogin());
            ps.setInt(Number.FOURTH, entity.getMoney().getId());
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
    public Order read(Integer id) {

        Order order = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ)) {
            ps.setInt(Number.FIRST, id);
            rs = ps.executeQuery();
            order = setOrder(rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }

        return order;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {

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
    public List<Order> readAll() {

        List<Order> orders = null;
        ResultSet rs = null;
        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(READ_ALL);
            orders = setOrders(rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }

        return orders;
    }

    public List<Order> readAllByAccount(Account account) {

        List<Order> orders = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_ACCOUNT_ID)) {
            ps.setString(Number.FIRST, account.getLogin());
            rs = ps.executeQuery();
            orders = setOrders(rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return orders;
    }

    private List<Order> setOrders(ResultSet rs) throws SQLException {

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            Voucher voucher = new Voucher();
            Client client = new Client();
            Tour tour = new Tour();
            Hotel hotel = new Hotel();
            Flight flightTo = new Flight();
            Flight flightFrom = new Flight();
            Plane planeTo = new Plane();
            Plane planeFrom = new Plane();
            City city = new City();
            City departureCity = new City();
            Country country = new Country();
            Money money = new Money();

            order.setId(rs.getInt(Number.FIRST));
            client.setId(rs.getInt(Number.SECOND));
            client.setFullName(rs.getString(Number.THIRD));
            client.setPaper(rs.getString(Number.FOURTH));
            client.setDocumentNum(rs.getInt(Number.FIFTH));
            client.setPhone(rs.getString(Number.SIXTH));
            client.setBirthday(rs.getDate(Number.SEVENTH));
            client.setEmail(rs.getString(Number.EIGHTH));
            voucher.setId(rs.getInt(Number.NINTH));
            voucher.setClientNumber(rs.getInt(Number.TENTH));
            voucher.setPrice(rs.getInt(Number.ELEVENTH));
            flightTo.setDate(rs.getDate(Number.TWELFTH));
            planeTo.setName(rs.getString(Number.THIRTEENTH));
            planeTo.setDepartureTime(rs.getTime(Number.FOURTEENTH));
            flightFrom.setDate(rs.getDate(Number.FIFTEENTH));
            planeFrom.setName(rs.getString(Number.SIXTEENTH));
            planeFrom.setDepartureTime(rs.getTime(Number.SEVENTEENTH));
            tour.setId(rs.getInt(Number.TWENTY_EIGHTH));
            tour.setDescription(rs.getString(Number.EIGHTEENTH));
            tour.setDays(rs.getInt(Number.NINETEENTH));
            tour.setPrice(rs.getInt(Number.TWENTIETH));
            hotel.setName(rs.getString(Number.TWENTY_FIRST));
            hotel.setDescription(rs.getString(Number.TWENTY_SECOND));
            hotel.setStarsNumber(rs.getInt(Number.TWENTY_THIRD));
            city.setName(rs.getString(Number.TWENTY_FOURTH));
            country.setName(rs.getString(Number.TWENTY_FIFTH));
            departureCity.setName(rs.getString(Number.TWENTY_SIXTH));
            money.setId(rs.getInt(Number.TWENTY_SEVENTH));

            planeTo.setDepartureCity(departureCity);
            city.setCountry(country);
            tour.setCity(city);
            tour.setHotel(hotel);
            voucher.setTour(tour);
            flightTo.setPlane(planeTo);
            flightFrom.setPlane(planeFrom);
            voucher.setFlightTo(flightTo);
            voucher.setFlightFrom(flightFrom);
            order.setClient(client);
            order.setVoucher(voucher);
            order.setMoney(money);

            orders.add(order);
        }
        return orders;
    }

    private Order setOrder(ResultSet rs) throws SQLException {
        Order order = null;
        while (rs.next()) {
            order = new Order();
            Voucher voucher = new Voucher();
            Client client = new Client();
            Tour tour = new Tour();
            Hotel hotel = new Hotel();
            Flight flightTo = new Flight();
            Flight flightFrom = new Flight();
            Plane planeTo = new Plane();
            Plane planeFrom = new Plane();
            City city = new City();
            City departureCity = new City();
            Country country = new Country();
            Money money = new Money();

            order.setId(rs.getInt(Number.FIRST));
            client.setId(rs.getInt(Number.SECOND));
            client.setFullName(rs.getString(Number.THIRD));
            client.setPaper(rs.getString(Number.FOURTH));
            client.setDocumentNum(rs.getInt(Number.FIFTH));
            client.setPhone(rs.getString(Number.SIXTH));
            client.setBirthday(rs.getDate(Number.SEVENTH));
            client.setEmail(rs.getString(Number.EIGHTH));
            voucher.setId(rs.getInt(Number.NINTH));
            voucher.setClientNumber(rs.getInt(Number.TENTH));
            voucher.setPrice(rs.getInt(Number.ELEVENTH));
            flightTo.setDate(rs.getDate(Number.TWELFTH));
            planeTo.setName(rs.getString(Number.THIRTEENTH));
            planeTo.setDepartureTime(rs.getTime(Number.FOURTEENTH));
            flightFrom.setDate(rs.getDate(Number.FIFTEENTH));
            planeFrom.setName(rs.getString(Number.SIXTEENTH));
            planeFrom.setDepartureTime(rs.getTime(Number.SEVENTEENTH));
            tour.setId(rs.getInt(Number.TWENTY_EIGHTH));
            tour.setDescription(rs.getString(Number.EIGHTEENTH));
            tour.setDays(rs.getInt(Number.NINETEENTH));
            tour.setPrice(rs.getInt(Number.TWENTIETH));
            hotel.setName(rs.getString(Number.TWENTY_FIRST));
            hotel.setDescription(rs.getString(Number.TWENTY_SECOND));
            hotel.setStarsNumber(rs.getInt(Number.TWENTY_THIRD));
            city.setName(rs.getString(Number.TWENTY_FOURTH));
            country.setName(rs.getString(Number.TWENTY_FIFTH));
            departureCity.setName(rs.getString(Number.TWENTY_SIXTH));
            money.setId(rs.getInt(Number.TWENTY_SEVENTH));
            flightTo.setId(rs.getInt(Number.TWENTY_NINTH));
            flightFrom.setId(rs.getInt(Number.THIRTIETH));

            planeTo.setDepartureCity(departureCity);
            city.setCountry(country);
            tour.setCity(city);
            tour.setHotel(hotel);
            voucher.setTour(tour);
            flightTo.setPlane(planeTo);
            flightFrom.setPlane(planeFrom);
            voucher.setFlightTo(flightTo);
            voucher.setFlightFrom(flightFrom);
            order.setClient(client);
            order.setVoucher(voucher);
            order.setMoney(money);
        }

        return order;
    }

}
