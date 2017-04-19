package com.soprasteria.asp.gestionEvol.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractModelAction {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	protected int id;

	@NotNull
	protected String personne;
	@NotNull
	protected boolean fait;
	@NotNull
	protected Date date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonne() {
		return personne;
	}
	public void setPersonne(String personne) {
		this.personne = personne;
	}
	public boolean isFait() {
		return fait;
	}
	public void setFait(boolean fait) {
		this.fait = fait;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) throws ParseException {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	     java.util.Date parsed = format.parse(date);
	     java.sql.Date dateSql = new Date(parsed.getTime());
	     this.date=dateSql;
	}
	
	
}
