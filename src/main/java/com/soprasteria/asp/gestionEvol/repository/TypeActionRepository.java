package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.model.TypeAction;

@Transactional
@Repository
public interface TypeActionRepository extends JpaRepository<TypeAction, Long>{
    ArrayList<TypeAction> findAll();
    TypeAction findById(int id);
}
