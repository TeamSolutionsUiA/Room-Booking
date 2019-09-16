/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author arefj
 */
public class BilderDAO {
    public void Insert(Connection conn, Bilder bilder, int id){
        try {
            for (InputStream bilde : bilder.getBilder()){
                
                String sql= "INSERT INTO Bilde (Bilde, Leilighet_ID)" +"VALUES(?, ?)";
                PreparedStatement statement;
                statement = conn.prepareStatement(sql);
                statement.setBlob(1, bilde);
                statement.setInt(2, id);
                int rowsInserted = statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
