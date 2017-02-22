package com.test.gestionEvol.model.dao;

import java.util.ArrayList;

import com.test.gestionEvol.model.bean.Appli;
import com.test.gestionEvol.model.bean.Branche;
import com.test.gestionEvol.model.bean.Evol;
import com.test.gestionEvol.model.dao.exceptions.DAOException;

public interface EvolDao {
	
	public Evol find(String name) throws DAOException;
	public ArrayList<Branche> findBrancheByApplication(String nameEvol, String nameAppli) throws DAOException;
	public ArrayList<String> getNameAllEvol() throws DAOException;
	public ArrayList<Evol> findAll() throws DAOException;
	public ArrayList<Appli> getBrancheByEvol(String name) throws DAOException;
	public ArrayList<Appli> findApplicationForEvols(ArrayList<Evol> evols);

}
