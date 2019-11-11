/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

import Klasser.Bruker.Bruker;

/**
 *
 * @author mohamJ
 */
public class Bestilling {

    private int bestillingsNummer;
    private String startDato;
    private String sluttDato;
    private int brukerID;
    private int antallPerson;

    public Bestilling(int bestillingsNummer, String startDato, String sluttDato, int brukerId, int antallPerson) {
        this.bestillingsNummer = bestillingsNummer;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.brukerID = brukerId;
        this.antallPerson = antallPerson;
    }

    public Bestilling(String startDato, String sluttDato, int brukerId, int antallPerson) {

        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.brukerID = brukerId;
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

    public int getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(int brukerID) {
        this.brukerID = brukerID;
    }

}
