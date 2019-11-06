/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import Klasser.Bruker.Bruker;
import Klasser.DbTool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author altee
 */
public class BestillingDAO {
    private Connection conn ;
    private Bestilling bestilling;
    private Bruker bruker ;
    
    
    public int insert(Bestilling bestilling) {
        
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
    
         try {
              String sql = "INSERT INTO Bestilling (StartDato, SluttDato , Bruker_ID , antallpers)"
                    + "VALUES (?, ?, ?, ?)";

              PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              
              
                     statement.setString(1, bestilling.getStartDato());
                     statement.setString(2, bestilling.getSluttDato());
                     statement.setInt   (3, bestilling.getBrukerID());
                     statement.setInt   (4, bestilling.getAntallPerson());
                     
                  
            
                    int rowsInserted = statement.executeUpdate();
                    ResultSet idRs = statement.getGeneratedKeys();
                     if (idRs.next()) {
                    int BestillingsNummer= idRs.getInt(1);
            
                   return BestillingsNummer;
             }
                     
    
    
 
            
          } catch (SQLException e) {
            e.printStackTrace();
          } catch (Exception e) {
            e.printStackTrace();
          }
        return 0;
        
    }

      public int insertBruker (Bruker bruker) {
        
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
    
         try {
              String sql = "INSERT INTO Bruker (Fornavn, EtterNavn , Epost , DOB )"
                    + "VALUES (?, ?, ?, ?)";

              PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              
              
                     statement.setString(1, bruker.getFornavn());
                     statement.setString(2, bruker.getEtternavn());
                     statement.setString   (3, bruker.getEpost());
                     statement.setString   (4, bruker.getFodselsDato());
                     
                  
            
                    int rowsInserted = statement.executeUpdate();
                    ResultSet idRs = statement.getGeneratedKeys();
                     if (idRs.next()) {
                    int Id= idRs.getInt(1);
            
                   return Id;
             }
    
    

            
          } catch (SQLException e) {
            e.printStackTrace();
          } catch (Exception e) {
            e.printStackTrace();
          }
        return 0;
        
    }
}


    

  
    

