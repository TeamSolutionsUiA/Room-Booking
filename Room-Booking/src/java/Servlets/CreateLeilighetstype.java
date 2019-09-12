/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Klasser.LeilighetsType;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Are
 */
@WebServlet(name = "CreateRomtype", urlPatterns = {"/createleilighetstype"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class CreateLeilighetstype extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("Are er awsome");
            String navn = request.getParameter("Navn");
            String enkeltsenger = request.getParameter("Enkeltsenger");
            String dobeltsenger = request.getParameter("Dobeltsenger");
            String beskrivelse = request.getParameter("Beskrivelse");
            String pris = request.getParameter("Pris");
            String kategori = request.getParameter("Kategori");
            String egenskaper = request.getParameter("Egenskaper");
            List<InputStream> bilder = new ArrayList<>();
            for (Part bilde : request.getParts()){
                if (bilde.getContentType() != null){
                    InputStream inputStream = bilde.getInputStream();
                    bilder.add(inputStream);
                }
            }
            
            LeilighetsType leilighetsType = new LeilighetsType();
            leilighetsType.Insert(navn, kategori, enkeltsenger, dobeltsenger, beskrivelse, pris , egenskaper, bilder);
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
