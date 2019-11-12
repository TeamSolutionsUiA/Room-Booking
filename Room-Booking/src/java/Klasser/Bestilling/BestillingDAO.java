/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import Klasser.Boenhet.Boenhet;
import Klasser.Bruker.Bruker;
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

    private Connection conn;
    private Bestilling bestilling;
   

    public void insert (Bestilling bestilling, int brukerID) {
        List <Boenhet> boenheter = bestilling.getBoenhet();
        
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        int bestillingsnummer =insertbestilling(bestilling, conn, brukerID);
      
        
        for (Boenhet boenhet: boenheter) {
            insertlinje(boenhet,conn, bestillingsnummer);
        }
    }
    
    
    private void insertlinje(Boenhet boenhet, Connection conn, int bestillingsNummer) {
        try{
        String sql = "INSERT INTO BestillingsLinje (BoenhetsNummer, BestillingsNummer)" + "VALUES(?, ?)";
        PreparedStatement statement;
        statement = conn.prepareStatement(sql);
        statement.setString(1, boenhet.getBoenhetsnummer());
        statement.setInt(2, bestillingsNummer);
        statement.executeUpdate();
             
            
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private int insertbestilling(Bestilling bestilling, Connection conn, int brukerID) {

       
        try {
            String sql = "INSERT INTO Bestilling (StartDato, SluttDato , Bruker_ID)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, bestilling.getStartDato());
            statement.setString(2, bestilling.getSluttDato());
            statement.setInt(3, brukerID);
   

            int rowsInserted = statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
            if (idRs.next()) {
                int BestillingsNummer = idRs.getInt(1);

                return BestillingsNummer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    
}
