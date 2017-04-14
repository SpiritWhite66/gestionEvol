package com.soprasteria.asp.gestionEvol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "typeaction")
public class TypeAction {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	  
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String libelle;
	
	
	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId() {
		return id;
	}
	
	
	
	
}