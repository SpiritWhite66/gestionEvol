package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.ActionMerge;
import com.soprasteria.asp.gestionEvol.model.ActionRenommeBranche;
import com.soprasteria.asp.gestionEvol.model.ActionSupprimeBranche;

public interface ServiceGererBranche {
	
	/* Action Merge */
	public ActionMerge save(ActionMerge merge);
	public ArrayList<ActionMerge> findAllMergeOrderByDateDesc(Boolean all);
	public boolean modifierMergeFait(int id);
	
	/* Action Renomme */
	public ActionRenommeBranche save(ActionRenommeBranche merge);
	public ArrayList<ActionRenommeBranche> findAllRenommeOrderByDateDesc();
	public ArrayList<ActionRenommeBranche> findByListRenommeBranche(ArrayList<Branche> listBranche);
	
	/* Action Supprime */ 
	public 	ActionSupprimeBranche save(ActionSupprimeBranche merge);
	public ArrayList<ActionSupprimeBranche> findAllSupprimeOrderByDateDesc();
	public ArrayList<ActionSupprimeBranche> findByListSupprimeBranche(ArrayList<Branche> listBranche);
	public boolean modifierRenommeFait(int id);
	public boolean modifierSupprimeFait(int id);

}
