package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.*;
import service.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDao extends DAO<Order, Integer> {

    private static final Query CREATE = new Query("INSERT INTO ORDERS (CLIENT_ID, VOUCHER_ID, LOGIN_ID, MONEY_ID) VALUES (?,?,?,?)");
    private static final Query READ_ALL_BY_ACCOUNT_ID = new Query("SELECT\n" +
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
            "WHERE ACCOUNT.LOGIN = ?");

    private static final Query READ_ALL = new Query("SELECT\n" +
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
            "  INNER JOIN COUNTRIES ON CITIES.COUNTRY_ID = COUNTRIES.COUNTRY_ID");

    private static final Query READ = new Query("SELECT\n" +
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
            "WHERE ORDERS.ORDER_ID = ?");

    private static final Query DELETE = new Query("DELETE FROM orders WHERE order_id = ?");

    @Override
    public Integer create(Order entity) {

        Connection connection = getConnection();
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Num.FIRST.getNum(), entity.getClient().getId());
            ps.setInt(Num.SECOND.getNum(), entity.getVoucher().getId());
            ps.setString(Num.THIRD.getNum(), entity.getAccount().getLogin());
            ps.setInt(Num.FOURTH.getNum(), entity.getMoney().getId());
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
    public Order read(Integer id) {

        Connection connection = getConnection();
        Order order = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), id);
            rs = ps.executeQuery();
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

                order.setId(rs.getInt(Num.FIRST.getNum()));
                client.setId(rs.getInt(Num.SECOND.getNum()));
                client.setFullName(rs.getString(Num.THIRD.getNum()));
                client.setPaper(rs.getString(Num.FOURTH.getNum()));
                client.setDocumentNum(rs.getInt(Num.FIFTH.getNum()));
                client.setPhone(rs.getString(Num.SIXTH.getNum()));
                client.setBirthday(rs.getDate(Num.SEVENTH.getNum()));
                client.setEmail(rs.getString(Num.EIGHTH.getNum()));
                voucher.setId(rs.getInt(Num.NINTH.getNum()));
                voucher.setClientNumber(rs.getInt(Num.TENTH.getNum()));
                voucher.setPrice(rs.getInt(Num.ELEVENTH.getNum()));
                flightTo.setDate(rs.getDate(Num.TWELFTH.getNum()));
                planeTo.setName(rs.getString(Num.THIRTEENTH.getNum()));
                planeTo.setDepartureTime(rs.getTime(Num.FOURTEENTH.getNum()));
                flightFrom.setDate(rs.getDate(Num.FIFTEENTH.getNum()));
                planeFrom.setName(rs.getString(Num.SIXTEENTH.getNum()));
                planeFrom.setDepartureTime(rs.getTime(Num.SEVENTEENTH.getNum()));
                tour.setId(rs.getInt(Num.TWENTY_EIGHTH.getNum()));
                tour.setDescription(rs.getString(Num.EIGHTEENTH.getNum()));
                tour.setDays(rs.getInt(Num.NINETEENTH.getNum()));
                tour.setPrice(rs.getInt(Num.TWENTIETH.getNum()));
                hotel.setName(rs.getString(Num.TWENTY_FIRST.getNum()));
                hotel.setDescription(rs.getString(Num.TWENTY_SECOND.getNum()));
                hotel.setStarsNumber(rs.getInt(Num.TWENTY_THIRD.getNum()));
                city.setName(rs.getString(Num.TWENTY_FOURTH.getNum()));
                country.setName(rs.getString(Num.TWENTY_FIFTH.getNum()));
                departureCity.setName(rs.getString(Num.TWENTY_SIXTH.getNum()));
                money.setId(rs.getInt(Num.TWENTY_SEVENTH.getNum()));
                flightTo.setId(rs.getInt(Num.TWENTY_NINTH.getNum()));
                flightFrom.setId(rs.getInt(Num.THIRTIETH.getNum()));

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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return order;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {

        Connection connection = getConnection();

        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public List<Order> readAll() {

        Connection connection = getConnection();
        List<Order> orders = new ArrayList<>();
        ResultSet rs = null;
        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(READ_ALL.getQuery());
            setOrder(orders, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }

        return orders;
    }


    public List<Order> readAllByAccount(Account account) {

        Connection connection = getConnection();
        List<Order> orders = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_ACCOUNT_ID.getQuery())) {
            ps.setString(Num.FIRST.getNum(), account.getLogin());
            rs = ps.executeQuery();
            setOrder(orders, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }

        return orders;
    }

    private void setOrder(List<Order> orders, ResultSet rs) throws SQLException {
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

            order.setId(rs.getInt(Num.FIRST.getNum()));
            client.setId(rs.getInt(Num.SECOND.getNum()));
            client.setFullName(rs.getString(Num.THIRD.getNum()));
            client.setPaper(rs.getString(Num.FOURTH.getNum()));
            client.setDocumentNum(rs.getInt(Num.FIFTH.getNum()));
            client.setPhone(rs.getString(Num.SIXTH.getNum()));
            client.setBirthday(rs.getDate(Num.SEVENTH.getNum()));
            client.setEmail(rs.getString(Num.EIGHTH.getNum()));
            voucher.setId(rs.getInt(Num.NINTH.getNum()));
            voucher.setClientNumber(rs.getInt(Num.TENTH.getNum()));
            voucher.setPrice(rs.getInt(Num.ELEVENTH.getNum()));
            flightTo.setDate(rs.getDate(Num.TWELFTH.getNum()));
            planeTo.setName(rs.getString(Num.THIRTEENTH.getNum()));
            planeTo.setDepartureTime(rs.getTime(Num.FOURTEENTH.getNum()));
            flightFrom.setDate(rs.getDate(Num.FIFTEENTH.getNum()));
            planeFrom.setName(rs.getString(Num.SIXTEENTH.getNum()));
            planeFrom.setDepartureTime(rs.getTime(Num.SEVENTEENTH.getNum()));
            tour.setId(rs.getInt(Num.TWENTY_EIGHTH.getNum()));
            tour.setDescription(rs.getString(Num.EIGHTEENTH.getNum()));
            tour.setDays(rs.getInt(Num.NINETEENTH.getNum()));
            tour.setPrice(rs.getInt(Num.TWENTIETH.getNum()));
            hotel.setName(rs.getString(Num.TWENTY_FIRST.getNum()));
            hotel.setDescription(rs.getString(Num.TWENTY_SECOND.getNum()));
            hotel.setStarsNumber(rs.getInt(Num.TWENTY_THIRD.getNum()));
            city.setName(rs.getString(Num.TWENTY_FOURTH.getNum()));
            country.setName(rs.getString(Num.TWENTY_FIFTH.getNum()));
            departureCity.setName(rs.getString(Num.TWENTY_SIXTH.getNum()));
            money.setId(rs.getInt(Num.TWENTY_SEVENTH.getNum()));

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
    }

}
