package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.model.RenommeBranche;
import com.soprasteria.asp.gestionEvol.model.SupprimeBranche;

public interface ServiceGererBranche {
	
	/* Action Merge */
	public Merge save(Merge merge);
	public ArrayList<Merge> findAllMergeOrderByDateDesc(Boolean all);
	public boolean modifierMergeFait(int id);
	
	/* Action Renomme */
	public 	RenommeBranche save(RenommeBranche merge);
	public ArrayList<RenommeBranche> findAllRenommeOrderByDateDesc();

	
	/* Action Supprime */ 
	public 	SupprimeBranche save(SupprimeBranche merge);
	public ArrayList<SupprimeBranche> findAllSupprimeOrderByDateDesc();
	public boolean modifierRenommeFait(int id);
	public boolean modifierSupprimeFait(int id);

}
