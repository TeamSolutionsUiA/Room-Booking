/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import Klasser.DbTool;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arefj
 */
public class BildeDAO {

    private List<Bilde> liste;
    private Connection conn;

    public void insert(Connection conn, Bilde bilde, int id) {
        String hash = MD5Hash(bilde.getBilde());

        if (!exists(conn, hash)) {
            insertNy(conn, hash, bilde.getBilde());
        }
        insertLink(conn, hash, id);
    }

    private void insertNy(Connection conn, String hash, byte[] bildeByte) {
        try {
            String sql = "INSERT INTO bilde (Bilde_hash, Bilde)" + "VALUES(?, ?)";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, hash);
            statement.setBytes(2, bildeByte);
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

    private String MD5Hash(byte[] bildeByte) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestArr = md.digest(bildeByte);
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < digestArr.length; ++i) {
                buffer.append(Integer.toHexString((digestArr[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException e) {
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
                liste.add(new Bilde(rs.getBytes("Bilde"), rs.getString("Bilde_hash")));
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

    public void delete(Connection conn, int id) {
        List<Bilde> liste = readAll(conn, id);

        for (Bilde bilde : liste) {
            deleteLink(conn, bilde, id);
            if (!iBruk(conn, bilde)) {
                deletebilde(conn, bilde);
            }
        }

    }

    private void deleteLink(Connection conn, Bilde bilde, int id) {
        try {
            String query = "DELETE FROM bildelink WHERE BoenhetsType_ID = ? AND Bilde_hash = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.setString(2, (bilde.getHash()));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletebilde(Connection conn, Bilde bilde) {
        try {
            String sql = "DELETE FROM bilde WHERE Bilde_hash = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, bilde.getHash());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean iBruk(Connection conn, Bilde bilde) {
        boolean iBruk = false;

        try {
            String query = "SELECT Bilde_hash FROM bildelink WHERE Bilde_hash = ?";
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

    public void update(List<Bilde> bildeN, int id) {
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        
        List<Bilde> bildeG = readAll(conn, id);
        
        boolean fjernet;
        for (Bilde skjekkBildeG : bildeG) {
            fjernet = true;
            for (Bilde skjekkBildeN : bildeN) {
                if (MD5Hash(skjekkBildeN.getBilde()).equals(skjekkBildeG.getHash())) {
                    fjernet = false;
                }
            }
            if (fjernet) {
                deleteLink(conn, skjekkBildeG, id);

                if (!iBruk(conn, skjekkBildeG)) {
                    deletebilde(conn, skjekkBildeG);
                }
            }
        }

        boolean lagtTil;
        for (Bilde skjekkBildeN : bildeN) {
            lagtTil = true;
            for (Bilde skjekkeBildeG : bildeG) {
                if (MD5Hash(skjekkBildeN.getBilde()).equals(skjekkeBildeG.getHash())) {
                    lagtTil = false;
                }
            }
            if (lagtTil) {
                insert(conn, skjekkBildeN, id);
            }
        }
    }
}
