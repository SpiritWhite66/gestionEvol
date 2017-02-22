package com.test.gestionEvol.model.bean;
import java.util.ArrayList;

import com.test.gestionEvol.model.dao.exceptions.DAOException;
public class Evol {
	private String name;
	private ArrayList<Appli> application;
	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}
	public ArrayList<Appli> getApplication() {
		return this.application;
	}
	public void setApplication(ArrayList<Appli> application) {
		this.application=application;
	}
	
	public Appli find(String name) throws DAOException {
	    for (Appli appli : application) {
	        if(appli.getName().equals(name))
	        {
	        	System.out.println(appli.getName());
	        	return appli;
	        }
	    }
		return null;
	}
}
