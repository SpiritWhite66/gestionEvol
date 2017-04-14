package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Evolution;
import com.soprasteria.asp.gestionEvol.model.Merge;

public interface ServiceGererEvolution {

	public Evolution save(Merge merge);
	public boolean modifierEvolution(int id);

}
