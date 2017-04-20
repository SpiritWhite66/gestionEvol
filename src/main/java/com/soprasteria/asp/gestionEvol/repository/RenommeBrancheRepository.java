package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.ActionMerge;
import com.soprasteria.asp.gestionEvol.model.ActionRenommeBranche;

@Transactional
@Repository
public interface RenommeBrancheRepository extends JpaRepository<ActionRenommeBranche, Long>{

	ActionRenommeBranche findById(int id);
	ArrayList<ActionRenommeBranche> findAllByOrderByDateDesc();
	
	/**
	 * Met à jour le boolean "fait" du merge n° "id".
	 * @param id
	 * @param fait
	 * @return Merge
	 * retourne le merge modifié
	 **/
	@Modifying
	@Query("UPDATE ActionRenommeBranche SET fait = ?2 where id = ?1")
	int setFaitForRenomme(int id, boolean fait);
	
	@Modifying
	@Query("SELECT rb FROM ActionRenommeBranche rb WHERE  rb.src IN ?1")
	ArrayList<ActionRenommeBranche> findByListBranche(ArrayList<String> name);
	
}
