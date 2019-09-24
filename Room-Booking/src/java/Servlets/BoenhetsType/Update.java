/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.BoenhetsType;

import Klasser.Bilde;
import Klasser.BoenhetsType;
import Klasser.BoenhetsTypeDAO;
import Klasser.Egenskap;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author arefj
 */
@WebServlet(name = "BoenhetsType_Update", urlPatterns = {"/boenhetstype/oppdater"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
public class Update extends HttpServlet {

    private BoenhetsTypeDAO boenhetsTypeDAO;

    /**
     * Beskrivelse
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
            out.println("<title>Servlet Update</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"Update\">");
            out.println("<h1>Oppdater Boenhetstype</h1>");
            out.println("<form action=\"oppdater\" method=\"post\" enctype=\"multipart/form-data\">");
            String IDStr = request.getParameter("id");
            int ID = Integer.parseInt(IDStr);

            boenhetsTypeDAO = new BoenhetsTypeDAO();
            BoenhetsType boenhetsType = boenhetsTypeDAO.read(ID);

            List<String> egenskaperList = new ArrayList();
            for (Egenskap egenskap : boenhetsType.getEgenskaper()) {
                egenskaperList.add(egenskap.getEgenskap());
            }
            String egenskaper = String.join(", ", egenskaperList);

            out.println("<p><input type=\"text\" name=\"Navn\" placeholder=\"Navn\" value=\"" + boenhetsType.getNavn() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"id\" placeholder=\"ID\" value=\"" + boenhetsType.getID() + "\" readonly></p>");
            out.println("<p><input type=\"text\" name=\"Kategori\" placeholder=\"Kategori\" value=\"" + boenhetsType.getKategori() + "\" required></p>");
            out.println("<p><input type=\"number\" name=\"Enkeltsenger\" placeholder=\"Antall enkeltsenger\" min=\"0\" max=\"100\" value=\"" + boenhetsType.getEnkeltsenger() + "\"></p>");
            out.println("<p><input type=\"number\" name=\"Dobeltsenger\" placeholder=\"Antall dobeltsenger\" min=\"0\" max=\"100\" value=\"" + boenhetsType.getDobeltsenger() + "\"></p>");
            out.println("<p><input type=\"text\" name=\"Beskrivelse\" placeholder=\"Beskrivelse\" value=\"" + boenhetsType.getBeskrivelse() + "\"></p>");
            //out.println("<p><input type=\"file\" name=\"Bilder\" multiple=\"multiple\" accept=\"image/*\"></p>");
            out.println("<p><input type=\"number\" name=\"Pris\" placeholder=\"Pris per døgn\" min=\"50\" max=\"100000\" value=\"" + boenhetsType.getPris() + "\" required></p>");
            out.println("<p><input type=\"text\" name=\"Egenskaper\" placeholder=\"Egenskaper separert med komma\" value=\"" + egenskaper + "\"></p>");
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
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            
            String navn = request.getParameter("Navn");

            String enkeltsengerStr = request.getParameter("Enkeltsenger");
            int enkeltsenger = Integer.parseInt(enkeltsengerStr);

            String dobeltsengerStr = request.getParameter("Dobeltsenger");
            int dobeltsenger = Integer.parseInt(dobeltsengerStr);
            
            String beskrivelse = request.getParameter("Beskrivelse");

            String prisStr = request.getParameter("Pris");
            int pris = Integer.parseInt(prisStr);

            String kategori = request.getParameter("Kategori");
            String egenskaper = request.getParameter("Egenskaper");

            List<Egenskap> egenskaperList = new ArrayList();
            for (String egenskap : egenskaper.split(",")) {
                Egenskap nyEgenskap = new Egenskap(egenskap);
                egenskaperList.add(nyEgenskap);
            }
            
            List<Bilde> bilder = new ArrayList<>();
            for (Part bilde : request.getParts()) {
                if (bilde.getContentType() != null) {
                    InputStream inputStream = bilde.getInputStream();
                    bilder.add(new Bilde(inputStream));
                }
            }

            BoenhetsType boenhetsType;
            boenhetsType = new BoenhetsType(id, navn, kategori, enkeltsenger, dobeltsenger, beskrivelse, pris, bilder, egenskaperList);
            boenhetsTypeDAO = new BoenhetsTypeDAO();
            boenhetsTypeDAO.update(boenhetsType);

            String reDir = "../boenhetstype?id=" + boenhetsType.getID();
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
