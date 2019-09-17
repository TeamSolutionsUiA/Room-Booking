/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Klasser.Bilde;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Klasser.BoenhetsType;
import Klasser.BoenhetsTypeDAO;
import Klasser.Egenskap;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Are
 */
@WebServlet(name = "BoenhetsTypeController", urlPatterns = {"/boenhetstype"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class BoenhetsTypeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private BoenhetsTypeDAO boenhetsTypeDAO;
    
    protected void insertBoenhetsType(HttpServletRequest request, HttpServletResponse response)
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
            
            String kategori = request.getParameter("Kategori");
            String egenskaper = request.getParameter("Egenskaper");
            
            List<Egenskap> egenskaperList = new ArrayList();
            for (String egenskap : egenskaper.split(",")){
            Egenskap nyEgenskap = new Egenskap(egenskap);
            egenskaperList.add(nyEgenskap);
            }
            
            List<Bilde> bilder = new ArrayList<>();
            for (Part bilde : request.getParts()){
                if (bilde.getContentType() != null){
                    InputStream inputStream = bilde.getInputStream();
                    bilder.add(new Bilde(inputStream));
                }
            }
            
            BoenhetsType boenhetsType;
            boenhetsType = new BoenhetsType(navn, kategori, enkeltsenger, dobeltsenger, beskrivelse, pris , egenskaperList, bilder);
            boenhetsTypeDAO.Insert(boenhetsType);
        }
    }
    
    protected void ReadAllBoenhetsType(HttpServletRequest request, HttpServletResponse response)
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
            List<String> kategorier = boenhetsType;
            List<BoenhetsType> boenhetsTyper= boenhetsTypeDAO.readAll();
            for
            for (BoenhetsType boenhetsType : boenhetsTyper){
                out.println("<div>");
                for
                out.println("<h2>");
                out.println(boenhetsType.getNavn());
                out.println("</h2>");
                out.println("<>");
                out.println("<>");
                out.println("</div>");
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
        String action = request.getServletPath();
        switch (action) {
            case "/ny":
                //vise innholdet som er i newleilightstype.html usikker på om den funker nå
                response.sendRedirect("newleilightstype.html");
                break;
            default:
                break;
        }
        
        //processRequest(request, response);
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
        String action = request.getServletPath();
        switch (action) {
            case "/ny":
                insertBoenhetsType(request, response);
                break;
            default:
                break;
        }

        //processRequest(request, response);
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
