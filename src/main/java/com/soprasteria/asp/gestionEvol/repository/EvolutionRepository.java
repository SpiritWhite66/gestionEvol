package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.soprasteria.asp.gestionEvol.model.Evolution;
import com.soprasteria.asp.gestionEvol.model.Merge;

@Transactional
@Repository
public interface EvolutionRepository extends JpaRepository<Evolution, Long>{

	Evolution findById(int id);
}
