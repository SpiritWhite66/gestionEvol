package com.soprasteria.asp.gestionEvol.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.repository.HistoriqueRepository;
import com.soprasteria.asp.gestionEvol.repository.MergeRepository;
import com.soprasteria.asp.gestionEvol.service.ServiceGererMerge;

@Component
@Service
@Transactional
public class ServiceGererMergeImpl implements ServiceGererMerge {

	@Autowired
	MergeRepository repository;
	@Autowired
	HistoriqueRepository historiqueRepository;
	
	@Override
	public Merge save(Merge merge) {
		merge = repository.saveAndFlush(merge);
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		historiqueRepository.save(new Historique(1,merge.getId(), "Non identifié", today));
		return merge;
	}

	@Override
	public ArrayList<Merge> findAllOrderByDateDesc(Boolean all) {
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
		repository.flush();
		long date = new java.util.Date().getTime();
		Date today=new Date(date);
		historiqueRepository.save(new Historique(2,id , "Non identifié", today));
		return true;
	}
	
	
	
	
	
	
}
