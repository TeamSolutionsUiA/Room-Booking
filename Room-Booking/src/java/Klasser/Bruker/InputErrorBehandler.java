package Klasser.Bruker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathans
 */
public class InputErrorBehandler {
    
 private final BrukerDAO brukerDAO;
    
 public InputErrorBehandler() {
    
     brukerDAO = new BrukerDAO();
 }
    public boolean sjekkBrukerEksist(String epost){
    
       boolean brukerEksisterer = false;
        
        if(brukerDAO.sjekkBrukerEksist(epost)){
            brukerEksisterer = true;
        
        }
        return brukerEksisterer;
    }
    public boolean validEpostFormat(String epost){
        
        boolean godkjentFormat = true;
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(epost);
        
        if(!matcher.matches()){
            godkjentFormat = false;
        }
        return godkjentFormat;
    }
    public boolean validFodselsDato(String fodselsDato){
        
        boolean godkjentFormat = true;
        String pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-"
                + "(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|"
                + "(?:(?:0[13578]|1[02])-31))";
        
        Pattern godkjentPattern = Pattern.compile(pattern);
        Matcher matcher = godkjentPattern.matcher(fodselsDato);
        
         if(!matcher.matches()){
            godkjentFormat = false;
        }
         return godkjentFormat;
    }
    
    //https://www.tutorialspoint.com/name-validation-using-java-regular-expressions
    public boolean validNavn(String navn){
     
      boolean validNavn = true; 
        
      //String inputNavn = navn;
      //String patternNavn = "\\p{Upper}(\\p{Lower}+\\s?)";
      //String patternFulltNavn = "(" + patternNavn + "){2,3,4}";
      
        if(navn.isEmpty())
            validNavn = false;
        
        return validNavn; 
      
   }
   //https://java2blog.com/validate-phone-number-java/
   // Sjekk format før implementering i Servlet.
    public boolean validTLF(String telefon){
      
      Pattern p = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$"); 
 
      Matcher m = p.matcher(telefon); 
		
      return (m.find() && m.group().equals(telefon)); 
}
   
    
}
    
