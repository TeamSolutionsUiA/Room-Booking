/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Bruker;

import Klasser.Bruker.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author Jonathans
 */
   @WebServlet(name = "Bruker_Create", urlPatterns = {"/bruker/register"})
    @MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
        )

   public class Create extends HttpServlet {
       
       private BrukerDAO brukerDAO;
       private InputErrorBehandler inputBehandler;
       private PassordHasher passordHasher;
       
       // Maps med alle errormeldinger og alle input-verdier som skal 
       //gjenbrukes hvis feil oppstår
        Map<String, String> errors; 
        Map<String, String> afters; 
       
       protected void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
 
        try (PrintWriter out = response.getWriter()) {
out.println("getWriter = ok");
            
            //Innhenting av alle parametere
            String rolle = "Bruker";
            
            String forNavn = request.getParameter("Navn");
            String etterNavn = request.getParameter("Etternavn");
            String navn = forNavn + " " + etterNavn;
out.println(navn);
           
            String fodselsDato = request.getParameter("Fodselsdato");
            
out.println(fodselsDato);
            
           
            String epost = request.getParameter("Epost");
            
out.println(epost);
            
            // Innhenting og kryptering av passord:
            passordHasher = new PassordHasher();
            
            String verifPassord = "";
            String passord = passordHasher.krypterPassord(request.getParameter("Passord"));
            String passordBekreft = passordHasher.krypterPassord(request.getParameter("Re-passord"));
            if(passordBekreft.equals(passord)){
                verifPassord = passord;
                
            }
out.println(verifPassord);
            
            String telefonStr = request.getParameter("Mobilnummer");
            int telefon = Integer.parseInt(telefonStr);
out.println(telefon);          
            // Legger inn alle parametere i liste (after) for gjenbruk i tilfelle error.
            afters = new HashMap();
           
            afters.put("Navn",forNavn);
            afters.put("Etternavn",etterNavn);
            afters.put("Fodselsdato", fodselsDato);
            afters.put("Epost",epost);
            afters.put("Mobilnummer",telefonStr);
            
out.println(afters);
            // Verifisering av parametere og opprettelse av error.
            inputBehandler = new InputErrorBehandler();
            errors = new HashMap(); 
            
            if(inputBehandler.sjekkBrukerEksist(epost))
                errors.put("Epost", "Det finnes allerede en bruker med denne epostadressen! Vennligst gå til innlogging.");            
            
            if(!inputBehandler.validEpostFormat(epost))
                errors.put("Epost", "Dette er ikke en epostadresse.");
            
            if(!inputBehandler.validFodselsDato(fodselsDato))
                errors.put("Fodselsdato", "Vennligst bruk dette formatet: åååå/mm/dd");
            
            if(!inputBehandler.validNavn(forNavn))
                errors.put("Navn", "Vennligst legg til fornavn.");
                        
            if(!inputBehandler.validNavn(etterNavn))
                errors.put("Etternavn", "Vennligst legg til etternavn.");
out.println(errors);
            //Opprettelse av ny bruker, dersom det ikke er errors.
            
            if(errors.isEmpty()){   
                Bruker bruker;
                bruker = new Bruker(rolle, navn, fodselsDato, epost, verifPassord, telefon);
out.println(bruker); 
                brukerDAO = new BrukerDAO();
                int id = brukerDAO.insert(bruker);
out.println("ID: " + id);
            
                if (id != 0) {
    
                    String reDirBruker = "../bruker?id=" + id;
                    response.sendRedirect(reDirBruker);
                }
            }
                    else{
                
                        // Legger inn errors og after-verdier i felt  
                        // i opprinnelig jsp form og presenterer for bruker.
                        request.setAttribute("after", afters); 
                        request.setAttribute("errors", errors);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                       
         
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
        RequestDispatcher dispacher = request.getRequestDispatcher("register.html");
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
           try {
               insert(request, response);
           } catch (ParseException ex) {
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

