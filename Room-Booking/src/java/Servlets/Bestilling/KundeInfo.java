/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bestilling;


import Klasser.Bestilling.*;
import Klasser.Boenhet.*;
import Klasser.BoenhetsType.BoenhetsType;
import Klasser.BoenhetsType.BoenhetsTypeDAO;
import Klasser.Bruker.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamJ
 */
@WebServlet(name = "Bestilling.KundeInfo", urlPatterns = {"/bestilling/bruker2"})
public class KundeInfo extends HttpServlet {

    private BestillingDAO bestillingDAO;
    private Bruker kundeBruker;
    private BrukerDAO brukerDAO;
    private BoenhetDAO boenhetDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Create</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bestillingsdetaljer:</h1>");

            // Hente inn kundedata
            String navn = request.getParameter("Navn");
            String etterNavn = request.getParameter("Etternavn");
            String fodselsDato = request.getParameter("Fodselsdato");
            String mobilNummer = request.getParameter("Mobilnummer");
            String epost = request.getParameter("Epost");
            
           
            
            // Hente inn bestillingsinfo
            String startDato = request.getParameter("fraDato");
            String sluttDato = request.getParameter("tilDato");
            String boenhetsTypeIDString = request.getParameter("boenhetstypeid");
            int boenhetsTypeID = Integer.parseInt(boenhetsTypeIDString);
         
            BoenhetsTypeDAO boenhetsTypeDAO = new BoenhetsTypeDAO();
            BoenhetsType boenhetsType = boenhetsTypeDAO.read(boenhetsTypeID);
            int antallPers = boenhetsType.getDobeltsenger() * 2 + boenhetsType.getEnkeltsenger();
            
            out.println("<p>");
            out.println("<b>" + boenhetsType.getNavn() + "</b>");
            out.println("Fra: " + startDato + "Til: " + sluttDato);
            out.println("Kategori: " + boenhetsType.getKategori());
            out.println("Antall personer: " + antallPers);
            out.println("</p>");
            
            out.println("<h2>Kundeinformasjon</h2>");
            out.println("<p>");
            out.println("Navn: " + navn + " " + etterNavn);
            out.println("Fødselsdato: " + fodselsDato);
            out.println("Epost: " + epost);
            out.println("Telefon: " + mobilNummer);
            out.println("</p>");
            
            out.println("<form action=\"create\" method=\"post\">");
            out.println("<p><input type=\"hidden\" name=\"fornavn\" value=\"" + navn + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"etternavn\" value=\"" + etterNavn + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"fodselsdato\" value=\"" + fodselsDato + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"mobilnummer\" value=\"" + mobilNummer + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"epost\" value=\"" + epost + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"boenhetstypeid\" value=\"" + boenhetsTypeID + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"fradato\" value=\"" + startDato + "\" readonly></p>");
            out.println("<p><input type=\"hidden\" name=\"tildato\" value=\"" + sluttDato + "\" readonly></p>");
            out.println("<p><input type=\"submit\" value=\"Betal\"></p>");
            out.println("</form>");

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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(KundeInfo.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(KundeInfo.class.getName()).log(Level.SEVERE, null, ex);
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
