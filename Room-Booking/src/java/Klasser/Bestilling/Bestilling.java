/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import Klasser.Boenhet.Boenhet;
import Klasser.Bruker.Bruker;
import java.util.List;

/**
 *
 * @author mohamJ
 */
public class Bestilling {

    private int bestillingsNummer;
    private String startDato;
    private String sluttDato;
    private int antallPerson;
    private List <Boenhet> boenhet;

   
    public Bestilling(int bestillingsNummer, String startDato, String sluttDato, int brukerId, int antallPerson, List <Boenhet> boenheter) {
        this.bestillingsNummer = bestillingsNummer;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.antallPerson = antallPerson;
        this.boenhet= boenheter;
        
    }

    public Bestilling(String startDato, String sluttDato, int brukerId, int antallPerson) {

        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.antallPerson = antallPerson;
    }

    public Bestilling(String startDato, String sluttDato, int antallPerson) {

        this.startDato = startDato;
        this.sluttDato = sluttDato;

        this.antallPerson = antallPerson;
    }

    public int getbestillingsNummer() {
        return bestillingsNummer;
    }

    public String getStartDato() {
        return startDato;
    }

    public String getSluttDato() {
        return sluttDato;
    }

    public int getAntallPerson() {
        return antallPerson;
    }

    public void setBestillingsNummer(int bestillingsNummer) {
        this.bestillingsNummer = bestillingsNummer;
    }

    public void setStartDato(String startDato) {
        this.startDato = startDato;
    }

    public void setSluttDato(String sluttDato) {
        this.sluttDato = sluttDato;
    }

    public void setAntallPerson(int antallPerson) {
        this.antallPerson = antallPerson;
    }

    
     public List<Boenhet> getBoenhet() {
        return boenhet;
    }

    public void setBoenhet(List<Boenhet> boenhet) {
        this.boenhet = boenhet;
    }

}
