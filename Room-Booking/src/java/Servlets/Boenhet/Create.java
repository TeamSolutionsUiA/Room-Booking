/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Boenhet;

import Klasser.Boenhet.Boenhet;
import Klasser.Boenhet.BoenhetDAO;
import Klasser.BoenhetsType.BoenhetsType;
import Klasser.BoenhetsType.BoenhetsTypeDAO;
import Klasser.BoenhetsType.Egenskap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lasse
 */
@WebServlet(name = "Boenhet_Create", urlPatterns = {"/boenhet/ny"})
public class Create extends HttpServlet {

    private BoenhetDAO boenhetDAO;
    private BoenhetsTypeDAO boenhetsTypeDAO;

    protected void createForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ny Boenhet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"Update\">");
            String IDStr = request.getParameter("id");
            int ID = Integer.parseInt(IDStr);

            boenhetsTypeDAO = new BoenhetsTypeDAO();
            BoenhetsType boenhetsType = boenhetsTypeDAO.read(ID);
            out.println("<h1>Opprett ny Boenhet i " + boenhetsType.getNavn() + "</h1>");
            out.println("<form action=\"ny\" method=\"post\" enctype=\"multipart/form-data\">");

            out.println("<p><input type=\"text\" name=\"boenhetsNummer\" placeholder=\"boenhetsNummer\"></p>");
            out.println("<p><input type=\"text\" name=\"id\" hidden></p>");
            out.println("<p><input type=\"submit\" value=\"opprett boenhet\"></p>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String boenhetsNummer = request.getParameter("boenhetsNummer");
            String BoenhetsTypeIDString = request.getParameter("id");
            int BoenhetsTypeID = Integer.parseInt(BoenhetsTypeIDString);

            Boenhet boenhet;
            boenhet = new Boenhet(boenhetsNummer, BoenhetsTypeID);
            boenhetDAO.insert(boenhet);

            if (BoenhetsTypeID != 0) {
                String reDir = "../boenhetstype?id=" + BoenhetsTypeID;
                response.sendRedirect(reDir);
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
        createForm(request, response);
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
        create(request, response);
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
