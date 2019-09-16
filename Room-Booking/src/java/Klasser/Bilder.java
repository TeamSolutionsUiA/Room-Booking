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
public class Bilder {
    List<InputStream> bilder;

    public Bilder(List<InputStream> bilder) {
        this.bilder = bilder;
    }

    public List<InputStream> getBilder() {
        return bilder;
    }

    public void setBilder(List<InputStream> bilder) {
        this.bilder = bilder;
    }
}
