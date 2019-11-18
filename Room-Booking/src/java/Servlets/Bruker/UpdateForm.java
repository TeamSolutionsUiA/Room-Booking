/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bruker;

import Klasser.Bruker.Bruker;
import Klasser.Bruker.BrukerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Bruker_UpdateForm", urlPatterns = {"/bruker/oppdater"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
/**
 *
 * @author Jonathans
 */
public class UpdateForm extends HttpServlet {
    
    private BrukerDAO brukerDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
            out.println("<form action=\"oppdaterBruker\" method=\"post\" enctype=\"multipart/form-data\">");
            String IDStr = request.getParameter("id");
            int ID = Integer.parseInt(IDStr);
            
 out.println(ID);           
            //Innhenting av brukerdata fra databasen:
            brukerDAO = new BrukerDAO();
            Bruker bruker = brukerDAO.read(ID);
            
            out.println("<p><input type=\"hidden\" name=\"ID\" value=\"" + bruker.getId() + "\" readonly></p>");
            out.println("<p><input type=\"text\" name=\"Fornavn\" placeholder=\"Fornavn\" value=\"" + bruker.getFornavn() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Etternavn\" placeholder=\"Etternavn\" value=\"" + bruker.getEtternavn() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Fodselsdato\" placeholder=\"Fødselsdato\" value=\"" + bruker.getFodselsDato() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Mobilnummer\" placeholder=\"Mobilnummer\" min=\"8\" max=\"14\" value=\"" + bruker.getTelefon() + "\"></p>");
            out.println("<p><input type=\"email\" name=\"Epost\" placeholder=\"Epost-adresse\" min=\"6\" max=\"100\" value=\"" + bruker.getEpost() + "\"></p>");
            out.println("<p>Legg til nytt passord(valgfritt):</p>");
            out.println("<p><input type=\"password\" name=\"Passord\"  placeholder=\"Passord\" value=\"\"></p>");
            out.println("<p><input type=\"password\" name=\"Re-Passord\"  placeholder=\"Bekreft passord\" value=\"\"></p>");
       
            out.println("<p><input type=\"submit\" value=\"Oppdater bruker\"></p>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
         
    
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
            
            updateForm(request, response);

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
