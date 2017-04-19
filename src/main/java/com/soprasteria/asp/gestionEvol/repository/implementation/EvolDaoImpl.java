package com.soprasteria.asp.gestionEvol.repository.implementation;



import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.soprasteria.asp.gestionEvol.exceptions.DAOException;
import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.model.Evolution;
import com.soprasteria.asp.gestionEvol.repository.EvolDao;
import com.soprasteria.asp.gestionEvol.service.ServiceGererEvolution;


@Component
public class EvolDaoImpl implements EvolDao {
	
	private String ERROR_MESSAGE_NO_RESULT_BRANCHE = "Erreur dans la recherche, la branche n'existe pas.";
	private String ERROR_MESSAGE_NO_RESULT_DATA = "evolsData est vide, aucune évolution présente.";

	private ArrayList<Evol> evolsData;
	
	@Autowired
	private ServiceGererEvolution evolutionService;
 
	
    
    public void setEvolsData(ArrayList<Evol> evols)
    {
    	this.evolsData=evols;
    }
    /**
	* Effectue une recherche dans les évolutions par nom, dans les données extraite du fichier JSON
    *
    * @param name
    * Nom de l'évolution
    *
    * @return
    * Retourne la première évolution trouvé dont le nom correspond à "name"
    * Renvoie null si aucune évolution ne correspond
    * 
    */
	public Evol find(String name) {
		/*
		 * Retourne la première evolution correspondant à @name (non sensible à la case)
		 */
	    for (Evol evol : evolsData) {
	        if(evol.getName().toUpperCase().equals(name.toUpperCase()))
	        {
	        	return evol;
	        }
	    }
		return null;
	}

	
    /**
	* Liste tout les noms d'évolution (ne traite pas les doublons) présent dans les données extraite du JSON
    *
    * @return
    * Retourne un ArrayList de String correspondant au nom des évolutions.
    * 
    */
	public ArrayList<String> getNameAllEvol(boolean tri){
			Predicate<Evolution> p1;
			ArrayList<Evolution> evolutionReel = null;

			if(tri)
			{
				evolutionReel = evolutionService.findAll();
			}
			ArrayList<String> testEvol = new ArrayList<>();
		    for (Evol evol : evolsData) {
		    	if(tri)
		    	{
					p1 = ev -> evol.getName().contains(ev.getNom());
			        if(evolutionReel.stream().filter(p1).anyMatch(p1))
			        {
			        	testEvol.add(evol.getName());	
			        }
			        
		    	} else {
				    testEvol.add(evol.getName());
		    	}
		    }
		

    	return testEvol;
	}
	
    /**
	* Trouve toute les branches étant lié à l'évolution dont le nom est "name" (on prend la première évolution ayant ce nom).
    * @param name
    * Nom de l'évolution
    *
    * @return
    * Retourne un ArrayList d'Evol.
    * 
    */
	public ArrayList<Evol> findBrancheByEvol(String name) throws DAOException {
		
		ArrayList<Evol> result = new ArrayList<>(); // Liste de résultat
		Evol evolTmp = new Evol(); // Ligne d'évolution Tmp (pour harmoniser le type de retour entre les différentes fonctions de recherche)
		
		Evol evol = this.find(name);
		if(evol!=null)
		{
			evolTmp.setName(evol.getName());

		    ArrayList<Appli> listAppliTmp = new ArrayList<>();
		    
			if(evol != null)
			{
			    for (Appli application : evol.getApplication()) {
			    	listAppliTmp.add(application);
			    }
			}
			if(listAppliTmp.isEmpty())
			{
				return null;
			}
			
			evolTmp.setApplication(listAppliTmp);
			result.add(evolTmp);

			return result;
		}
		else
		{
			return null;
		}

	}	
	public ArrayList<Evol> findAll() throws DAOException {
		if(evolsData==null)
		{
		      throw new DAOException(ERROR_MESSAGE_NO_RESULT_DATA);
		}
		else
		{
			return evolsData;
		}
	}

	@Override
	/***
	 * Recherche des branches d'une application dans une branche donnée.
	 * 
	 */
	public ArrayList<Branche> findBrancheByApplication(Evol evol, String name) throws DAOException {
		
	    ArrayList<Branche> brancheByEvol = new ArrayList<>();
	    for (Appli application : evol.getApplication()) {
	    	if(application.getName().equals(name))
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
			for(Evol evol : evols)
			{
				for(Appli appli : evol.getApplication())
				{
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
		for(Appli appliTmp : applis)
		{
			if(appliTmp.getName().equals(appli.getName())){
				return true;
			}
		}
		return false;
	}
		
	public ArrayList<Evol> findEvolByBranche(String name, String SearchBy) {
		
		Evol evolTmp;
		
		ArrayList<Evol> listEvol = findAll(); // Liste des évolutions existante
		ArrayList<Appli> listAppliTmp = null;
		ArrayList<Appli> listAppliResult = null;
		ArrayList<Evol> result=new ArrayList<>();
		
		if(!name.equals(""))
		{
			for(Evol evol : listEvol)
			{
				evolTmp = new Evol();
				ArrayList<Appli> listAppli = evol.getApplication(); // on récupère les applications de l'évolution
				listAppliResult = new ArrayList<>();
				for(Appli appli : listAppli) // on navigue dans les applications
				{
					if(SearchBy.equals("Application"))
					{
						if(appli.getName().toUpperCase().contains(name.toUpperCase())) // si l'application correspond à celle qu'on cherche on la récupère
						{
							listAppliResult.add(appli);
						}
					}
					else
					{
						listAppliTmp=findByBranche(appli ,name);
						if(listAppliTmp != null)
						{
							listAppliResult.addAll(listAppliTmp);
						}
					}
				}
				if(listAppliResult!=null && !listAppliResult.isEmpty())
				{
					evolTmp.setName(evol.getName());
					evolTmp.setApplication(listAppliResult);
					result.add(evolTmp);
				}
			}
		}
		if(result.isEmpty())
		{
			throw new DAOException(ERROR_MESSAGE_NO_RESULT_BRANCHE);
		}
		return result;
	}
	
	private ArrayList<Appli> findByBranche(Appli appli, String name)
	{
		Appli appliTmp;
		ArrayList<Appli> listAppliTmp =new ArrayList<>();
		ArrayList<Branche> brancheTmp;
		
		appliTmp=new Appli();
		brancheTmp=new ArrayList<>();
		for(Branche branche : appli.getBranche())
		{
			if(branche.getName().toUpperCase().contains(name.toUpperCase())) // si l'application correspond à celle qu'on cherche on la récupère
			{
				brancheTmp.add(branche);
			}
		}
		if(!brancheTmp.isEmpty())
		{
			appliTmp.setName(appli.getName());
			appliTmp.setBranche(brancheTmp);
			listAppliTmp.add(appliTmp);
		}
		return listAppliTmp;
	}






}
