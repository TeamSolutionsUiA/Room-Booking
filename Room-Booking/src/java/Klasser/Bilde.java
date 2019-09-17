/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author arefj
 */
public class Bilde {
    InputStream bilde;

    public Bilde(InputStream bilde) {
        this.bilde = bilde;
    }

    public InputStream getBilde() {
        return bilde;
    }

    public void setBilde(InputStream bilde) {
        this.bilde = bilde;
    }
}
