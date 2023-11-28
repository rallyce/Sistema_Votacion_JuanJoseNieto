
package classes;

import java.sql.*;


public class get_connection {
    
    public static Connection connect(){
        
        try {
            String url = "jdbc:mysql://mysql-superdb.alwaysdata.net/superdb_test_1";
            String user = "superdb";
            String pass = "fetchingBd";
            
            Connection cn = DriverManager.getConnection(url, user, pass);
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Database Error! " + e);
        }
        return (null);
    }
    
}
