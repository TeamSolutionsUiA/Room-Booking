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
    
    public int insert(Bruker bruker) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

           try {
           
                String sql = "INSERT INTO Bruker (Rolle, Navn, DOB, Epost, Passord, Telefon)"
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
    public List<Bruker> readAll() {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            Statement stm = conn.createStatement();
            String query = "SELECT * From Bruker";
            ResultSet rs = stm.executeQuery(query);
            List<Bruker> brukere = new ArrayList<Bruker>();
            
            while (rs.next()) {
                bruker = new Bruker(rs.getInt("ID"), rs.getString("Navn"), rs.getString("Fodsels_Dato"),
                        rs.getString("Epost"),rs.getInt("Telefon"));
                        
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
           bruker = new Bruker(rs.getInt("ID"), rs.getString("Navn"), rs.getString("DOB"),
                    rs.getString("Epost"), rs.getInt("Telefon"));
                    

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
    
    public void update(Bruker bruker) {
        DbTool dbTool = new DbTool();
        conn=dbTool.loggInn();
        try {
            String sql = "update bruker set Navn=? , DOB=? , Epost=? , Telefon=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, bruker.getNavn());
            statement.setString(2, bruker.getFodselsDato());
            statement.setString(3, bruker.getEpost());
            statement.setInt(4, bruker.getTelefon());
            
            int rowsInserted = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
       
