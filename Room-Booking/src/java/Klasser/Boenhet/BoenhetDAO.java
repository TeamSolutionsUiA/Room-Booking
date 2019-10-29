/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Boenhet;

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
 * @author lasse
 */
public class BoenhetDAO {

    private Connection conn;
    private Boenhet boenhet;

    public void insert(Boenhet boenhet) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        try {
            String sql = "Insert into Boenhet (BoenhetsNummer, BoenhetsType_ID)" + "VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, boenhet.getBoenhetsnummer());
            statement.setInt(2, boenhet.getBoenhetstypeID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Boenhet> readAll(int boenhetsTypeid) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();

        try {

            String query = "SELECT * FROM Boenhet WHERE BoenhetsType_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, boenhetsTypeid);
            ResultSet rs = stm.executeQuery();
            List<Boenhet> boenheter = new ArrayList<Boenhet>();

            while (rs.next()) {
                boenhet = new Boenhet(rs.getString("BoenhetsNummer"), rs.getInt("BoenhetsType_ID"));

                boenheter.add(boenhet);
            }
            return boenheter;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Connection conn, int Boenhetsnummer) {

        try {
            String query = "DELETE FROM Boenhet WHERE Boenhetsnummer = ? ";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, Boenhetsnummer);

            stm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Connection conn, Boenhet boenhet) {

        try {
            String sql = "UPDATE boenhetstype SET Boenhetsnummer=?, Boenhetstype_ID=?,";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, boenhet.getBoenhetsnummer());
            statement.setInt(2, boenhet.getBoenhetstypeID());

            int rowsInserted = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
