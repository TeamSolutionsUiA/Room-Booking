package Klasser.Bruker;

import Klasser.Bruker.BrukerDAO;
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
    
 private BrukerDAO brukerDAO;
    
 public InputErrorBehandler() {
    
     brukerDAO = new BrukerDAO();
 }
    public boolean sjekkEpost(String epost){
    
        boolean godkjentEpost = brukerDAO.sjekkBrukerEksist(epost);
        return godkjentEpost;
    }
}