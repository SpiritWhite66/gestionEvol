package com.soprasteria.asp.gestionEvol.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soprasteria.asp.gestionEvol.model.Evolution;
import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.model.TypeAction;
import com.soprasteria.asp.gestionEvol.repository.EvolutionRepository;
import com.soprasteria.asp.gestionEvol.repository.HistoriqueRepository;
import com.soprasteria.asp.gestionEvol.repository.MergeRepository;
import com.soprasteria.asp.gestionEvol.repository.TypeActionRepository;
import com.soprasteria.asp.gestionEvol.service.ServiceGererEvolution;
import com.soprasteria.asp.gestionEvol.service.ServiceGererBranche;

@Component
@Service
@Transactional
public class ServiceGererEvolutionImpl implements ServiceGererEvolution {

	@Autowired
	EvolutionRepository evolutionRepository;
	
	@Override
	public Evolution save(Evolution evolution) {
		evolution = evolutionRepository.save(evolution);
		return evolution;
	}

	@Override
	public boolean modifierEvolution(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Evolution> findAll() {
		return (ArrayList<Evolution>) evolutionRepository.findAll();
	}

	@Override
	public int remove(int id) {
		return evolutionRepository.removeById(id);
	}
	
	
}
