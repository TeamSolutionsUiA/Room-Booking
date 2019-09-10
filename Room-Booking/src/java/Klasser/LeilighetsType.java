/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.PrintWriter;
import java.sql.*;

/**
 *
 * @author Are
 */
public class LeilighetsType {
    Connection conn;
    Statement stmt;
    
    public void Insert(String navn, String kategori, String enkeltsenger, String dobeltsenger, String beskrivelse, String pris, String egenskaper){
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        try{
            String sql = "INSERT INTO LeilighetsType (Navn, Kategori, Enkeltsenger, Dobeltsenger, Beskrivelse, Pris) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, navn);
            statement.setString(2, kategori);
            statement.setString(3, enkeltsenger);
            statement.setString(4, dobeltsenger);
            statement.setString(5, beskrivelse);
            statement.setString(6, pris);
 
            int rowsInserted = statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
            if (idRs.next()){
            int id = idRs.getInt(1);
            InsertEgenskaper(conn, egenskaper, id);
            } else {
                throw new SQLException("Ingen ID returnert");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void InsertEgenskaper(Connection conn, String egenskaper, int id){
        String[] egenskaperArr;
        egenskaperArr = egenskaper.split(",");
        try {
            for (String egenskap : egenskaperArr){
                egenskap = egenskap.trim();
                
                String sql= "INSERT INTO Egenskap (Egenskap, Leilighet_ID)" +"VALUES(?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
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
