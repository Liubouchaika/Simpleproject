import dao.Dao;
import domain.Account;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Playground {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        long sum = getAccountsSum();
        System.out.println("AccountsSum:"+ sum);

        User richestUser = getTheRichestUser();
        System.out.println(richestUser.getFirstName()+" "+ richestUser.getLastName());

    }

    private static long getAccountsSum() throws SQLException, ClassNotFoundException {
        long sum = 0;

        Dao dao = new Dao();
        ArrayList<Account> accounts = dao.getAllAccounts();
        for(int i=0; i < accounts.size(); i++) {
            sum = sum + accounts.get(i).getAccount();
        }
        return sum;
    }

    private static User getTheRichestUser() throws SQLException, ClassNotFoundException {
        User richestUser = null;

        Dao dao = new Dao();
        ArrayList<Account> accounts = dao.getAllAccounts();
        long richestUserAccount = 0;
        long richestUserId = 0;

        for ( int i = 0; i < accounts.size(); i++ ) {
             if (richestUserAccount < accounts.get(i).getAccount()) {
                 richestUserAccount = accounts.get(i).getAccount();
                 richestUserId = accounts.get(i).getUserId();
             }
        }

        richestUser = dao.getUserById(richestUserId);
        System.out.println("richestUserAccount: " + richestUserAccount);



        return richestUser;
    }
}
