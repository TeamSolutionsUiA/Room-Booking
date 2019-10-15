/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bruker;

/**
 *
 * @author Jonathans
 */
public class Bruker {
    
    private int id;
    private String rolle;
    private String fornavn;
    private String etternavn;
    private String fodselsDato; 
    private String epost;
    private String passord;
    private String telefon;
    
    public Bruker(String rolle,String fornavn, String etternavn, String fodselsDato, String epost,String passord,String telefon){
    
    this.rolle = rolle;
    this.fornavn = fornavn;
    this.etternavn = etternavn;
    this.fodselsDato = fodselsDato;
    this.epost = epost;
    this.passord = passord;
    this.telefon = telefon;
}
    
    public Bruker(int id,String fornavn, String etternavn, String fodselsDato, String epost,String telefon){
    
    this.id = id;
    this.fornavn = fornavn;
    this.etternavn = etternavn;
    this.fodselsDato = fodselsDato;
    this.epost = epost;
    this.telefon = telefon;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getFodselsDato() {
        return fodselsDato;
    }

    public void setFodselsDato(String fodselsDato) {
        this.fodselsDato = fodselsDato;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
}