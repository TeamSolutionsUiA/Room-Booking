/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author arefj
 */
public class BoenhetsTypeDAO {
    private Connection conn;
    private BildeDAO bilderDAO;
    private EgenskapDAO egenskaperDAO;
    
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
            for (Egenskap egenskap : boenhetsType.getEgenskaper()){
                egenskaperDAO.Insert(conn, egenskap, id);
            }
            for (Bilde bilde : boenhetsType.getBilder()){
                bilderDAO.Insert(conn, bilde, id);
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
   public void read () {
       DbTool dbTool = new DbTool();
       conn = dbTool.loggInn();
       
       
       
       try {
       Statement stm =conn.createStatement();
       String query ="SELECT * From LeilighetsType";
       ResultSet rs = stm.executeQuery(query);
       ArrayList<BoenhetsType> list = new ArrayList<BoenhetsType>();
       
       
       
       while (rs.next()) {
         

       list.add(new BoenhetsType(rs.getString("navn"), rs.getString("enkeltSenger"), rs.getString("DobeltSenger"), rs.getString("Beskrivelse"), rs.getString("Pris"), EgenskapDAO.readEgenskaper(conn,rs.getString("Leilighet_ID"))));   
        
       
       
       
       }
       
       }catch(Exception e) {
           
           
       }
       
   }
    
    
    
}
