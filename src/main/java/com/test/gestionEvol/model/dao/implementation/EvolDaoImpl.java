package com.test.gestionEvol.model.dao.implementation;



import java.lang.reflect.Array;
import java.util.ArrayList;

import com.test.gestionEvol.model.bean.Appli;
import com.test.gestionEvol.model.bean.Branche;
import com.test.gestionEvol.model.bean.Evol;
import com.test.gestionEvol.model.dao.DAOFactory;
import com.test.gestionEvol.model.dao.EvolDao;
import com.test.gestionEvol.model.dao.exceptions.DAOException;

public class EvolDaoImpl implements EvolDao {
	
	@SuppressWarnings("unused")
	private DAOFactory daoFactory;
	ArrayList<Evol> evolsData;

    public EvolDaoImpl( DAOFactory daoFactory ) {

        this.daoFactory = daoFactory;
	    try {
	    	evolsData = daoFactory.getData();

	    } catch ( Exception e ) {
	    	evolsData = null;
	        throw new DAOException( e );
	    }
    }
    
	public Evol find(String name) throws DAOException {
	    for (Evol evol : evolsData) {
	        if(evol.getName().equals(name))
	        {
	        	System.out.println(evol.getName());
	        	return evol;
	        }
	    }
		return null;
	}

	public ArrayList<String> getNameAllEvol() throws DAOException{

		ArrayList<String> testEvol = new ArrayList<>();
	    for (Evol evol : evolsData) {
		    testEvol.add(evol.getName());
	    }

    	return testEvol;
	}
	public ArrayList<Appli> getBrancheByEvol(String name) throws DAOException {
		
		Evol evol = this.find(name);
	    ArrayList<Appli> testEvol = new ArrayList<>();
	    for (Appli application : evol.getApplication()) {
		    testEvol.add(application);
	    }

    	return testEvol;
	}	
	public ArrayList<Evol> findAll() throws DAOException {
		if(evolsData==null)
		{
		      throw new DAOException("evolsData est vide, aucune évolution");
		}
		else
		{
			return evolsData;
		}
	}

	@Override
	public ArrayList<Branche> findBrancheByApplication(String nameEvol, String nameAppli) throws DAOException {
		Evol evol = this.find(nameEvol);
	    ArrayList<Branche> brancheByEvol = new ArrayList<>();
	    for (Appli application : evol.getApplication()) {
	    	if(application.getName().equals(nameAppli))
	    	{
	    		brancheByEvol.addAll(application.getBranche());
	    	}
	    }

    	return brancheByEvol;
	}

	public ArrayList<Appli> findApplicationForEvols(ArrayList<Evol> evols) throws DAOException {
		ArrayList<Appli> applis = new ArrayList<>();
		if(evolsData==null)
		{
		      throw new DAOException("evolsData est vide, aucune évolution");
		}
		else
		{
			System.out.println("evol : " + evols.size());
			for(Evol evol : evols)
			{
				System.out.println("evol : " + evol.getApplication().size());
				for(Appli appli : evol.getApplication())
				{
					System.out.println("here");
					if(!arrayContient(applis, appli))
					{
						applis.add(appli);
					}
				}
			}
		}
		return applis;
	}
	
	public boolean arrayContient(ArrayList<Appli> applis, Appli appli){
    	System.out.println("-> " + applis.size());
		for(Appli appliTmp : applis)
		{
	    	System.out.println("-> " + appli.getName() + " = " + appliTmp.getName());
			if(appliTmp.getName().equals(appli.getName())){
				return true;
			}
		}
		return false;
	}



}
