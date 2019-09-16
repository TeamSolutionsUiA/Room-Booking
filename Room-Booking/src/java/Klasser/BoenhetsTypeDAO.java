/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.InputStream;
import java.sql.*;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author arefj
 */
public class BoenhetsTypeDAO {
    private Connection conn;
    private BilderDAO bilderDAO;
    private EgenskaperDAO egenskaperDAO;
    
    public void Insert(BoenhetsType boenhetsType){
        DbTool dbTool = new DbTool();
        conn = dbTool.loggInn();
        try{
            String sql = "INSERT INTO LeilighetsType (Navn, Kategori, Enkeltsenger, Dobeltsenger, Beskrivelse, Pris) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, boenhetsType.getNavn());
            statement.setString(2, boenhetsType.getKategori());
            statement.setString(3, boenhetsType.getEnkeltsenger());
            statement.setString(4, boenhetsType.getDobeltsenger());
            statement.setString(5, boenhetsType.getBeskrivelse());
            statement.setString(6, boenhetsType.getPris());
 
            int rowsInserted = statement.executeUpdate();
            ResultSet idRs = statement.getGeneratedKeys();
            if (idRs.next()){
            int id = idRs.getInt(1);
            egenskaperDAO.Insert(conn, boenhetsType.getEgenskaper(), id);
            bilderDAO.Insert(conn, boenhetsType.getBilder(), id);
            } else {
                throw new SQLException("Ingen ID returnert");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
}
