/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import java.io.InputStream;

/**
 *
 * @author arefj
 */
public class Bilde {

    private InputStream bilde;
    private String hash;

    public Bilde(InputStream bilde) {
        this.bilde = bilde;
    }

    public Bilde(InputStream bilde, String hash) {
        this.bilde = bilde;
        this.hash = hash;
    }

    public InputStream getBilde() {
        return bilde;
    }

    public void setBilde(InputStream bilde) {
        this.bilde = bilde;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
