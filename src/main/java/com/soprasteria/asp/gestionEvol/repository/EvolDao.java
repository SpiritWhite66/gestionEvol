package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.Evol;



public interface EvolDao {
	
	public void setEvolsData(ArrayList<Evol> evols);
	public Evol find(String name);
	public ArrayList<Branche> findBrancheByApplication(Evol evol, String name);
	public ArrayList<String> getNameAllEvol();
	public ArrayList<Evol> findAll();
	public ArrayList<Appli> findApplicationForEvols(ArrayList<Evol> evols);
	
	// Fonction pour les recherches | renvoie normalisé liste d'evol
	public ArrayList<Evol> findBrancheByEvol(String name);
	public ArrayList<Evol> findEvolByBranche(String name, String SearchBy);
	
}
