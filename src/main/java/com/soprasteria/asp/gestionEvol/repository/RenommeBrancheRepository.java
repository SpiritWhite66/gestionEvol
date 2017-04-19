package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.Merge;
import com.soprasteria.asp.gestionEvol.model.RenommeBranche;

@Transactional
@Repository
public interface RenommeBrancheRepository extends JpaRepository<RenommeBranche, Long>{

	RenommeBranche findById(int id);
	ArrayList<RenommeBranche> findAllByOrderByDateDesc();
	
	/**
	 * Met à jour le boolean "fait" du merge n° "id".
	 * @param id
	 * @param fait
	 * @return Merge
	 * retourne le merge modifié
	 **/
	@Modifying
	@Query("UPDATE RenommeBranche SET fait = ?2 where id = ?1")
	int setFaitForMerge(int id, boolean fait);
}
