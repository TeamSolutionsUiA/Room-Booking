/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

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
    private BildeDAO bildeDAO;
    private EgenskapDAO egenskapDAO;
    private BoenhetsType boenhetsType; 
    
    public void Insert(BoenhetsType boenhetsType){
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        
        try{
            String sql = "INSERT INTO LeilighetsType (Navn, Kategori, Enkeltsenger, Dobeltsenger, Beskrivelse, Pris) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, boenhetsType.getNavn());
            statement.setString(2, boenhetsType.getKategori());
            statement.setInt(3, boenhetsType.getEnkeltsenger());
            statement.setInt(4, boenhetsType.getDobeltsenger());
            statement.setString(5, boenhetsType.getBeskrivelse());
            statement.setInt(6, boenhetsType.getPris());
            
            int rowsInserted = statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
            if (idRs.next()){
                int id = idRs.getInt(1);
            
                egenskapDAO = new EgenskapDAO();
                for (Egenskap egenskap : boenhetsType.getEgenskaper()){
                    egenskapDAO.Insert(conn, egenskap, id);
                }
            
                bildeDAO = new BildeDAO();
                for (Bilde bilde : boenhetsType.getBilder()){
                    bildeDAO.Insert(conn, bilde, id);
                }
            } else {
                throw new SQLException("Ingen ID returnert");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
   public List<BoenhetsType> readAll (PrintWriter out) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        
        try {
            Statement stm =conn.createStatement();
            String query ="SELECT * From LeilighetsType";
            ResultSet rs = stm.executeQuery(query);
            List<BoenhetsType> boenhetsTyper = new ArrayList<BoenhetsType>();
            egenskapDAO = new EgenskapDAO();
            bildeDAO = new BildeDAO();
            while (rs.next()) {
                boenhetsType = new BoenhetsType(rs.getString("Navn"), rs.getString("Kategori"),
                    rs.getInt("EnkeltSenger"), rs.getInt("DobeltSenger"),
                    rs.getString("Beskrivelse"), rs.getInt("Pris"),
                    egenskapDAO.readAll(conn,rs.getInt("ID")), 
                    bildeDAO.readAll(conn, rs.getInt("ID")));
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
        conn = dbTool.loggInn();
        
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
    public List<BoenhetsType> read(String id, PrintWriter out){
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
                egenskapDAO.readAll(conn,rs.getInt("Leilighet_ID")), 
                bildeDAO.readAll(conn, rs.getInt("Leilighet_ID")));      

        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
        
    }
    //private void read(){}
}
