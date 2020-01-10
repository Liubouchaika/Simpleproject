package dao;

import domain.Account;
import domain.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO {
    User getUserById(long id) throws SQLException, ClassNotFoundException;
    ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException;
}
