package com.soprasteria.asp.gestionEvol.model;

import java.util.ArrayList;

public class ReponseEvolution {

	private ArrayList<Evol> evolutions;
	private ArrayList<Branche> branches;
	private ArrayList<Appli> applications;

	private ArrayList<ArrayList<ActionRenommeBranche>> listActionRenomme;
	private ArrayList<ArrayList<ActionSupprimeBranche>> listActionSupprime;	

	private ArrayList<String> listError;
	private boolean error;
	
	
	public ReponseEvolution() {
		listActionRenomme = new ArrayList<>();
		listActionSupprime = new ArrayList<>();
	}

	public ReponseEvolution(ArrayList<Evol> evolutions, ArrayList<ArrayList<ActionSupprimeBranche>> listActionSupprime, ArrayList<ArrayList<ActionRenommeBranche>> listActionRenomme, ArrayList<String> listError) {
		super();
		this.evolutions = evolutions;
		this.listActionRenomme = listActionRenomme;
		this.listActionSupprime = listActionSupprime;

		this.listError = listError;
		if(listError != null && !listError.isEmpty())
		{
			error=true;
		}
	}
	
	public ArrayList<String> getListError() {
		return listError;
	}
	public void setListError(ArrayList<String> listError) {
		this.listError = listError;
		if(listError != null && !listError.isEmpty())
		{
			error=true;
		}
	}
	public ArrayList<Evol> getEvolutions() {
		return evolutions;
	}
	public void setEvolutions(ArrayList<Evol> evolutions) {
		this.evolutions = evolutions;
	}

	public ArrayList<ArrayList<ActionRenommeBranche>> getListActionRenomme() {
		return listActionRenomme;
	}

	public void setListActionRenomme(ArrayList<ArrayList<ActionRenommeBranche>>listActionRenomme) {
		this.listActionRenomme = listActionRenomme;
	}
	
	public void addListActionRenomme(ArrayList<ActionRenommeBranche> listActionRenomme ) {
		this.listActionRenomme.add(listActionRenomme);
	}
	
	public ArrayList<ArrayList<ActionSupprimeBranche>> getListActionSupprime() {
		return listActionSupprime;
	}

	public void setListActionSupprime(ArrayList<ArrayList<ActionSupprimeBranche>> listActionSupprime) {
		this.listActionSupprime = listActionSupprime;
	}
	public void addListActionSupprime(ArrayList<ActionSupprimeBranche> listActionSupprime) {
		this.listActionSupprime.add(listActionSupprime);
	}
	public ArrayList<Branche> getBranches() {
		return branches;
	}

	public void setBranches(ArrayList<Branche> branches) {
		this.branches = branches;
	}

	public ArrayList<Appli> getApplications() {
		return applications;
	}

	public void setApplications(ArrayList<Appli> applications) {
		this.applications = applications;
	}

	
}
