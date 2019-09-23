/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.InputStream;

/**
 *
 * @author arefj
 */
public class Bilde {
    InputStream bilde;
    int ID;

    public Bilde(InputStream bilde) {
        this.bilde = bilde;
    }

    public Bilde(InputStream bilde, int ID) {
        this.bilde = bilde;
        this.ID = ID;
    }

    public InputStream getBilde() {
        return bilde;
    }

    public void setBilde(InputStream bilde) {
        this.bilde = bilde;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
}
