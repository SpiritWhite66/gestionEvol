package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.soprasteria.asp.gestionEvol.model.Merge;

@Transactional
@Repository
public interface MergeRepository extends JpaRepository<Merge, Long>{

	Merge saveAndFlush(Merge merge);
	Merge findById(int id);
	ArrayList<Merge> findAllByOrderByDateDesc();
	ArrayList<Merge> findAllByFaitOrderByDateDesc(Boolean all);

	
	/**
	 * Met à jour le boolean "fait" du merge n° "id".
	 * @param id
	 * @param fait
	 * @return Merge
	 * retourne le merge modifié
	 **/
	@Modifying
	@Query("UPDATE Merge SET fait = ?2 where id = ?1")
	int setFaitForMerge(int id, boolean fait);
}
