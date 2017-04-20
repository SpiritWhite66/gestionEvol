package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Evolution;
import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.model.ActionMerge;

public interface ServiceGererEvolution {

	public ArrayList<Evolution> findAll();
	public Evolution save(Evolution evolution);
	public boolean modifierEvolution(int id);
	public int remove(int id);
}
