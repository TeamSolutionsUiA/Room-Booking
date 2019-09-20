/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.BoenhetsType;

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
@WebServlet(name = "BoenhetsType", urlPatterns = {"/boenhetstype"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class BoenhetsTypeIndex extends HttpServlet {
    
    private BoenhetsTypeDAO boenhetsTypeDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void readAllBoenhetsType(HttpServletRequest request, HttpServletResponse response)
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
            List<String> kategorier = boenhetsTypeDAO.readAllKategorier();
            List<Klasser.BoenhetsType> boenhetsTyper= boenhetsTypeDAO.readAll();
            for (String kategori: kategorier ) {
                out.println("<div>");
                    out.println("<h2>");
                        out.println(kategori);
                    out.println("</h2>");
                
            
                    for (Klasser.BoenhetsType boenhetsType : boenhetsTyper){
                        if(kategori.equals(boenhetsType.getKategori())){
                            out.println("<div>");
                                out.println("<h3>");
                                    out.println(boenhetsType.getNavn());
                                out.println("</h3>");
                                out.println("<p>");
                                    out.println(boenhetsType.getBeskrivelse());
                                out.println("</p>");
                                
                            int eSeng=boenhetsType.getEnkeltsenger();
                            int dSeng=boenhetsType.getDobeltsenger();
                            int sengTotal=eSeng+dSeng;
                            if(sengTotal>0) {
                                out.println("<p>");
                                    out.println("Antall enkeltsenger" +eSeng);
                                    out.println("Antall dobbelsenger" + dSeng);
                                    out.println("Antall sengeplasser " +sengTotal);
                                out.println("</p>");
                            }
                            out.println("<p>");
                                out.println("Pris" + boenhetsType.getPris() + "Nok");
                            out.println("</p>");
                            List<Egenskap> egenskaper =boenhetsType.getEgenskaper();
                            out.println("<p>");
                                out.println("<h4>Egenskaper:</h4>");
                                for(Egenskap egenskap : egenskaper ){
                                    out.println(egenskap);
                               }
                            out.println("</p>");
                            out.println("</div>");
                        }
                    }
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
        readAllBoenhetsType(request, response);
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
        readAllBoenhetsType(request, response);
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
