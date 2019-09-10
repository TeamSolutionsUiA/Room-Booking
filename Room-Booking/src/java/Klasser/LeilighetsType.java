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
    
    public void Insert(PrintWriter out, String Navn, String Kategori, String Enkeltsenger, String Dobeltsenger, String Beskrivelse, String Pris){
        out.println("Are er awsome!");
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn(out);
        try{
            DatabaseMetaData dbm = conn.getMetaData();
        
            
            
            
            String sql = "INSERT INTO LeilighetsType (Navn, Kategori, Enkeltsenger, Dobeltsenger, Beskrivelse, Pris) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, Navn);
            statement.setString(2, Kategori);
            statement.setString(3, Enkeltsenger);
            statement.setString(4, Dobeltsenger);
            statement.setString(5, Beskrivelse);
            statement.setString(6, Pris);
 
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e){
            e.printStackTrace();
            out.println("Are er ikke awsome");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void CreateTable(PrintWriter out, Connection conn){
        try {
        
        //stmt = conn.createStatement();
        String sql = "create table LeilighetsType(ID int AUTO_INCREMENT=10000, Navn varchar(30), Kategori varchar(20),"
                + " Enkeltsenger int,Dobeltsenger int, Beskrivelse varchar(150), Pris int, PublisertStatus bool,"
                + "constraint PK_LeilighetsType primary key (ID));";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();
        out.println("Are er awsome!!!!!!!!!!");
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
