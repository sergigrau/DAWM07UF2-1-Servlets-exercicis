
package edu.fje.daw2;

import java.io.Serializable;

/**
 * Classe que representa a un Producte
 * @author sergi grau
 * @version 2.0 7/11/2013
 */
public class Producte implements Serializable{

    private String nom;
    private int unitats;

    public Producte() {
    }
    
    public Producte(String producte, int unitats) {
	nom=producte;
	this.unitats=unitats;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getUnitats() {
        return unitats;
    }

    public void setUnitats(int unitats) {
        this.unitats = unitats;
    }
    
}
