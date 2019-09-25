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
            String sql = "INSERT INTO Bruker (ID, Rolle, Navn, Fodselsdato, Epost, Passord, Telefon)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            Date fodselsDato = bruker.getFodselsDato();
            SimpleDateFormat formatter = new SimpleDateFormat();
            String mysqlDateString = formatter.format(fodselsDato);

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bruker.getId());
            statement.setString(2, bruker.getRolle());
            statement.setString(3, bruker.getNavn());
            statement.setString(4, mysqlDateString);
            statement.setString(5, bruker.getEpost());
            statement.setString(6, bruker.getPassord());
            statement.setInt(7, bruker.getTelefon());

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