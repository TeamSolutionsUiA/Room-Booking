/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import java.io.IOException;
import java.io.InputStream;
import sun.misc.IOUtils;

/**
 *
 * @author arefj
 */
public class Bilde {

    private byte[] bilde;
    private String hash;

    public Bilde(InputStream bildeStream) {
        try {
            byte[] bilde = IOUtils.readFully(bildeStream, -1, false);
            this.bilde = bilde;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bilde(byte[] bilde) {
        this.bilde = bilde;
    }

    public Bilde(byte[] bilde, String hash) {
        this.bilde = bilde;
        this.hash = hash;
    }

    public byte[] getBilde() {
        return bilde;
    }

    public void setBilde(byte[] bilde) {
        this.bilde = bilde;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
