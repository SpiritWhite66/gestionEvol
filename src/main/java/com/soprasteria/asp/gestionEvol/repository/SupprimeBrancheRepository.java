package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.model.SupprimeBranche;

@Transactional
@Repository
public interface SupprimeBrancheRepository extends JpaRepository<SupprimeBranche, Long>{

	SupprimeBranche findById(int id);
	ArrayList<SupprimeBranche> findAllByOrderByDateDesc();

}
