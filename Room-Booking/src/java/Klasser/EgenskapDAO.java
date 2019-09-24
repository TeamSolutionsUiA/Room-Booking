/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

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

    public void Insert(Connection conn, Egenskap egenskaper, int id) {
        String[] egenskaperArr;
        egenskaperArr = egenskaper.getEgenskap().split(",");
        try {
            for (String egenskap : egenskaperArr) {
                egenskap = egenskap.trim();

                String sql = "INSERT INTO Egenskap (Egenskap, Leilighet_ID)" + "VALUES(?, ?)";
                PreparedStatement statement;
                statement = conn.prepareStatement(sql);
                statement.setString(1, egenskap);
                statement.setInt(2, id);
                int rowsInserted = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Egenskap> readAll(Connection conn, int LelighetsID) {

        try {
            String query = "SELECT * FROM Egenskap WHERE Leilighet_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, LelighetsID);

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
}
