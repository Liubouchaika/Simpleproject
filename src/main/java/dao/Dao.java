package dao;

import domain.Account;
import domain.User;

import java.sql.*;
import java.util.ArrayList;

public class Dao implements IDAO {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/main?useUnicode=true&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private static Connection connection = null;

    public Dao() {}

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        }

        return connection;
    }

    public User getUserById(long id) throws SQLException, ClassNotFoundException {
        User user = null;
        String template = "SELECT * FROM user WHERE userid = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = this.getConnection();

            statement = connection.prepareStatement(template);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                long userId = resultSet.getLong("userid");
                String firstName = resultSet.getString("name");
                String lastName = resultSet.getString("sureName");

                user = new User(userId, firstName, lastName);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return user;
    }
    public ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {

        ArrayList<Account> accounts = new ArrayList<Account>();
        String sql = "SELECT * FROM account";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = this.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                long userId = resultSet.getLong("userid");
                long accountId = resultSet.getLong("accountid");
                long account = resultSet.getLong("account");

                Account a = new Account(accountId, account, userId);

                accounts.add(a);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return accounts;
    }
}
