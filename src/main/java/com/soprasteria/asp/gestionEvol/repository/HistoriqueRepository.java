package com.soprasteria.asp.gestionEvol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.soprasteria.asp.gestionEvol.model.Historique;

@Transactional
@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long>{

}
