package com.soprasteria.asp.gestionEvol.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "historique")

public class Historique {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(columnDefinition="integer")
	private int idType;  
	
	@NotNull
	@Column(columnDefinition="integer")
	private int idMerge;
	@NotNull
	@Column(length = 100,columnDefinition="_varchar")
	private String qui;
	@NotNull
	@Column(columnDefinition="date")
	private Date date;
	
	
	public Historique() {
	}
	
	public Historique(int idType, int idMerge, String personne, Date date) {
		this.idType = idType;
		this.idMerge = idMerge;
		this.qui = personne;
		this.date = date;
	}
	

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public int getIdMerge() {
		return idMerge;
	}

	public void setIdMerge(int idMerge) {
		this.idMerge = idMerge;
	}

	public String getQui() {
		return qui;
	}
	public void setQui(String personne) {
		this.qui = personne;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
	    this.date=date;
	}
	
	
	
}
