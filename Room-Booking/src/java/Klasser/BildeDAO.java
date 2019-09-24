/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;


/**
 *
 * @author arefj
 */
public class BildeDAO {
    
    private List<Bilde> liste;
    private Connection conn;
        
    public void Insert(Connection conn, Bilde bilde, int id){
        try {
                InputStream nyttBilde = bilde.getBilde();
                String sql= "INSERT INTO Bilde (Bilde, Leilighet_ID)" +"VALUES(?, ?)";
                PreparedStatement statement;
                statement = conn.prepareStatement(sql);
                statement.setBlob(1, nyttBilde);
                statement.setInt(2, id);
                int rowsInserted = statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Bilde> readAll(Connection conn, int LelighetsID){
        
        liste = new ArrayList();
        
        try {
            String query = "SELECT * FROM Bilde WHERE Leilighet_ID = ?";
            PreparedStatement stm =conn.prepareStatement(query);
            stm.setInt(1,LelighetsID);
            ResultSet rs = stm.executeQuery();
       
            while(rs.next()){
                liste.add(new Bilde(rs.getBinaryStream("Bilde"), rs.getInt("ID")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return liste;
    }
    
    public Bilde read(int ID){
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        try {
            String query = "SELECT * FROM Bilde where ID=?";
            PreparedStatement stm =conn.prepareStatement(query);
            stm.setInt(1,ID);
            ResultSet rs = stm.executeQuery();
       
            rs.next();
            Bilde bilde = new Bilde(rs.getBinaryStream("Bilde"), rs.getInt("ID"));
            return bilde;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    }

    
    

