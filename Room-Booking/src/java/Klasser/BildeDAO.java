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
    
    public List<Bilde> readAll(Connection conn, String LelighetsID){
        
     
        try {
            String query = "SELECT * FROM Bilde where Leilighet_ID=?";
            PreparedStatement stm =conn.prepareStatement(query);
            stm.setString(1,LelighetsID);
            ResultSet rs = stm.executeQuery();
       
            while(rs.next()){
                liste.add(new Bilde(rs.getBinaryStream("Bilde")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return liste;
        }
    }
    

