/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arefj
 */
public class EgenskapDAO {
    public void Insert(Connection conn, Egenskap egenskaper, int id){
        String[] egenskaperArr;
        egenskaperArr = egenskaper.getEgenskap().split(",");
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
    public List<Egenskap> readEgenskaper (Connection conn, String LelighetsID) {
        List<Egenskap> list = new ArrayList<Egenskap>();
       
       
      
        try {
            Statement stm =conn.createStatement();
            String query = "SELECT * FROM Egenskap where Leilighet_ID=" +LelighetsID;
            ResultSet rs = stm.executeQuery(query);
       
            list.add(new Egenskap(rs.getString(query)));
       }catch (Exception e){
           
           
       }
        return list;
    }
}


