/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bruker;

import Klasser.Bruker.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Bruker_Update", urlPatterns = {"/bruker/update.html"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
public class UpdateBruker extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           
            String IDStr = request.getParameter("id");
            int ID = Integer.parseInt(IDStr);
            
            brukerDAO = new BrukerDAO();
            Bruker bruker = brukerDAO.read(ID);
            out.println("<p><input type=\"text\" name=\"Navn\" placeholder=\"Navn\" value=\"" + bruker.getNavn() + "\" required></p>");
            out.println("<p><input type=\"number\" name=\"Id\" placeholder=\"ID\" value=\"" + bruker.getId() + "\" readonly></p>");
            out.println("<p><input type=\"text\" name=\"Fodselsdato\" placeholder=\"Fodselsdato\" value=\"" + bruker.getFodselsDato() + "\" required></p>");
            out.println("<p><input type=\"text  \" name=\"Epost\" placeholder=\"Epost\" value=\"" + bruker.getEpost() + "\"></p>");
            out.println("<p><input type=\"text  \" name=\"Passord\" placeholder=\"Passord\" value=\"" + bruker.getPassord() + "\"></p>");
            out.println("<p><input type=\"number\" name=\"Telefon\" placeholder=\"Telefon\" value=\"" + bruker.getTelefon() + "\"></p>");
            //out.println("<p><input type=\"file\" name=\"Bilder\" multiple=\"multiple\" accept=\"image/*\"></p>");
            out.println("<p><input type=\"submit\" value=\"Oppdater romtype\"></p>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
            
        protected void update(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String idStr = request.getParameter("Id");
            int id = Integer.parseInt(idStr);
            
            String forNavn = request.getParameter("Navn");
            
            String etterNavn = request.getParameter("Etternavn");
            
            String navn = forNavn + " " + etterNavn;

            String fodselsDato = request.getParameter("fodselsDato");
            
            String epost = request.getParameter("Epost");
            
            String telefonStr = request.getParameter("Telefon");
            int telefon = Integer.parseInt(telefonStr);
            
            Bruker bruker;
            bruker = new Bruker( id,navn,fodselsDato,epost,telefon);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
