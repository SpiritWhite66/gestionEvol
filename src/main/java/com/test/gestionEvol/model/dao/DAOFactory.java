package com.test.gestionEvol.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.gestionEvol.model.bean.Evol;
import com.test.gestionEvol.model.dao.exceptions.DAOConfigurationException;
import com.test.gestionEvol.model.dao.implementation.EvolDaoImpl;

public class DAOFactory {

	static File filejson;
	static String nameFile;
	
	DAOFactory( String nameFile) {
		DAOFactory.nameFile=nameFile;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException 
    {
    	nameFile = "file:///home/vincent/Documents/test.json";
    	filejson = new File(nameFile);
		return new DAOFactory(nameFile);
    }
    public static void setInstance(String nameFile) throws DAOConfigurationException 
    {
    	try {
			filejson = new File(nameFile);
		} catch (Exception e) {
			System.out.println("erreur filejson null");
		}
    }


    public ArrayList<Evol> getData() throws DAOConfigurationException 
    {
    	ObjectMapper mapper = new ObjectMapper();
    	ArrayList<Evol> data = new ArrayList<>();
    	
    	try { 
			String pathFile="D:\\Profiles\\vguillamot\\Documents\\WorkSpace_Projet\\Repo\\Osiris_wrk.json";
    		BufferedReader br = new BufferedReader( new FileReader(pathFile)); 
    		data = mapper.readValue(br, new TypeReference<ArrayList<Evol>>(){});
        } catch (FileNotFoundException e) {
        	System.out.println("erreur filejson ouverture data");
        } catch (Exception e) {
        	System.out.println("erreur filejson récupération data");
        }
    	return data;
    }
    
    public EvolDao getUtilisateurDao() {
        return new EvolDaoImpl(this);
    }
    
}


