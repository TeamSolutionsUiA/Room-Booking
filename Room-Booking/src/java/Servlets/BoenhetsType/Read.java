/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.BoenhetsType;

import Klasser.Bilde;
import Klasser.BoenhetsType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Klasser.BoenhetsTypeDAO;
import Klasser.Egenskap;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author arefj
 */
@WebServlet(name = "BoenhetsType_Read", urlPatterns = {"/boenhetstype"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
public class Read extends HttpServlet {

    private BoenhetsTypeDAO boenhetsTypeDAO;

    /**
     * Beskrivelse
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void readAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BoenhetsTyper</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>BoenhetsTyper</h1>");
            boenhetsTypeDAO = new BoenhetsTypeDAO();
            List<String> kategorier = boenhetsTypeDAO.readAllKategorier();
            List<BoenhetsType> boenhetsTyper = boenhetsTypeDAO.readAll();

            for (String kategori : kategorier) {
                out.println("<div>");
                out.println("<h2>");
                out.println(kategori);
                out.println("</h2>");

                for (BoenhetsType boenhetsType : boenhetsTyper) {
                    if (kategori.equals(boenhetsType.getKategori())) {
                        out.println("<div>");
                        out.println("<a href=\"?id=" + boenhetsType.getID() + "\">");
                        out.println("<h3>");
                        out.println(boenhetsType.getNavn());
                        out.println("</h3>");
                        out.println("<p>");
                        out.println(boenhetsType.getBeskrivelse());
                        out.println("</p>");

                        int eSeng = boenhetsType.getEnkeltsenger();
                        int dSeng = boenhetsType.getDobeltsenger();
                        int sengTotal = eSeng + dSeng * 2;
                        if (sengTotal > 0) {
                            out.println("<p>");
                            out.println("Antall enkeltsenger" + eSeng);
                            out.println("Antall dobbelsenger" + dSeng);
                            out.println("Antall sengeplasser " + sengTotal);
                            out.println("</p>");
                        }
                        out.println("<p>");
                        out.println("Pris" + boenhetsType.getPris() + "Nok");
                        out.println("</p>");
                        List<Egenskap> egenskaper = boenhetsType.getEgenskaper();
                        out.println("<p>");
                        out.println("<h4>Egenskaper:</h4>");
                        for (Egenskap egenskap : egenskaper) {
                            out.println(egenskap.getEgenskap());
                        }
                        out.println("</p>");
                        List<Bilde> bilder = boenhetsType.getBilder();
                        if (bilder != null) {
                            out.println("<div>");
                            out.println("<h4>Bilder:</h4>");
                            for (Bilde bilde : bilder) {
                                out.println("<img src=\"" + request.getContextPath() + "/bilde?id=" + bilde.getID() + "\" width=\"400px\" />");
                            }
                            out.println("</div>");
                        }
                        out.println("</a>");
                        out.println("</div>");
                    }
                }
                out.println("</div>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BoenhetsTyper</title>");
            out.println("</head>");
            out.println("<body>");

            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);

            boenhetsTypeDAO = new BoenhetsTypeDAO();
            BoenhetsType boenhetsType = boenhetsTypeDAO.read(id);
            out.println("<div>");
            out.println("<h1>" + boenhetsType.getNavn() + "</h1>");

            List<Bilde> bilder = boenhetsType.getBilder();
            if (bilder != null) {
                out.println("<div>");
                out.println("<h4>Bilder:</h4>");
                for (Bilde bilde : bilder) {
                    out.println("<img src=\"" + request.getContextPath() + "/bilde?id=" + bilde.getID() + "\" width=\"400px\" />");
                }
                out.println("</div>");
            }

            out.println("<h2>" + boenhetsType.getKategori() + "</h2>");

            out.println("<p>" + boenhetsType.getBeskrivelse() + "</p>");

            int eSeng = boenhetsType.getEnkeltsenger();
            int dSeng = boenhetsType.getDobeltsenger();
            int sengTotal = eSeng + dSeng * 2;
            if (sengTotal > 0) {
                out.println("<p>");
                out.println("Antall enkeltsenger" + eSeng);
                out.println("Antall dobbelsenger" + dSeng);
                out.println("Antall sengeplasser " + sengTotal);
                out.println("</p>");
            }
            out.println("<p>");
            out.println("Pris" + boenhetsType.getPris() + "Nok");
            out.println("</p>");

            List<Egenskap> egenskaper = boenhetsType.getEgenskaper();
            if (egenskaper != null) {
                out.println("<h4>Egenskaper:</h4>");
                out.println("<p>");
                for (Egenskap egenskap : egenskaper) {
                    out.println(egenskap.getEgenskap());
                }
                out.println("</p>");
            }

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
        if (request.getParameter("id") == null || request.getParameter("id").equals("null")) {
            readAll(request, response);
        } else {
            
            read(request, response);
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
        doGet(request, response);
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
