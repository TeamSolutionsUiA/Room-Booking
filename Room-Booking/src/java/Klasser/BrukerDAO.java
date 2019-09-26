/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author Jonathans
 */
public class BrukerDAO {
    
    private Connection conn;
    private Bruker bruker;
    
    public int insert(Bruker bruker) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        
            try {
           
                String sql = "INSERT INTO Bruker (Rolle, Navn, Fodsels_dato, Epost, Passord, Telefon)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                statement.setString(1, bruker.getRolle());
                statement.setString(2, bruker.getNavn());
                statement.setString(3, bruker.getFodselsDato());
                statement.setString(4, bruker.getEpost());
                statement.setString(5, bruker.getPassord());
                statement.setInt(6, bruker.getTelefon());

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