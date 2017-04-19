package com.soprasteria.asp.gestionEvol.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.model.RenommeBranche;
import com.soprasteria.asp.gestionEvol.model.SupprimeBranche;
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
	
	@Override
	public Merge save(Merge merge) {
		merge = repository.save(merge);
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(1);
		historiqueRepository.save(new Historique(TYPE_OBJET_MERGE, typeaction,merge.getId(), "Non identifié", today));
		return merge;
	}

	@Override
	public ArrayList<Merge> findAllMergeOrderByDateDesc(Boolean all) {
		if(all!=null && all)
		{
			return (ArrayList<Merge>) repository.findAllByOrderByDateDesc();
		}
		else
		{
			return (ArrayList<Merge>) repository.findAllByFaitOrderByDateDesc(all);
		}
	}
	
	@Override
	public boolean modifierMergeFait(int id) {
		Merge merge = repository.findById(id);
		repository.setFaitForMerge(id, !merge.isFait());
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(determineAction(merge.isFait()));
		historiqueRepository.save(new Historique(TYPE_OBJET_MERGE, typeaction,merge.getId(), "Non identifié", today));
		return true;
	}


	
	@Override
	public RenommeBranche save(RenommeBranche branche) {
		
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(1);
		historiqueRepository.save(new Historique(TYPE_OBJET_RENOMME,typeaction,branche.getId(), "Non identifié", today));
		
		return renommeBrancheRepository.save(branche);
	}

	@Override
	public ArrayList<RenommeBranche> findAllRenommeOrderByDateDesc() {
		return renommeBrancheRepository.findAllByOrderByDateDesc();
	}

	
	
	@Override
	public SupprimeBranche save(SupprimeBranche branche) {
		
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(1);
		historiqueRepository.save(new Historique(TYPE_OBJET_SUPPRIME,typeaction,branche.getId(), "Non identifié", today));
		
		return supprimeBrancheRepository.save(branche);
	}

	@Override
	public ArrayList<SupprimeBranche> findAllSupprimeOrderByDateDesc() {
		return supprimeBrancheRepository.findAllByOrderByDateDesc();
	}

	@Override
	public boolean modifierRenommeFait(int id) {
		RenommeBranche renommeBranche = renommeBrancheRepository.findById(id);
		repository.setFaitForMerge(id, !renommeBranche.isFait());
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		int type=2;

		TypeAction typeaction = repositoryTypeAction.findById(determineAction(renommeBranche.isFait()));
		historiqueRepository.save(new Historique(TYPE_OBJET_RENOMME,typeaction,id, "Non identifié", today));
		return true;
	}

	@Override
	public boolean modifierSupprimeFait(int id) {
		SupprimeBranche supprimeBranche = supprimeBrancheRepository.findById(id);
		repository.setFaitForMerge(id, !supprimeBranche.isFait());
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		TypeAction typeaction = repositoryTypeAction.findById(determineAction(supprimeBranche.isFait()));
		historiqueRepository.save(new Historique(TYPE_OBJET_SUPPRIME,typeaction,id, "Non identifié", today));
		return true;
	}
	
	public int determineAction(boolean fait)
	{
		if(fait)
		{
			return PASSAGE_NON_FAIT;
		}else{
			return PASSAGE_FAIT;
		}
	}
	
	
	
	
}
