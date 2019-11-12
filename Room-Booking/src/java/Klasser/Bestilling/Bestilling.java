/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import Klasser.Boenhet.Boenhet;
import java.util.List;

/**
 *
 * @author mohamJ
 */
public class Bestilling {

    private int bestillingsNummer;
    private int brukerID;
    private String startDato;
    private String sluttDato;
    private List<Boenhet> boenheter;

    public Bestilling(int bestillingsNummer, String startDato, String sluttDato, int brukerID, List<Boenhet> boenheter) {
        this.bestillingsNummer = bestillingsNummer;
        this.brukerID = brukerID;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.boenheter = boenheter;
    }

    public Bestilling(String startDato, String sluttDato, int brukerID, List<Boenhet> boenheter) {
        this.brukerID = brukerID;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.boenheter = boenheter;
    }

    public int getBestillingsNummer() {
        return bestillingsNummer;
    }

    public void setBestillingsNummer(int bestillingsNummer) {
        this.bestillingsNummer = bestillingsNummer;
    }

    public int getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(int brukerID) {
        this.brukerID = brukerID;
    }

    public String getStartDato() {
        return startDato;
    }

    public void setStartDato(String startDato) {
        this.startDato = startDato;
    }

    public String getSluttDato() {
        return sluttDato;
    }

    public void setSluttDato(String sluttDato) {
        this.sluttDato = sluttDato;
    }

    public List<Boenhet> getBoenheter() {
        return boenheter;
    }

    public void setBoenheter(List<Boenhet> boenheter) {
        this.boenheter = boenheter;
    }
}
