package edu.fje.daw2;

import java.io.Serializable;

/**
 * Bean que representa una Persona
 * 
 * @author sergi grau
 * @version 1.0 31.10.2013
 * 
 */
public class Persona implements Serializable{
	private String nom = "";
	private int numFills;
	private boolean casat = false;
	
	public Persona() {

	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumFills() {
		return numFills;
	}

	public void setNumFills(int numFills) {
		this.numFills = numFills;
	}

	public boolean isCasat() {
		return casat;
	}

	public void setCasat(boolean casat) {
		this.casat = casat;
	}

	public boolean isComplert() {
		return (teValor(getNom()) && getNumFills() >= 0);
	}

	public boolean isParcialmentComplert() {
		return teValor(getNom()) || getNumFills() >= 0;
	}

	private boolean teValor(String val) {
		return ((val != null) && (!val.equals("")));
	}
	
}
