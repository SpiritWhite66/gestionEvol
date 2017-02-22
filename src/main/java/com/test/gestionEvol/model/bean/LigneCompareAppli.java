package com.test.gestionEvol.model.bean;

import java.util.ArrayList;

import com.test.gestionEvol.model.dao.DAOFactory;
import com.test.gestionEvol.model.dao.EvolDao;

public class LigneCompareAppli {

		/*
		 *Bean représentant une ligne de tableau contenant les numéros de version pour chaque ligne d'évolution 
		 */
		private DAOFactory daoFactory;
		private ArrayList<Branche> branche;
		private Appli appli;
		
		public ArrayList<Branche> getBranche()
		{
			return branche;	
		}

		public LigneCompareAppli(ArrayList<Evol> evolList, Appli appli){
			branche = new ArrayList<>();  // de la taille de "evol"
			Appli application;
			String brancheVersionString = "";
			Branche brancheTmp = new Branche();
			System.out.println("######################");
			System.out.println(evolList);
			for(Evol evol : evolList)
			{
				application = evol.find(appli.getName());
				if(application != null)
				{
					if(application.getBranche().size()>1)
					{
						for(Branche branche : application.getBranche())
						{
							brancheVersionString += branche.getVersion() + "\n";
						}
						brancheTmp.setVersion(brancheVersionString);
						brancheTmp.setName(brancheVersionString);
						branche.add(brancheTmp);
					}else{
						branche.add(application.getBranche().get(0));
					}
				}else{
						brancheTmp.setVersion("Aucune Version");
						brancheTmp.setName("Aucune Version");
						branche.add(brancheTmp);
				}
			}
			this.branche = branche;
		}
		
}