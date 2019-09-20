/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arefj
 */
public class BoenhetsTypeDAO {
    private Connection conn;
    private BildeDAO bilderDAO;
    private EgenskapDAO egenskapDAO;
    private BoenhetsType boenhetsType; 
    
    public void Insert(BoenhetsType boenhetsType, PrintWriter out){
        out.println("Test");
        DbTool dbTool = new DbTool();
        out.println("Test");
        conn = dbTool.loggInn(out);
        out.println(conn);
        try{
            String sql = "INSERT INTO LeilighetsType (Navn, Kategori, Enkeltsenger, Dobeltsenger, Beskrivelse, Pris) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            out.println("Test3");
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            out.println("Test4");
            statement.setString(1, boenhetsType.getNavn());
            statement.setString(2, boenhetsType.getKategori());
            statement.setInt(3, boenhetsType.getEnkeltsenger());
            statement.setInt(4, boenhetsType.getDobeltsenger());
            statement.setString(5, boenhetsType.getBeskrivelse());
            statement.setInt(6, boenhetsType.getPris());
            out.println("Test4");
            int rowsInserted = statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
            out.println("Test5");
            if (idRs.next()){
            int id = idRs.getInt(1);
            for (Egenskap egenskap : boenhetsType.getEgenskaper()){
                egenskapDAO.Insert(conn, egenskap, id);
            }
            out.println("Test6");
            for (Bilde bilde : boenhetsType.getBilder()){
                bilderDAO.Insert(conn, bilde, id);
            }
            } else {
                throw new SQLException("Ingen ID returnert");
            }
            out.println("Test");
        } catch (SQLException e){
            out.println("TestSQLEx");
            e.printStackTrace();
        } catch (Exception e){
            out.println("TestEx");
            out.println(e);
            e.printStackTrace();
        }
    }
   public List<BoenhetsType> readAll () {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn(out);
        
        try {
            Statement stm =conn.createStatement();
            String query ="SELECT * From LeilighetsType";
            ResultSet rs = stm.executeQuery(query);
            List<BoenhetsType> boenhetsTyper = new ArrayList<BoenhetsType>();
       
            while (rs.next()) {
                boenhetsType = new BoenhetsType(rs.getString("Navn"), rs.getString("Kategori"),
                    rs.getInt("EnkeltSenger"), rs.getInt("DobeltSenger"),
                    rs.getString("Beskrivelse"), rs.getInt("Pris"),
                    egenskapDAO.readAll(conn,rs.getString("Leilighet_ID")), 
                    bilderDAO.readAll(conn, rs.getString("Leilighet_ID")));
                boenhetsTyper.add(boenhetsType);   
            }
            return boenhetsTyper;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
   }
   
   public List<String> readAllKategorier(){
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn(out);
        
        try {
            Statement stm =conn.createStatement();
            String query ="SELECT DISTINCT Kategori From LeilighetsType";
            ResultSet rs = stm.executeQuery(query);
            List<String> kategori = new ArrayList<String>();
       
            while (rs.next()) {
                kategori.add(rs.getString("Kategori"));   
            }
            return kategori;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<BoenhetsType> readBoenhet(String id){
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        
        try {
            String query ="SELECT From LeilighetsType WHERE ID = id";
            PreparedStatement stm =conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery(query);
            //List<BoenhetsType> boenhetInfo = new ArrayList<BoenhetsType>();
       
            boenhetsType = new BoenhetsType(rs.getString("Navn"), rs.getString("Kategori"),
                rs.getInt("EnkeltSenger"), rs.getInt("DobeltSenger"),
                rs.getString("Beskrivelse"), rs.getInt("Pris"),
                egenskapDAO.readAll(conn,rs.getString("Leilighet_ID")), 
                bilderDAO.readAll(conn, rs.getString("Leilighet_ID")));      

        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
        
    }
    //private void read(){}
}
