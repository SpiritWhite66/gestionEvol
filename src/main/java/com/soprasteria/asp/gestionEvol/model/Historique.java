package com.soprasteria.asp.gestionEvol.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "historique")

public class Historique {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private TypeAction type;  
	
    @ManyToOne
	private Merge merge;
	
    @NotNull
	@Column(length = 100,columnDefinition="_varchar")
	private String qui;
    
	@NotNull
	@Column(columnDefinition="date")
	private Date date;
	
	
	public Historique() {
	}
	
	public Historique(TypeAction type, Merge merge, String personne, Date date) {
		this.type = type;
		this.merge = merge;
		this.qui = personne;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TypeAction getType() {
		return type;
	}

	public void setType(TypeAction type) {
		this.type = type;
	}

	public Merge getMerge() {
		return merge;
	}

	public void setMerge(Merge merge) {
		this.merge = merge;
	}

	public String getQui() {
		return qui;
	}

	public void setQui(String qui) {
		this.qui = qui;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	
	
	
	
}
