/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author altee
 */
public class BestillingDAO {
    private List<Bestilling> bestillinger ;
    
    
    
    public void insertNy ( Connection conn , Bestilling bestilling ) throws SQLException{
         try {
        String sql = "INSERT INTO bestilling (Bestilling)" + "VALUES(?)";
          
        PreparedStatement statement; 
            statement = conn.prepareStatement(sql);
            statement.setString(1, bestilling.getStartDato());
            statement.setString(2, bestilling.getSluttDato());
            statement.setInt (3, bestilling.getAntallPerson());
            
            statement.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    
    
    

