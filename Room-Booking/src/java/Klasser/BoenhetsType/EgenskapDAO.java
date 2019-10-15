/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import java.io.PrintWriter;
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

    private List<Egenskap> egenskaper;

    public void insert(Connection conn, Egenskap egenskap, int id) {
        if (!exists(conn, egenskap)) {
            insertNy(conn, egenskap);
        }
        insertLink(conn, egenskap, id);
    }
    
    private void insertNy(Connection conn, Egenskap egenskap) {
        try {
            String sql = "INSERT INTO egenskap (Egenskap)" + "VALUES(?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, egenskap.getEgenskap());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertLink(Connection conn, Egenskap egenskap, int id) {
        try {
            String sql = "INSERT INTO egenskaplink (Egenskap, BoenhetsType_ID)" + "VALUES(?, ?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, egenskap.getEgenskap());
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean exists(Connection conn, Egenskap egenskap) {
        boolean exsists = false;

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM egenskap WHERE Egenskap = ?");
            stm.setString(1, egenskap.getEgenskap());
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
    
    public List<Egenskap> readAll(Connection conn, int boenhetsTypeID) {
        try {
            String query = "SELECT Egenskap FROM egenskaplink WHERE BoenhetsType_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, boenhetsTypeID);

            ResultSet rs = stm.executeQuery();
            egenskaper = new ArrayList();
            while (rs.next()) {
                egenskaper.add(new Egenskap(rs.getString("Egenskap")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return egenskaper;
    }

    public void delete(Connection conn, int LelighetsID) {
        try {
            String query = "DELETE FROM Egenskap WHERE Leilighet_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, LelighetsID);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}