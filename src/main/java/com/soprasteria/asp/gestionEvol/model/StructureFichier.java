package com.soprasteria.asp.gestionEvol.model;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;

public class StructureFichier {
	
	private ArrayList<Evol> listEvol;
	private ArrayList<ErrorException> listError;
	
	public StructureFichier() {
		listError = new ArrayList<>();
		listEvol = new ArrayList<>();
	}
	
	public ArrayList<Evol> getListEvol() {
		return listEvol;
	}
	public void setListEvol(ArrayList<Evol> listEvol) {
		this.listEvol = listEvol;
	}
	public ArrayList<ErrorException> getListError() {
		return listError;
	}
	public void setListError(ArrayList<ErrorException> listError) {
		this.listError = listError;
	}

	
	
}
