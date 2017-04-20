package com.soprasteria.asp.gestionEvol.model;

import java.text.DateFormat;
import java.util.Date;

public class PatchBDD {

	private String name;
	private String application;
	private String date;
	private String auteur;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getDate() {
		return date;		
	}
	public void setDate(String date) {
		this.date=date;
		//Integer timestamp = Integer.parseInt(date);
		//int test = timestamp.intValue();
		//System.out.println(Integer.parseInt(date));
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	
}
