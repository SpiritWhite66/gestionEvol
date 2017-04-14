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
@Table(name = "evolution")
public class Evolution {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(columnDefinition="date")
	private Date debut;
	
	@Column(columnDefinition="date")
	private Date fin;
	
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String chef_projet;
	
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String ref_technique;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getChef_projet() {
		return chef_projet;
	}

	public void setChef_projet(String chef_projet) {
		this.chef_projet = chef_projet;
	}

	public String getRef_technique() {
		return ref_technique;
	}

	public void setRef_technique(String ref_technique) {
		this.ref_technique = ref_technique;
	}
	
	
	
	
	
	
}