/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Boenhet;

import Klasser.Boenhet.Boenhet;
import Klasser.Boenhet.BoenhetDAO;
import Klasser.BoenhetsType.Bilde;
import Klasser.BoenhetsType.BoenhetsType;
import Klasser.BoenhetsType.BoenhetsTypeDAO;
import Klasser.BoenhetsType.Egenskap;
import Klasser.BoenhetsType.Kategori;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Are
 */
@WebServlet(name = "Boenhet_Update", urlPatterns = {"/boenhet/oppdater"})
public class Update extends HttpServlet {

    private BoenhetDAO boenhetDAO;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Oppdater Boenhet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"Update\">");
            out.println("<h1>Oppdater Boenhet </h1>");
            out.println("<form action=\"oppdater\" method=\"post\">");

            String boenhetNr = request.getParameter("boenhet");
            boenhetDAO = new BoenhetDAO();
            Boenhet boenhet = boenhetDAO.read(boenhetNr);
            
            out.println("<h3>Boenhetsnummer:</h3>");
            out.println("<p><input type=\"text\" name=\"boenhet\" placeholder=\"Navn\" value=\"" + boenhetNr + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"gammelBoenhetNr\" placeholder=\"Navn\" value=\"" + boenhetNr + "\" hidden></p>");
            out.println("<h3>BoenhetsType ID:</h3>");
            out.println("<p><input type=\"text\" name=\"id\" placeholder=\"ID\" value=\"" + boenhet.getBoenhetstypeID() + "\" required></p>");
            out.println("<p><input type=\"submit\" value=\"Oppdater romtype\"></p>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        String boenhetNr = request.getParameter("boenhet");
        String gammelBoenhetNr = request.getParameter("gammelBoenhetNr");

        Boenhet boenhet;
        boenhet = new Boenhet(boenhetNr, id);
        boenhetDAO = new BoenhetDAO();
        boenhetDAO.update(boenhet, gammelBoenhetNr);

        String reDir = "../boenhetstype?id=" + boenhet.getBoenhetstypeID();
        response.sendRedirect(reDir);

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
        updateForm(request, response);
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
        update(request, response);
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
