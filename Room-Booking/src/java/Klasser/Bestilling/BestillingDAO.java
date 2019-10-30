/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import Klasser.DbTool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author altee
 */
public class BestillingDAO {
    private List<Bestilling> bestillinger ;
    private Connection conn ;
    
    private Bestilling bestilling;
    public int insert(Bestilling bestilling) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
    
         try {
         String sql = "INSERT INTO Bestilling (startDato, sluttDato, antallPers)"
                    + "VALUES (?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement = conn.prepareStatement(sql);
            statement.setString(1, bestilling.getStartDato());
            statement.setString(2, bestilling.getSluttDato());
            statement.setInt (3, bestilling.getAntallPerson());
            
            
               int rowsInserted = statement.executeUpdate();
                ResultSet idRs = statement.getGeneratedKeys();
                if (idRs.next()) {
                int id = idRs.getInt(1);
            
                return id;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
    

