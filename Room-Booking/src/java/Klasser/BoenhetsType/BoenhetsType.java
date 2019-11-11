/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.BoenhetsType;

import java.util.List;

/**
 *
 * @author Are
 */
public class BoenhetsType {

    private int ID;
    private String navn;
    private Kategori kategori;
    private int enkeltsenger;
    private int dobeltsenger;
    private String beskrivelse;
    private int pris;
    private List<Bilde> bilder;
    private List<Egenskap> egenskaper;

    public BoenhetsType(String navn, Kategori kategori, int enkeltsenger, int dobeltsenger, String beskrivelse, int pris, List<Bilde> bilder, List<Egenskap> egenskaper) {
        this.navn = navn;
        this.kategori = kategori;
        this.enkeltsenger = enkeltsenger;
        this.dobeltsenger = dobeltsenger;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.bilder = bilder;
        this.egenskaper = egenskaper;
    }

    public BoenhetsType(int ID, String navn, Kategori kategori, int enkeltsenger, int dobeltsenger, String beskrivelse, int pris, List<Bilde> bilder, List<Egenskap> egenskaper) {
        this.ID = ID;
        this.navn = navn;
        this.kategori = kategori;
        this.enkeltsenger = enkeltsenger;
        this.dobeltsenger = dobeltsenger;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.bilder = bilder;
        this.egenskaper = egenskaper;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public int getEnkeltsenger() {
        return enkeltsenger;
    }

    public void setEnkeltsenger(int enkeltsenger) {
        this.enkeltsenger = enkeltsenger;
    }

    public int getDobeltsenger() {
        return dobeltsenger;
    }

    public void setDobeltsenger(int dobeltsenger) {
        this.dobeltsenger = dobeltsenger;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public List<Bilde> getBilder() {
        return bilder;
    }

    public void setBilder(List<Bilde> bilder) {
        this.bilder = bilder;
    }

    public List<Egenskap> getEgenskaper() {
        return egenskaper;
    }

    public void setEgenskaper(List<Egenskap> egenskaper) {
        this.egenskaper = egenskaper;
    }
}
