package com.soprasteria.asp.gestionEvol.model;

import java.util.ArrayList;

public class Appli {
	private String name;
	private ArrayList<Branche> branche;
	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}
	public ArrayList<Branche> getBranche() {
		return this.branche;
	}
	public void setBranche(ArrayList<Branche> branche) {
		this.branche=branche;
	}

}
