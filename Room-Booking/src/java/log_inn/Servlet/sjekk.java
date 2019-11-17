/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log_inn.Servlet;

import Klasser.Bruker.Bruker;
import Klasser.loginDAO;
import Klasser.Bruker.PassordHasher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author altee
 */
@WebServlet(name = "sjekk", urlPatterns = {"/Logg_inn/login"})
 @MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
        )
public class sjekk extends HttpServlet {
   
    private PassordHasher passordHasher;
   
  
       /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void checking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet kjekk_loginn</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet kjekk_loginn at " + request.getContextPath() + "</h1>");
           
          passordHasher = new PassordHasher();
            
          String epost=request.getParameter("epost");
          String passord = passordHasher.krypterPassord(request.getParameter("passord"));
          
            loginDAO dao = new  loginDAO();
           Bruker bruker = dao.check(epost, passord);
            
            String destPage = "login.jsp";
         
            
                 
        
            if( dao.check(epost,passord)!= null  ){
                
                 String roll = bruker.getRolle();
                 
                if (roll.equals("Bruker")) {
                 response.sendRedirect("welcom.jsp");
             
                 HttpSession session = request.getSession();
                 
                 session.setAttribute("brukerId", bruker.getId());
                 session.setAttribute("fornavn1", bruker.getFornavn());
                 session.setAttribute("Etternavn", bruker.getEtternavn());
                
                 session.setAttribute("DOB", bruker.getFodselsDato());
                 session.setAttribute("epost", bruker.getEpost());
                 session.setAttribute("tele", bruker.getTelefon());
                        }
                 else if (roll.equals("admin")) { 
                         out.println("welcom to Admin page");
                         
                         }
             
                 
                 
                 
              
              
                
        }
            else{
                String message = "Invalid email/password";
                request.setAttribute("message", message);
                
                /*response.sendRedirect("login.html");*/
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
                
                
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
        checking(request, response);
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
          checking(request, response);
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