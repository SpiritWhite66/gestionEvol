package com.soprasteria.asp.gestionEvol.model;

import java.util.ArrayList;

public class LigneCompareAppli {

		/*
		 *Bean représentant une ligne de tableau contenant les numéros de version pour chaque ligne d'évolution 
		 */
		private ArrayList<Branche> branche;
		private Appli appli;
		
		public ArrayList<Branche> getBranche()
		{
			return branche;	
		}
		public Appli getAppli()
		{
			return appli;	
		}

		public LigneCompareAppli(ArrayList<Evol> evolList, Appli appli){
			this.appli=appli;
			branche = new ArrayList<>();  // de la taille de "evol"
			Appli application;
			String brancheVersionString = "";
			Branche brancheTmp = null;
			for(Evol evol : evolList)
			{
				brancheVersionString="";
				brancheTmp = new Branche();
				brancheTmp.setVersion("Aucune Version");
				brancheTmp.setName("Aucune Version");
				
				application = evol.find(appli.getName()); // on cherche la présence de l'application dans l'évolution sinon on dit qu'il n'existe pas de version
				
				if(application != null)
				{
					if(application.getBranche().size()>1) // Uniquement si c'est une "vrai" application ayant des branches
					{
						for(Branche branche : application.getBranche()) // on concatène les différentes version
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
						branche.add(brancheTmp);
				}
			}
		}
		
}