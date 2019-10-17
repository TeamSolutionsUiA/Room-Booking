/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import Klasser.DbTool;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sun.misc.IOUtils;

/**
 *
 * @author arefj
 */
public class BildeDAO {

    private List<Bilde> liste;
    private Connection conn;

    public void insert(Connection conn, Bilde bilde, int id) {
        InputStream nyttBilde = bilde.getBilde();
        String hash = MD5Hash(nyttBilde);

        if (!exists(conn, hash)) {
            insertNy(conn, hash, nyttBilde);
        }
        insertLink(conn, hash, id);
    }

    private void insertNy(Connection conn, String hash, InputStream bilde) {
        try {
            String sql = "INSERT INTO bilde (Bilde_hash, Bilde)" + "VALUES(?, ?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, hash);
            statement.setBlob(2, bilde);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertLink(Connection conn, String hash, int id) {
        try {
            String sql = "INSERT INTO bildelink (Bilde_hash, BoenhetsType_ID)" + "VALUES(?, ?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, hash);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean exists(Connection conn, String hash) {
        boolean exsists = false;

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM bilde WHERE Bilde_hash = ?");
            stm.setString(1, hash);
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

    private String MD5Hash(InputStream bilde) {
        try {
            byte[] bildeByte = IOUtils.readFully(bilde, -1, false);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestArr = md.digest(bildeByte);
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < digestArr.length; ++i) {
                buffer.append(Integer.toHexString((digestArr[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Bilde> readAll(Connection conn, int boenhetsTypeID) {

        liste = new ArrayList();

        try {
            String query = "SELECT bilde.Bilde, bilde.Bilde_hash FROM bilde INNER JOIN bildelink ON bilde.Bilde_hash = bildelink.Bilde_hash"
                    + " WHERE bildelink.Boenhetstype_ID = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, boenhetsTypeID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                liste.add(new Bilde(rs.getBinaryStream("Bilde"), rs.getString("Bilde_hash")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Bilde read(String hash) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        try {
            String query = "SELECT Bilde FROM bilde WHERE Bilde_hash=?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, hash);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Bilde bilde = new Bilde(rs.getBinaryStream("Bilde"));
                return bilde;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
public void delete(Connection conn, Bilde bilde, int id) {
        deleteLink(conn, bilde, id);
        if (!iBruk(conn, bilde)) {
            deleteKategori(conn, bilde);
        }

    }

    private void deleteLink(Connection conn, Bilde bilde, int id) {
        try {
            String query = "DELETE FROM bilde WHERE boenhetstype_ID = ? AND Bilde_hash = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.setString(2, (bilde.getHash()));
            stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteKategori(Connection conn, Bilde bilde) {
        try {
            String sql = "DELETE FROM bilde WHERE Bilde_hash = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, bilde.getHash());
            stm.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean iBruk(Connection conn, Bilde bilde) {
        boolean iBruk = false;

        try {
            String query = "SELECT FROM bildelink WHERE Bilde_hash = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, bilde.getHash());
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
}
