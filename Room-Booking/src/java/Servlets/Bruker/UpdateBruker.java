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
import javax.servlet.ServletContext;
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
    protected void updateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Update</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"Update\">");
            out.println("<h1>Oppdater bruker</h1>");
            out.println("<form action=\"oppdater\" method=\"post\" enctype=\"multipart/form-data\">");
            String IDStr = request.getParameter("id");
            int ID = Integer.parseInt(IDStr);
            
 out.println(ID);           
            //Innhenting av brukerdata fra databasen:
            brukerDAO = new BrukerDAO();
            Bruker bruker = brukerDAO.read(ID);
            
            out.println("<p><input type=\"hidden\" name=\"ID\" placeholder=\"ID\" hidden=\"hidden\" value=\"bruker.getId()\" readonly></p>");
            out.println("<p><input type=\"text\" name=\"Fornavn\" placeholder=\"Fornavn\" value=\"" + bruker.getFornavn() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Etternavn\" placeholder=\"Etternavn\" value=\"" + bruker.getEtternavn() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Fodselsdato\" placeholder=\"Fødselsdato\" value=\"" + bruker.getFodselsDato() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Mobilnummer\" placeholder=\"Mobilnummer\" min=\"8\" max=\"14\" value=\"" + bruker.getTelefon() + "\"></p>");
            out.println("<p><input type=\"email\" name=\"Epost\" placeholder=\"Epost-adresse\" min=\"6\" max=\"100\" value=\"" + bruker.getEpost() + "\"></p>");
            out.println("<p><input type=\"password\" name=\"Passord\"  placeholder=\"Passord\" value=\"" + bruker.getPassord() + "\"required></p>");
            out.println("<p><input type=\"password\" name=\"Re-Passord\"  placeholder=\"Bekreft passord\" value=\"" + bruker.getPassord() + "\"required></p>");
       
            out.println("<p><input type=\"submit\" value=\"Oppdater bruker\"></p>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }
    
            
        protected void update(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
  
        try (PrintWriter out = response.getWriter()) {
out.println("getWriter = ok");      
            
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
            
            String verifPassord = "";
            String passord = passordHasher.krypterPassord(request.getParameter("Passord"));
            String passordBekreft = passordHasher.krypterPassord(request.getParameter("Re-Passord"));
            if(passordBekreft.equals(passord)){
                verifPassord = passord;
                
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
            
            if(inputBehandler.sjekkBrukerEksist(epost))
                errors.put("Epost", "Det finnes allerede en bruker med denne epostadressen! Vennligst gå til innlogging.");            
            
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
            //Opprettelse av ny bruker, dersom det ikke er errors.
            
            if(errors.isEmpty()){   
                Bruker bruker;
                bruker = new Bruker(rolle, forNavn, etterNavn, fodselsDato, epost, verifPassord, telefon);
out.println(bruker); 
                brukerDAO = new BrukerDAO();
                
                int id = brukerDAO.update(bruker);
  
out.println("ID: " + id);
            
                if (id != 0) {
    
                    String reDirBruker = "../bruker?id=" + id;
                    response.sendRedirect(reDirBruker);
                }
            }
                    else{
                
                        // Legger inn errors og after-verdier i felt  
                        // i opprinnelig jsp form og presenterer for bruker.
                        request.setAttribute("after", after); 
                        request.setAttribute("errors", errors);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                       
         
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
            updateForm(request, response);
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
