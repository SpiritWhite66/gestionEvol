package com.soprasteria.asp.gestionEvol.service.impl;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.model.ActionMerge;
import com.soprasteria.asp.gestionEvol.model.ActionRenommeBranche;
import com.soprasteria.asp.gestionEvol.model.ActionSupprimeBranche;
import com.soprasteria.asp.gestionEvol.model.TypeAction;
import com.soprasteria.asp.gestionEvol.repository.HistoriqueRepository;
import com.soprasteria.asp.gestionEvol.repository.MergeRepository;
import com.soprasteria.asp.gestionEvol.repository.RenommeBrancheRepository;
import com.soprasteria.asp.gestionEvol.repository.SupprimeBrancheRepository;
import com.soprasteria.asp.gestionEvol.repository.TypeActionRepository;
import com.soprasteria.asp.gestionEvol.service.ServiceGererBranche;

@Component
@Service
@Transactional
public class ServiceGererBrancheImpl implements ServiceGererBranche {

	private int PASSAGE_FAIT = 2;
	private int PASSAGE_NON_FAIT= 3;
	private int CREATION_MERGE = 1;
	private int CREATION_SUPPRIME = 6;
	private int CREATION_RENOMME = 7;

	private char TYPE_OBJET_MERGE='M';
	private char TYPE_OBJET_RENOMME='R';
	private char TYPE_OBJET_SUPPRIME='S';

	
	@Autowired
	MergeRepository repository;
	
	@Autowired
	TypeActionRepository repositoryTypeAction;
	
	@Autowired
	HistoriqueRepository historiqueRepository;
	
	@Autowired
	RenommeBrancheRepository renommeBrancheRepository;
	
	@Autowired
	SupprimeBrancheRepository supprimeBrancheRepository;
	
	/***************************
	 * ACTION : MERGE
	 ***************************/
	@Override
	public ActionMerge save(ActionMerge merge) {
		merge = repository.save(merge);
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(CREATION_MERGE);
		historiqueRepository.save(new Historique(TYPE_OBJET_MERGE, typeaction,merge.getId(), "Non identifié", today));
		return merge;
	}

	@Override
	public ArrayList<ActionMerge> findAllMergeOrderByDateDesc(Boolean all) {
		if(all!=null && all)
		{
			return (ArrayList<ActionMerge>) repository.findAllByOrderByDateDesc();
		}
		else
		{
			return (ArrayList<ActionMerge>) repository.findAllByFaitOrderByDateDesc(all);
		}
	}
	
	@Override
	public boolean modifierMergeFait(int id) {
		ActionMerge merge = repository.findById(id);
		repository.setFaitForMerge(id, !merge.isFait());
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(determineAction(merge.isFait()));
		historiqueRepository.save(new Historique(TYPE_OBJET_MERGE, typeaction,merge.getId(), "Non identifié", today));
		return true;
	}

	/***************************
	 * ACTION : RENOMME BRANCHE
	 ***************************/
	
	@Override
	public ActionRenommeBranche save(ActionRenommeBranche branche) {
		
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(CREATION_RENOMME);
		historiqueRepository.save(new Historique(TYPE_OBJET_RENOMME,typeaction,branche.getId(), "Non identifié", today));
		
		return renommeBrancheRepository.save(branche);
	}

	@Override
	public ArrayList<ActionRenommeBranche> findAllRenommeOrderByDateDesc() {
		return renommeBrancheRepository.findAllByOrderByDateDesc();
	}

	@Override
	public boolean modifierRenommeFait(int id) {
		ActionRenommeBranche renommeBranche = renommeBrancheRepository.findById(id);
		renommeBrancheRepository.setFaitForRenomme(id, !renommeBranche.isFait());
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		int type=2;

		TypeAction typeaction = repositoryTypeAction.findById(determineAction(renommeBranche.isFait()));
		historiqueRepository.save(new Historique(TYPE_OBJET_RENOMME,typeaction,id, "Non identifié", today));
		return true;
	}
	
	@Override
	public ArrayList<ActionRenommeBranche> findByListRenommeBranche(ArrayList<Branche> listBranche)
	{
		ArrayList<String> branches = (ArrayList<String>) listBranche.stream().map(Branche::getName).collect(Collectors.toList());
		return renommeBrancheRepository.findByListBranche(branches);
	}
	
	/***************************
	 * ACTION : SUPPRIME BRANCHE
	 ***************************/
	
	@Override
	public ActionSupprimeBranche save(ActionSupprimeBranche branche) {
		
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(CREATION_SUPPRIME);
		historiqueRepository.save(new Historique(TYPE_OBJET_SUPPRIME,typeaction,branche.getId(), "Non identifié", today));
		
		return supprimeBrancheRepository.save(branche);
	}

	@Override
	public ArrayList<ActionSupprimeBranche> findAllSupprimeOrderByDateDesc() {
		return supprimeBrancheRepository.findAllByOrderByDateDesc();
	}

	@Override
	public ArrayList<ActionSupprimeBranche> findByListSupprimeBranche(ArrayList<Branche> listBranche)
	{
		ArrayList<String> branches = (ArrayList<String>) listBranche.stream().map(Branche::getName).collect(Collectors.toList());
		return supprimeBrancheRepository.findByListBranche(branches);
	}

	@Override
	public boolean modifierSupprimeFait(int id) {
		ActionSupprimeBranche supprimeBranche = supprimeBrancheRepository.findById(id);
		supprimeBrancheRepository.setFaitForSupprime(id, !supprimeBranche.isFait());
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(determineAction(supprimeBranche.isFait()));
		historiqueRepository.save(new Historique(TYPE_OBJET_SUPPRIME,typeaction,id, "Non identifié", today));
		return true;
	}
	
	private int determineAction(boolean fait)
	{
		if(fait)
		{
			return PASSAGE_NON_FAIT;
		}else{
			return PASSAGE_FAIT;
		}
	}


	
	
	
	
}
