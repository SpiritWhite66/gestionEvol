package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;
import java.util.function.Predicate;
import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Evol;



public class TriSVN {
	
	private ArrayList<Evol> listEvol;
	
	public TriSVN(ArrayList<Evol> listEvol)
	{
		this.listEvol=listEvol;
	}
	
	
	/**
	* Va retourner une liste d'application trier pour respecter la hierarchie SVN, c'est-à-dire :
	* Application 1
	*	|-> Branche 1
	*	|-> Branche 2 
	*	|-> ...
	* Application 2 
	* 	|-> ...
	* 
	*
	* @return
	* Retourne une liste d'Application.
	*/
	public ArrayList<Appli> triParApplication()
	{
		Predicate<Appli> p1;
		ArrayList<Appli> listResult = new ArrayList<>();
		int i;
		for(Evol evol : listEvol)
		{
			for(Appli appli : evol.getApplication())
			{	
				p1 = app -> app.getName().equals(appli.getName());
		        if(listResult.stream().filter(p1).anyMatch(p1))
		        {
		        	System.out.println("true");
		        	i = indexOfAppli(appli.getName(), listResult);
		        	listResult.get(i).getBranche().addAll(appli.getBranche());
		        }
		        else
		        {
		        	System.out.println("false");
		        	System.out.println(appli.getName());
		        	listResult.add(appli);
		        }
			}
		}
		return listResult;
	}
	
	
	
	/**
	* Recherche la position d'un objet Appli ayant comme attribut name la valeur name passé en paramètre
	*
	* @param name
	* Nom d'application à chercher, obligatoire.
	* @param listResult
	* Liste d'application
	*
	* @return
	* Retourne la position du premier objet Appli ayant le name correspondant au name passé en paramètre. 
	* Retourne -1 si aucun objet ne correspond.
	*
	* @throws NullPointerException 
	* Si listResult est null
	*/
	private int indexOfAppli(String name, ArrayList<Appli>listResult)
	{
		int i = 0;
		for( Appli appli : listResult)
		{
			if(appli.getName().equals(name))
			{
				return i;
			}
			i++;
		}
		return -1;
	}
}
