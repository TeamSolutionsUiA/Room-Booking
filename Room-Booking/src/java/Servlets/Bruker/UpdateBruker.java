/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bruker;

import Klasser.Bruker.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamedjabokji
 */
@WebServlet(name = "Bruker_Update", urlPatterns = {"/bruker/oppdaterBruker"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
public class UpdateBruker extends HttpServlet {
    private BrukerDAO brukerDAO;
    private InputErrorBehandler inputBehandler;
    private PassordHasher passordHasher;
       
       // Maps med alle errormeldinger og alle input-verdier som skal 
       //gjenbrukes hvis feil oppstår
        Map<String, String> errors; 
        Map<String, String> after; 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Viser registreringsskjema for bruker med ferdig utfyllt data
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException
     */
    
     
        protected void update(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
  
        try (PrintWriter out = response.getWriter()) {
out.println("getWriter = ok"); 

            String idStr = request.getParameter("ID");
            int id = Integer.parseInt(idStr);       
out.println(id);            
            String rolle = "Bruker";
            
            String forNavn = request.getParameter("Fornavn");
            String etterNavn = request.getParameter("Etternavn");
        
out.println(forNavn);
out.println(etterNavn);
           
            String fodselsDato = request.getParameter("Fodselsdato");
            
out.println(fodselsDato);
            
           
            String epost = request.getParameter("Epost");
            
out.println(epost);
            
            // Innhenting og kryptering av passord:
            passordHasher = new PassordHasher();
            
            boolean nyttPassord = false;
            String verifPassord = "";
            String passord = passordHasher.krypterPassord(request.getParameter("Passord"));
            String passordBekreft = passordHasher.krypterPassord(request.getParameter("Re-Passord"));
            if(passordBekreft.equals(passord)){
                verifPassord = passord;
                }
            if(!request.getParameter("Passord").equals("")){
                nyttPassord = true;
                }
                
          
out.println(verifPassord);
            
            String telefon = request.getParameter("Mobilnummer");

out.println(telefon);          
            // Legger inn alle parametere i liste (after) for gjenbruk i tilfelle error.
            after = new HashMap();
           
            after.put("Navn",forNavn);
            after.put("Etternavn",etterNavn);
            after.put("Fodselsdato", fodselsDato);
            after.put("Epost",epost);
            after.put("Mobilnummer",telefon);
            
out.println(after);
            // Verifisering av parametere og opprettelse av error.
            inputBehandler = new InputErrorBehandler();
            errors = new HashMap(); 
                       
            if(!inputBehandler.validEpostFormat(epost))
                errors.put("Epost", "Dette er ikke en epostadresse.");
            
            if(!inputBehandler.validFodselsDato(fodselsDato))
                errors.put("Fodselsdato", "Vennligst bruk dette formatet: åååå/mm/dd");
            
            if(!inputBehandler.validNavn(forNavn))
                errors.put("Navn", "Vennligst legg til fornavn.");
            
            if(!inputBehandler.validNavn(etterNavn))
                errors.put("Etternavn", "Vennligst legg til etternavn.");
            
            if(verifPassord.equals(""))
                errors.put("Passord", "Passordene er ikke like!");
                
out.println(errors);
            //Opprettelse av ny bruker, dersom det ikke er errors og oppdatering
            //av databasen.
            int updateID = 0;
            Bruker bruker = null;
            
            if(errors.isEmpty() & nyttPassord == true) {   
                bruker = new Bruker(id,forNavn, etterNavn, fodselsDato, epost, verifPassord, telefon);
out.println(bruker); 
                String query = "UPDATE Bruker SET Fornavn=?, Etternavn=?, DOB=?, Epost=?, Passord=?, Telefon=? WHERE ID=?";
                brukerDAO = new BrukerDAO();
                
                    updateID = brukerDAO.update(bruker, query);
            } else if((errors.isEmpty() & nyttPassord == false)) {   
                    bruker = new Bruker(id,forNavn, etterNavn, fodselsDato, epost,telefon);
out.println(bruker); 
                String query = "UPDATE Bruker SET Fornavn=?, Etternavn=?, DOB=?, Epost=?, Telefon=? WHERE ID=?";
                brukerDAO = new BrukerDAO();
                
                    updateID = brukerDAO.update(bruker, query);
            }
  
out.println("ID: " + updateID);
            
                if (updateID != 0) {
out.println("Vellykket");
                    String reDirBruker = "../bruker?id=" + bruker.getId();
                    response.sendRedirect(reDirBruker);
                }
                    else{
                
                        // Legger inn errors og after-verdier i felt  
                        // i opprinnelig jsp form og presenterer for bruker.
                        //request.setAttribute("after", after); 
                        //request.setAttribute("errors", errors);
                        //request.getRequestDispatcher("register.jsp").forward(request, response);
    out.println("Noe gikk galt");
         
                    }
                }
       
        }  
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            update(request, response);
 
        } catch (SQLException ex) {
            Logger.getLogger(UpdateBruker.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
      
   
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            update(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(UpdateBruker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
