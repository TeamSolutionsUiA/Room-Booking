/*
 * Hensikten med klassen er etablere kontakt med databasen
 */
package Klasser;


import java.io.PrintWriter;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;


/**
 *
 * @author hallgeir
 */
public class DbTool {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;

    /*
        Vil etablere kontakt med databsen og returner et Connection objekt. 
    */
    public Connection loggInn(PrintWriter out) {
        try {
        // Step 1: Allocate a database 'Connection' object
        Context cont = new InitialContext();
         out.println(cont);
        DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
        out.println(ds);
        conn = ds.getConnection();
        out.println(conn);
        return conn; 
 
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null; 
    }  // end loggInn
}
