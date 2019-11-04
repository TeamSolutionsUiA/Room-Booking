/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bestilling;

import Klasser.BoenhetsType.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathans
 */

@WebServlet(name = "Bestilling_VisBoenhetsTyper", urlPatterns = {"/bestilling/visBoenheter"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)

public class VisBoenhetsTyper extends HttpServlet {
    
    private BoenhetsTypeDAO boenhetsTypeDAO;
    private KategoriDAO kategoriDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void visBoenhetsTyper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            // Innhenting av søkeord fra bruker.
            String reqKategori = request.getParameter("Bestilling-kategori");
            String reqStartDato = request.getParameter("Bestilling-start");
            String reqSluttDato = request.getParameter("Bestilling-slutt");
            
            // Innhenting av alle tilgjengelige boenhetsTyper 
            // Må finnes i boenhettabell
            String boenhetsTypeSQL = "SELECT BoenhetsType.* FROM ((Boenhet LEFT JOIN BoenhetsType" +
               " ON Boenhet.BoenhetsType_ID = BoenhetsType.ID)" +
               " LEFT JOIN BestillingsLinje" +
               " ON Boenhet.BoenhetsNummer = BestillingsLinje.BoenhetsNummer)" +
               " LEFT JOIN Bestilling ON Bestilling.Bestillingsnummer" +
               " = BestillingsLinje.BestillingsNummer" +
               " WHERE (BoenhetsType.PublisertStatus = 'true')" +
               " AND Boenhet.BoenhetsNummer NOT IN (SELECT Boenhet.BoenhetsNummer FROM (Boenhet" +
               " RIGHT JOIN BestillingsLinje" +
               " ON BestillingsLinje.BoenhetsNummer = Boenhet.BoenhetsNummer)" +
               " RIGHT JOIN Bestilling" +
               " ON Bestilling.Bestillingsnummer = BestillingsLinje.BestillingsNummer" +
               " WHERE (BoenhetsType.PublisertStatus = 'true')" +
               " AND (Bestilling.SluttDato > '"+reqStartDato+"')" +
               " AND (Bestilling.StartDato < '"+reqSluttDato+"'));";

            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BoenhetsTyper</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Tilgjengelige overnattingstilbud</h1>");
            
            boenhetsTypeDAO = new BoenhetsTypeDAO();
            kategoriDAO = new KategoriDAO();
            List<BoenhetsType> boenhetsTyper = boenhetsTypeDAO.readAll(boenhetsTypeSQL);

            //Printe ut alle     
                for (BoenhetsType boenhetsType : boenhetsTyper) {
                    if (reqKategori.equals(boenhetsType.getKategori().getKategori())) {
                        out.println("<div>");
                        out.println("<fieldset>");
                        out.println("<h2>");
                        out.println(reqKategori);
                        out.println("</h2>");
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
                                out.println("<img src=\"" + request.getContextPath() + "/bilde?id=" + bilde.getHash() + "\" width=\"400px\" />");
                            }
                            out.println("</div>");
                        }
                        out.println("</a>");
                        out.println("</fieldset>");
                        out.println("</div>");
                    }
                }
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
        visBoenhetsTyper(request, response);
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
        visBoenhetsTyper(request, response);
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
