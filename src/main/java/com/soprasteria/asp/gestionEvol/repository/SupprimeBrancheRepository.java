package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.ActionMerge;
import com.soprasteria.asp.gestionEvol.model.ActionRenommeBranche;
import com.soprasteria.asp.gestionEvol.model.ActionSupprimeBranche;

@Transactional
@Repository
public interface SupprimeBrancheRepository extends JpaRepository<ActionSupprimeBranche, Long>{

	ActionSupprimeBranche findById(int id);
	ArrayList<ActionSupprimeBranche> findAllByOrderByDateDesc();

	@Modifying
	@Query("UPDATE ActionSupprimeBranche SET fait = ?2 where id = ?1")
	int setFaitForSupprime(int id, boolean fait);
	
	@Modifying
	@Query("SELECT sb FROM ActionSupprimeBranche sb WHERE  sb.src IN ?1")
	ArrayList<ActionSupprimeBranche> findByListBranche(ArrayList<String> name);
}
