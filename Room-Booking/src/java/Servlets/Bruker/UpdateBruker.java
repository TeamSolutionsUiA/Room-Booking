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
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "Bruker_Update", urlPatterns = {"/bruker/oppdater"})
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
        Map<String, String> afters; 

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
    protected void visUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           
            String IDStr = request.getParameter("id");
            int ID = Integer.parseInt(IDStr);
            
            
            //Innhenting av brukerdata fra databasen:
            brukerDAO = new BrukerDAO();
            Bruker brukerFraDB = brukerDAO.read(ID);
            
            // Lagre brukerdata i "afters" for utfylling av skjema.
            afters = new HashMap();
            
            afters.put("Fornavn", brukerFraDB.getFornavn());
            afters.put("Etternavn", brukerFraDB.getEtternavn());
            afters.put("Fodselsdato", brukerFraDB.getFodselsDato());
            afters.put("Epost", brukerFraDB.getEpost());
            afters.put("Mobilnummer", brukerFraDB.getTelefon());
            
            //Henter skjema for å endre bruker.
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            // Fyller inn formen med lagrede data fra brukeren.
            request.setAttribute("afters", afters);
            dispatcher.forward(request, response);
            
        }
    }
    
            
        protected void update(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String idStr = request.getParameter("Id");
            int id = Integer.parseInt(idStr);
            
            String forNavn = request.getParameter("Navn");
            
            String etterNavn = request.getParameter("Etternavn");
            
            String fodselsDato = request.getParameter("fodselsDato");
            
            String epost = request.getParameter("Epost");
            
            String telefon = request.getParameter("Telefon");
         
  
            Bruker bruker;
            bruker = new Bruker( id,forNavn, etterNavn, fodselsDato,epost,telefon);
            brukerDAO = new BrukerDAO();
            brukerDAO.update(bruker);

            String reDir = "../bruker?id=" + bruker.getId();
            response.sendRedirect(reDir);

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
            visUpdateForm(request, response);
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
