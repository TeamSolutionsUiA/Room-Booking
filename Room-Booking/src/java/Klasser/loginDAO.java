/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;
import java.sql.*;


/**
 *
 * @author altee
 */
public class loginDAO {
   
 private Connection conn;
 
 public boolean check(String Epost, String passord) {
      try {
      DbTool dbTool = new DbTool();
       conn = dbTool.loggInn();
        String sql = "SELECT * FROM Bruker where Epost=? and Passord=?";
          PreparedStatement statement = conn.prepareStatement(sql);
          statement.setString(1, Epost);
           statement.setString(2, passord);
           ResultSet rs = statement.executeQuery();
           
           if (rs.next()){
          
           return true ;
           
           
           
           }
           
      } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
          
    return false;
 }
    
 
   
    
}
