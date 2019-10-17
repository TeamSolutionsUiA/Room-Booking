/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import Klasser.DbTool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Are
 */
public class KategoriDAO {

    private Kategori kategori;
    private Connection conn;

    public void insert(Connection conn, Kategori kategori, int id) {
        if (!exists(conn, kategori)) {
            insertNy(conn, kategori);
        }
        insertLink(conn, kategori, id);
    }

    private void insertNy(Connection conn, Kategori kategori) {
        try {
            String sql = "INSERT INTO kategori (Kategori)" + "VALUES(?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, kategori.getKategori());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertLink(Connection conn, Kategori kategori, int id) {
        try {
            String sql = "INSERT INTO kategorilink (Kategori, BoenhetsType_ID)" + "VALUES(?, ?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, kategori.getKategori());
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean exists(Connection conn, Kategori kategori) {
        boolean exsists = false;

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM kategori WHERE Kategori = ?");
            stm.setString(1, kategori.getKategori());
            ResultSet r1 = stm.executeQuery();
            if (r1.next()) {
                exsists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exsists;
    }
    
    public Kategori read(Connection conn, int LelighetsID){
        try {
            String query = "SELECT Kategori FROM kategorilink WHERE BoenhetsType_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, LelighetsID);

            ResultSet rs = stm.executeQuery();
            rs.next();
            kategori = new Kategori(rs.getString("Kategori"));
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kategori;
    }
    
    public List<String> readAll() {

        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {
            Statement stm = conn.createStatement();
            String query = "SELECT Kategori FROM kategori";
            ResultSet rs = stm.executeQuery(query);
            List<String> kategoriList = new ArrayList<>();

            while (rs.next()) {
                kategoriList.add(rs.getString("Kategori"));
            }
            return kategoriList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void delete(Connection conn, int LelighetsID) {
        kategori =read(conn, LelighetsID);
        try {
            String query = "DELETE FROM Kategorilink WHERE boenhetstype_ID = ? AND Katagori = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, LelighetsID);
            stm.setString(2, kategori.getKategori());
            stm.executeQuery(query);
            iBruk(conn, kategori.getKategori());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void iBruk(Connection conn, String katagori) {
           
           try {
           String query = "SELECT FROM Kategorilink WHERE Katagori = ?";
           PreparedStatement stm = conn.prepareStatement(query);
           stm.setString(1, kategori.getKategori());
           ResultSet rs = stm.executeQuery();
            
            if(rs.next()==false) {
             query = "DELETE FROM KATEGORI WHERE Katagori = ?";   
             stm.setString(1, kategori.getKategori());
             stm.executeQuery(query);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
