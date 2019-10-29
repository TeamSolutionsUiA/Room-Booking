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
        egenskaper = new ArrayList();
        try {
            String query = "SELECT Egenskap FROM egenskaplink WHERE BoenhetsType_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, boenhetsTypeID);

            ResultSet rs = stm.executeQuery();

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

    public void delete(Connection conn, int id) {
        List<Egenskap> liste = readAll(conn, id);

        for (Egenskap egenskap : liste) {
            deleteLink(conn, egenskap, id);
            if (!iBruk(conn, egenskap)) {
                deleteegenskap(conn, egenskap);
            }
        }

    }

    private void deleteLink(Connection conn, Egenskap egenskap, int id) {
        try {
            String query = "DELETE FROM Egenskaplink WHERE boenhetstype_ID = ? AND Egenskap = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.setString(2, egenskap.getEgenskap());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteegenskap(Connection conn, Egenskap egenskap) {
        try {
            String sql = "DELETE FROM Egenskap WHERE Egenskap = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, egenskap.getEgenskap());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean iBruk(Connection conn, Egenskap Egenskap) {
        boolean iBruk = false;

        try {
            String query = "SELECT Egenskap FROM Egenskaplink WHERE Egenskap = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, Egenskap.getEgenskap());
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                iBruk = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iBruk;
    }

    public void update(Connection conn, List<Egenskap> egenskapN, List<Egenskap> egenskapG, int ID) {

        boolean fjernet = true;
        for (Egenskap skjekkegenskapG : egenskapG) {
            fjernet = true;
            for (Egenskap skjekkegenskapN : egenskapN) {
                if (skjekkegenskapN.getEgenskap().equals(skjekkegenskapG.getEgenskap())) {
                    fjernet = false;
                }
            }
            if (fjernet) {
                deleteLink(conn, skjekkegenskapG, ID);

                if (!iBruk(conn, skjekkegenskapG)) {
                    deleteegenskap(conn, skjekkegenskapG);
                }
            }
        }

        boolean lagtTil = true;
        for (Egenskap skjekkegenskapN : egenskapN) {
            lagtTil = true;
            for (Egenskap skjekkegenskapG : egenskapG) {
                if (skjekkegenskapN.getEgenskap().equals(skjekkegenskapG.getEgenskap())) {
                    lagtTil = false;
                }
            }
            if (lagtTil) {
                insert(conn, skjekkegenskapN, ID);
            }
        }
    }
}
