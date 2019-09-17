/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

/**
 *
 * @author arefj
 */
public class Egenskap {
    String egenskap;

    public Egenskap(String egenskap) {
        this.egenskap = egenskap.trim();
    }
    
    public String getEgenskap() {
        return egenskap;
    }

    public void setEgenskap(String egenskap) {
        this.egenskap = egenskap;
    }
}
