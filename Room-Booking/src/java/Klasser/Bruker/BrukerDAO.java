
  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bruker;

import Klasser.DbTool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jonathans
 */
public class BrukerDAO {
    
    private Connection conn;
    private Bruker bruker;
    
    public int insert(Bruker bruker, String sql) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

           try {

                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                statement.setString(1, bruker.getRolle());
                statement.setString(2, bruker.getFornavn());
                statement.setString(3, bruker.getEtternavn());
                statement.setString(4, bruker.getFodselsDato());
                statement.setString(5, bruker.getEpost());
                statement.setString(6, bruker.getTelefon());
                if(sql.contains("Passord")){
                statement.setString(7, bruker.getPassord());
                }
                
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
    public List<Bruker> readAll() {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            Statement stm = conn.createStatement();
            String query = "SELECT * From Bruker";
            ResultSet rs = stm.executeQuery(query);
            List<Bruker> brukere = new ArrayList<Bruker>();
            
            while (rs.next()) {
                bruker = new Bruker(rs.getInt("ID"), 
                        rs.getString("Fornavn"), rs.getString("Etternavn"), rs.getString("DOB"),
                        rs.getString("Epost"),rs.getString("Telefon"));
                        
                brukere.add(bruker);
            }
            return brukere;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}

    public Bruker read(int ID) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            String query = "SELECT * FROM Bruker WHERE ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();

            rs.next();
           bruker = new Bruker(rs.getInt("ID"),rs.getString("Fornavn"), rs.getString("Etternavn"), rs.getString("DOB"),
                    rs.getString("Epost"), rs.getString("Telefon"));
                    

            return bruker;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}
    
    public boolean sjekkBrukerEksist(String epost){

        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        boolean brukerEksist = false;
        
        try {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM Bruker WHERE Epost = ?");
        stm.setString(1, epost);
        ResultSet r1=stm.executeQuery();
            if(r1.next()) {
                brukerEksist = true;
            }
        }
            catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brukerEksist;
         }
    
    public int update(Bruker bruker) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        try {
            String sql = "UPDATE Bruker SET Fornavn=?, Etternavn=?, DOB=?, Epost=?, Passord=?, Telefon=? WHERE ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, bruker.getFornavn());
            statement.setString(2, bruker.getEtternavn());
            statement.setString(3, bruker.getFodselsDato());
            statement.setString(4, bruker.getEpost());
            statement.setString(5, bruker.getPassord());
            statement.setString(6, bruker.getTelefon());
            statement.setInt(7, bruker.getId());
            
             int rowsInserted = statement.executeUpdate();
            
                if (rowsInserted != 0) {
                    int id = bruker.getId();
            
                return id;
            
            } else {
                throw new SQLException("Ingen ID returnert");
            }
        }
          catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
       
    }
    public boolean delete(int brukerID) {
       
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        
        try {
            String query = "DELETE FROM Bruker WHERE ID = ? ";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, brukerID);
           
            statement.executeUpdate();
            
            Bruker brukerSjekk = read(brukerID);
            if(brukerSjekk == null) {
                return true;
            } else {
                
                throw new SQLException("Brukeren ble ikke slettet fra databasen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
       

