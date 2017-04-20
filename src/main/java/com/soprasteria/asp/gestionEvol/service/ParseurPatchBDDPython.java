package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.exceptions.DAOConfigurationException;
import com.soprasteria.asp.gestionEvol.model.StructureFichier;

public interface ParseurPatchBDDPython {
	
    /**
	* Retourne une liste d'EvolUTION correspondant aux évolutions présentes dans les dépôt SVN.
	* </br>
    *
    * @param name
    * nom du fichier JSON contenant les Log d'erreurs du script python.
    *
    * @return
    * Une Liste d'Evol
    *
    * @throws DAOConfigurationException 
    * Si une erreur de lecture survient.
    */
    public StructureFichier getData(String name); 
	public ArrayList<String> getAllFileRepo();

}
