/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bestilling;

/**
 *
 * @author altee
 */
public class Bestilling {
    private int id; 
    private String startDato ;
    private String sluttDato;
    private int antallPerson;

    public Bestilling(int id, String startDato, String sluttDato, int antallPerson) {
        this.id = id;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.antallPerson = antallPerson;
    }
  public Bestilling( String startDato, String sluttDato, int antallPerson) {
        
        this.startDato = startDato;
        this.sluttDato = sluttDato;
        this.antallPerson = antallPerson;
    }
    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
    
    
}
