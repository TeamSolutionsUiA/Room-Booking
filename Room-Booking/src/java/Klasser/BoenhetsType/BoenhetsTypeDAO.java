/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import Klasser.DbTool;
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
    private KategoriDAO kategoriDAO;
    private BoenhetsType boenhetsType;

    public int insert(BoenhetsType boenhetsType) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            String sql = "INSERT INTO boenhetstype (Navn, Enkeltsenger, Dobeltsenger, Beskrivelse, Pris) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, boenhetsType.getNavn());
            statement.setInt(2, boenhetsType.getEnkeltsenger());
            statement.setInt(3, boenhetsType.getDobeltsenger());
            statement.setString(4, boenhetsType.getBeskrivelse());
            statement.setInt(5, boenhetsType.getPris());

            statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
            if (idRs.next()) {
                int id = idRs.getInt(1);

                kategoriDAO = new KategoriDAO();
                kategoriDAO.insert(conn, boenhetsType.getKategori(), id);

                if (!boenhetsType.getBilder().isEmpty()) {
                    bildeDAO = new BildeDAO();
                    for (Bilde bilde : boenhetsType.getBilder()) {
                        bildeDAO.insert(conn, bilde, id);
                    }
                }

                if (!boenhetsType.getEgenskaper().isEmpty()) {
                    egenskapDAO = new EgenskapDAO();
                    for (Egenskap egenskap : boenhetsType.getEgenskaper()) {
                        egenskapDAO.insert(conn, egenskap, id);
                    }
                }
                return id;
            } else {
                throw new SQLException("Ingen ID returnert");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<BoenhetsType> readAll() {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            Statement stm = conn.createStatement();
            String query = "SELECT * From boenhetstype";
            ResultSet rs = stm.executeQuery(query);
            List<BoenhetsType> boenhetsTyper = new ArrayList<>();
            kategoriDAO = new KategoriDAO();
            egenskapDAO = new EgenskapDAO();
            bildeDAO = new BildeDAO();
            while (rs.next()) {
                boenhetsType = new BoenhetsType(rs.getInt("ID"), rs.getString("Navn"), kategoriDAO.read(conn, rs.getInt("ID")),
                        rs.getInt("EnkeltSenger"), rs.getInt("DobeltSenger"),
                        rs.getString("Beskrivelse"), rs.getInt("Pris"),
                        bildeDAO.readAll(conn, rs.getInt("ID")),
                        egenskapDAO.readAll(conn, rs.getInt("ID")));
                boenhetsTyper.add(boenhetsType);
            }
            return boenhetsTyper;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<BoenhetsType> readAll(String sql ) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        String query = sql;
        List<BoenhetsType> boenhetsTyper = new ArrayList<>();
        List<BoenhetsType> filter = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            kategoriDAO = new KategoriDAO();
            egenskapDAO = new EgenskapDAO();
            bildeDAO = new BildeDAO();
            
            while (rs.next()) {
               boolean eksisterer = false;
                boenhetsType = new BoenhetsType(rs.getInt("ID"), rs.getString("Navn"), kategoriDAO.read(conn, rs.getInt("ID")),
                        rs.getInt("EnkeltSenger"), rs.getInt("DobeltSenger"),
                        rs.getString("Beskrivelse"), rs.getInt("Pris"),
                        bildeDAO.readAll(conn, rs.getInt("ID")),
                        egenskapDAO.readAll(conn, rs.getInt("ID")));
                
                for (BoenhetsType boenhettype: boenhetsTyper) {
                   eksisterer = false;
                   for(BoenhetsType boenhettypef: filter) {
                     if(boenhettype ==boenhettypef) {
                         eksisterer =true;
                     }
                    }    
                  if (eksisterer !=true) {
                      boenhetsTyper.add(boenhetsType);
                } 
                
                }
                
                
                
            }
            if(!boenhetsTyper.isEmpty()){
                    return boenhetsTyper;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BoenhetsType read(int id) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            String query = "SELECT * FROM boenhetstype WHERE ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            egenskapDAO = new EgenskapDAO();
            bildeDAO = new BildeDAO();
            kategoriDAO = new KategoriDAO();

            rs.next();

            boenhetsType = new BoenhetsType(id, rs.getString("Navn"), kategoriDAO.read(conn, id),
                    rs.getInt("Enkeltsenger"), rs.getInt("Dobeltsenger"),
                    rs.getString("Beskrivelse"), rs.getInt("Pris"),
                    bildeDAO.readAll(conn, id),
                    egenskapDAO.readAll(conn, id));

            return boenhetsType;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void update(BoenhetsType boenhetsType) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        egenskapDAO = new EgenskapDAO();
        bildeDAO = new BildeDAO();
        kategoriDAO = new KategoriDAO();

        List<Egenskap> egenskaperN = boenhetsType.getEgenskaper();
        Kategori kategoriN = boenhetsType.getKategori();
        try {
            String sql = "UPDATE boenhetstype SET Navn=?, Enkeltsenger=?, Dobeltsenger=?, Beskrivelse=?, Pris=? WHERE ID=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, boenhetsType.getNavn());
            statement.setInt(2, boenhetsType.getEnkeltsenger());
            statement.setInt(3, boenhetsType.getDobeltsenger());
            statement.setString(4, boenhetsType.getBeskrivelse());
            statement.setInt(5, boenhetsType.getPris());
            statement.setInt(6, boenhetsType.getID());

            statement.executeUpdate();
            
            Kategori kategoriG = kategoriDAO.read(conn, boenhetsType.getID());
            List<Egenskap> egenskaperG = egenskapDAO.readAll(conn, boenhetsType.getID());
            
            kategoriDAO.update(conn, kategoriN, kategoriG, boenhetsType);
            egenskapDAO.update(conn, egenskaperN, egenskaperG, boenhetsType.getID());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            egenskapDAO = new EgenskapDAO();
            bildeDAO = new BildeDAO();
            kategoriDAO = new KategoriDAO();
            
            egenskapDAO.delete(conn, id);
            bildeDAO.delete(conn, id);
            kategoriDAO.delete(conn, id);

            String query = "DELETE FROM boenhetstype WHERE ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
