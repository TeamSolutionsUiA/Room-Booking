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
 * @author Are
 */
public class BoenhetsType {
    private String navn;
    private String kategori;
    private String enkeltsenger;
    private String dobeltsenger;
    private String beskrivelse;
    private String pris;
    private List<Bilde> bilder;
    private List<Egenskap> egenskaper;

    public BoenhetsType(String navn, String kategori, String enkeltsenger, String dobeltsenger, String beskrivelse, String pris, String egenskaper, List<InputStream> bilder) {
        this.navn = navn;
        this.kategori = kategori;
        this.enkeltsenger = enkeltsenger;
        this.dobeltsenger = dobeltsenger;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        
        String[] egenskaperArr;
        egenskaperArr = egenskaper.split(",");
        for (String egenskap : egenskaper.split(",")){
            Egenskap nyEgenskap = new Egenskap(egenskap);
            this.egenskaper.add(nyEgenskap);
        }
         
        for (InputStream bilde : bilder){
            Bilde nyttBilde = new Bilde(bilde);
            this.bilder.add(nyttBilde);
        }
    }
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getEnkeltsenger() {
        return enkeltsenger;
    }

    public void setEnkeltsenger(String enkeltsenger) {
        this.enkeltsenger = enkeltsenger;
    }

    public String getDobeltsenger() {
        return dobeltsenger;
    }

    public void setDobeltsenger(String dobeltsenger) {
        this.dobeltsenger = dobeltsenger;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
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
