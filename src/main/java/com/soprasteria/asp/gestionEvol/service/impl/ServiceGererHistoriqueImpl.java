package com.soprasteria.asp.gestionEvol.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.repository.HistoriqueRepository;
import com.soprasteria.asp.gestionEvol.service.ServiceGererHistorique;

@Component
@Service
@Transactional
public class ServiceGererHistoriqueImpl implements ServiceGererHistorique {

	@Autowired
	HistoriqueRepository repository;	


	@Override
	public ArrayList<Historique> findAll() {
		return (ArrayList<Historique>) repository.findAll();
	}
	

}
