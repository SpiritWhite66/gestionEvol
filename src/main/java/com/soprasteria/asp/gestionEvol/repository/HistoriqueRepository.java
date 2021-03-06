package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.soprasteria.asp.gestionEvol.model.Historique;

@Transactional
@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long>{
    ArrayList<Historique> findAll();
}
