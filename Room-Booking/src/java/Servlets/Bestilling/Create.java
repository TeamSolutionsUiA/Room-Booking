/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bestilling;

import Klasser.Bestilling.*;
import Klasser.Boenhet.*;
import Klasser.Bruker.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "Bestilling.Create", urlPatterns = {"/bestilling/create"})
public class Create extends HttpServlet {

    private BestillingDAO bestillingDAO;
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

        // Hente inn kundedata
        String navn = request.getParameter("fornavn");
        String etterNavn = request.getParameter("etternavn");
        String fodselsDato = request.getParameter("fodselsdato");
        String mobilNummer = request.getParameter("mobilnummer");
        String epost = request.getParameter("epost");

        String rolle = "Kunde";

        // Legge inn kundeinfo som bruker i databasen, med rolle
        // som "Kunde"- en bruker som ikke kan logge inn.
        Bruker kundeBruker = new Bruker(rolle, navn, etterNavn, fodselsDato, epost, mobilNummer);
        brukerDAO = new BrukerDAO();
        String query = "INSERT INTO Bruker (Rolle, Fornavn, Etternavn, DOB, Epost, Telefon) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int brukerID = brukerDAO.insert(kundeBruker, query);

        // Hente inn bestillingsinfo
        String startDato = request.getParameter("fradato");
        String sluttDato = request.getParameter("tildato");
        String boenhetsTypeIDString = request.getParameter("boenhetstypeid");
        int boenhetsTypeID = Integer.parseInt(boenhetsTypeIDString);

        // Finne boenhetsnummer.
        String boenhetSQL = "SELECT Boenhet.* FROM ((Boenhet LEFT JOIN BoenhetsType"
                + " ON Boenhet.BoenhetsType_ID = '" + boenhetsTypeID + "')"
                + " LEFT JOIN BestillingsLinje"
                + " ON Boenhet.BoenhetsNummer = BestillingsLinje.BoenhetsNummer)"
                + " LEFT JOIN Bestilling ON Bestilling.Bestillingsnummer"
                + " = BestillingsLinje.BestillingsNummer"
                + " WHERE Boenhet.BoenhetsNummer NOT IN (SELECT Boenhet.BoenhetsNummer FROM (Boenhet"
                + " RIGHT JOIN BestillingsLinje"
                + " ON BestillingsLinje.BoenhetsNummer = Boenhet.BoenhetsNummer)"
                + " RIGHT JOIN Bestilling"
                + " ON Bestilling.Bestillingsnummer = BestillingsLinje.BestillingsNummer"
                + " WHERE (Bestilling.SluttDato > '" + startDato + "')"
                + " AND (Bestilling.StartDato < '" + sluttDato + "'));";

        boenhetDAO = new BoenhetDAO();
        List<Boenhet> tilgjengeligeBoenheter = boenhetDAO.readAll(boenhetSQL);
        List<Boenhet> boenheter = new ArrayList();

        if (!tilgjengeligeBoenheter.isEmpty()) {

            boenheter.add(tilgjengeligeBoenheter.get(0));

        } else {
            //Error
        }

        Bestilling bestilling;
        bestilling = new Bestilling(startDato, sluttDato, brukerID, boenheter);

        bestillingDAO = new BestillingDAO();

        bestillingDAO.insert(bestilling);
        
        String reDir = "../";
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
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
