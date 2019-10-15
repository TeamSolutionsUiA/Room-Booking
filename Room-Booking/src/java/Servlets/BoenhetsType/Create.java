/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.BoenhetsType;

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
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "BoenhetsType_Create", urlPatterns = {"/boenhetstype/ny"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
public class Create extends HttpServlet {

    private BoenhetsTypeDAO boenhetsTypeDAO;

    /**
     * Beskrivelse
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String navn = request.getParameter("Navn");

            String enkeltsengerStr = request.getParameter("Enkeltsenger");
            int enkeltsenger = Integer.parseInt(enkeltsengerStr);

            String dobeltsengerStr = request.getParameter("Dobeltsenger");
            int dobeltsenger = Integer.parseInt(dobeltsengerStr);

            String beskrivelse = request.getParameter("Beskrivelse");

            String prisStr = request.getParameter("Pris");
            int pris = Integer.parseInt(prisStr);

            Kategori kategori = new Kategori(request.getParameter("Kategori"));
            
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
            boenhetsType = new BoenhetsType(navn, kategori, enkeltsenger, dobeltsenger, beskrivelse, pris, bilder, egenskaperList);
            boenhetsTypeDAO = new BoenhetsTypeDAO();
            int id = boenhetsTypeDAO.insert(boenhetsType);

            if (id != 0) {
                String reDir = "../boenhetstype?id=" + id;
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
        RequestDispatcher dispacher = request.getRequestDispatcher("ny.html");
                dispacher.forward(request, response);
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
        insert(request, response);
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
