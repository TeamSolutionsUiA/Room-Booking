/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lasse
 */
public class BoenhetDAO {
    private Connection conn;
    private BoenhetDAO boenhetDAO;
    
    private void insert (Boenhet boenhet) {
       DbTool dbTool = new DbTool();
       conn = dbTool.loggInn(); 
       try {
           String sql = "Insert into Boenhet (boenhetsnummer, BoenhetsTypeID)" + "VALUES (?,?)";
           PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           statement.setInt(1, boenhet.getBoenhetsnummer());
           statement.setInt(2, boenhet.getBoenhetstypeID());
           
            int rowsInserted = statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
           
       }
       catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}