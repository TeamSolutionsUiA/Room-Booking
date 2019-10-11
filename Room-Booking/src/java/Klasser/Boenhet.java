/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

/**
 *
 * @author lasse
 */




public class Boenhet {
private int boenhetsnummer;    

   
private int boenhetstypeID;

public void Boenhet(int boenhetsnummer, int BoenhetstypeID) {
    this.boenhetsnummer = boenhetsnummer;
    this.boenhetstypeID =BoenhetstypeID;
    
}
 public int getBoenhetsnummer() {
        return boenhetsnummer;
    }

public void setBoenhetsnummer(int boenhetsnummer) {
        this.boenhetsnummer = boenhetsnummer;
    }

public int getBoenhetstypeID() {
        return boenhetstypeID;
    }

public void setBoenhetstypeID(int boenhetstypeID) {
        this.boenhetstypeID = boenhetstypeID;
    }


}
