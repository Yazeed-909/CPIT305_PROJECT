package javafxapplication3;

import com.mysql.cj.protocol.Resultset;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/sys", "root", "123456");
            stm = con.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS Users "
                    + "(UserID int primary key AUTO_INCREMENT, FullName varchar(250) NOT NULL, Email varchar(250) NOT NULL, Password varchar(50) NOT NULL, BirthDay date NOT NULL, Gender varchar(6) NOT NULL,Balance double NOT NULL) ");

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
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Users (FullName, Email, Password, BirthDay, Gender,Balance) values (?, ?, ?, ?, ?, ?)");
       
        pstm.setString(1, FullName);
        pstm.setString(2, Email);
        pstm.setString(3, Password);
        // Need to check the following line later and make sure the date is added.
        pstm.setObject(4, (Date) BirthDay);
        pstm.setString(5, Gender);
        pstm.setDouble(6, 0.0);
   
        
        pstm.execute();
    }

    
    public UserINFO retrieveUser(String email) throws SQLException {

        String query = "SELECT * FROM sys.users where Email=\""+email+"\";";

        ResultSet rs = stm.executeQuery(query);
        
        if(rs.next()){
            UserINFO info=new UserINFO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getDouble(7));
            return info;
        }
               
        return null;
  
        //update sys.users set balance=12 where userid=1;
    }
    
    public void UpdateBalance(double amount,int user_id){
        
         String query = "update sys.users set balance="+amount+" where userid="+user_id+";";

        try {
            int rs = stm.executeUpdate(query);
          
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    }
    
    
    
    // Optional for testing purposes, Will Delete It later
    public static void main(String[] args) throws SQLException {
        instance.getInstance();
        DB a=DB.getInstance();
     //  a.addUser("yazeed","yzeed@gmail.comn", "1111",new Date(), "");
        System.out.println( a.retrieveUser("yzeed@gmail.comn").toString());
        
    }
}
