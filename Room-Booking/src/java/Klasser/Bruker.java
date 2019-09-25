/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.util.Date;

/**
 *
 * @author Jonathans
 */
public class Bruker {
    
    private int id;
    private String rolle;
    private String navn;
    private Date fodselsDato; 
    private String epost;
    private String passord;
    private int telefon;
    
    public Bruker(String rolle,String navn,Date fodselsDato, String epost,String passord,int telefon){
    
    this.id = id;
    this.rolle = rolle;
    this.navn = navn;
    this.fodselsDato = fodselsDato;
    this.epost = epost;
    this.passord = passord;
    this.telefon = telefon;
}
    
    public Bruker(int id, String rolle,String navn,Date fodselsDato, String epost,String passord,int telefon){
    
    this.id = id;
    this.rolle = rolle;
    this.navn = navn;
    this.fodselsDato = fodselsDato;
    this.epost = epost;
    this.passord = passord;
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

    public String getNavn() {
        return navn;
    }
    public Date getFodselsDato() {
        return fodselsDato;
    }

    public void setFodselsDato(Date fodselsDato) {
        this.fodselsDato = fodselsDato;
    }

    public void setNavn(String navn) {
        this.navn = navn;
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

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

}