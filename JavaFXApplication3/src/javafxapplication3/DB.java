package javafxapplication3;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author Wael
 */
public class DB {

    private static DB instance = null;
    private Connection con;
    private Statement stm;

    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // local db Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crypto", "root", "123456");
            stm = con.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS Users "
                    + "(UserID int primary key AUTO_INCREMENT, FullName varchar(250) NOT NULL, Email varchar(250) NOT NULL, Password varchar(50) NOT NULL, BirthDay date NOT NULL, Gender varchar(6) NOT NULL) ");

            stm.executeUpdate("CREATE TABLE IF NOT EXISTS Wallet (WalletID int primary key AUTO_INCREMENT, UserID int NOT NULL, Balance double NOT NULL, FOREIGN KEY (UserID) REFERENCES Users(UserID))");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Ensures Only One Instance created from this Class. So Only one DB connection is Established.
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }

        return instance;

    }

    public void addUser(String FullName, String Email, String Password, Date BirthDay, String Gender) throws SQLException {
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Users (FullName, Email, Password, BirthDay, Gender) values (?, ?, ?, ?, ?)");
        pstm.setString(1, FullName);
        pstm.setString(2, Email);
        pstm.setString(3, Password);
        // Need to check the following line later and make sure the date is added.
        pstm.setObject(4, (Date) BirthDay);
        pstm.setString(5, Gender);
    }

    public void addWallet(Double Balance) throws SQLException {
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Wallet (Balance) values (?)");
        pstm.setDouble(1, Balance);
    }

    // Optional for testing purposes, Will Delete It later
    public static void main(String[] args) {
        instance.getInstance();
    }
}
