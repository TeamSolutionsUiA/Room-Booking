/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;
import java.sql.*;
import Klasser.Bruker.Bruker;


/**
 *
 * @author altee
 */
public class loginDAO {
   
 private Connection conn;
 
 
 
 
 public Bruker check(String Epost, String passord) {
      try {
        DbTool dbTool = new DbTool();
       conn = dbTool.loggInn();
        String sql = "SELECT * FROM Bruker where Epost=? and Passord=?";
          PreparedStatement statement = conn.prepareStatement(sql);
          statement.setString(1, Epost);
           statement.setString(2, passord);
           ResultSet rs = statement.executeQuery();
         Bruker bruker ;  
        if (rs.next()){
            bruker = new Bruker(); 
            
            bruker.setId(rs.getInt("id"));
            
            bruker.setRolle(rs.getString("Rolle"));
            
           bruker.setFornavn(rs.getString("fornavn"));
            
           bruker.setEtternavn(rs.getString("etternavn"));
            
           bruker.setFodselsDato(rs.getString("DOB"));
            
           bruker.setEpost(rs.getString("Epost"));
            
           bruker.setTelefon(rs.getString("Telefon"));
          
            
           
            

           return bruker;
           
           }
          
           
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
      
      return null ;
        
 
 }
    
}
