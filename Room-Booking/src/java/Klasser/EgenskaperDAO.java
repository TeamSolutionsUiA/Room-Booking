/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author arefj
 */
public class EgenskaperDAO {
    public void Insert(Connection conn, Egenskaper egenskaper, int id){
        String[] egenskaperArr;
        egenskaperArr = egenskaper.getEgenskaper().split(",");
        try {
            for (String egenskap : egenskaperArr){
                egenskap = egenskap.trim();
                
                String sql= "INSERT INTO Egenskap (Egenskap, Leilighet_ID)" +"VALUES(?, ?)";
                PreparedStatement statement;
                statement = conn.prepareStatement(sql);
                statement.setString (1, egenskap);
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
