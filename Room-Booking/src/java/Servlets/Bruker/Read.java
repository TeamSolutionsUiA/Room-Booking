/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bruker;



import Klasser.Bruker.Bruker;
import Klasser.Bruker.BrukerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamedjabokji
 */
@WebServlet(name = "Bruker_Read", urlPatterns = {"/bruker"})
public class Read extends HttpServlet {
    
    private BrukerDAO brukerDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void readAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Bruker1212</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bruker</h1>");
            brukerDAO = new BrukerDAO();
            List<Bruker> brukere = brukerDAO.readAll();

                for(Bruker bruker : brukere) {
               
                out.println("<div>");
                out.println("<a href=\"?id=" + bruker.getId() + "\">");
                out.println("<h3>");
                out.println(bruker.getFornavn() + " " + bruker.getEtternavn());
                out.println("</h3>");
                out.println("<p>");
                out.println("Fødselsdato: " + bruker.getFodselsDato());
                out.println("Epost/brukernavn: " + bruker.getEpost());
                out.println("Telefonnummer: " + bruker.getTelefon());
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
            out.println("<title>Bruker</title>");
            out.println("</head>");
            out.println("<body>");

            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            
            brukerDAO = new BrukerDAO();
            Bruker bruker = brukerDAO.read(id);
            out.println("<div>");
            out.println("<h1>" + bruker.getFornavn() + " " + bruker.getEtternavn() + "</h1>");
            out.println("</div>");
            out.println("<div>");
            out.println("Fødselsdato: "+ bruker.getFodselsDato());
            out.println("</div>");
            out.println("<div>");
            out.println("Epost/brukernavn: " + bruker.getEpost());
            out.println("</div>");
            out.println("<div>");
            out.println("Telefon: " + bruker.getTelefon());
            out.println("</div>");
            
            out.println("<form action=\"bruker/register.jsp\" method=\"post\">");
            out.println("<p><input type=\"hidden\" name=\"id\" placeholder=\"ID\" value=\"" + bruker.getId() + "\" readonly></p>");
          //out.println("<p><input type=\"submit\" value=\"Slett\"></p>");
            out.println("<p><input type=\"submit\" value=\"Endre\"></p>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
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
        readAll(request, response);
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

