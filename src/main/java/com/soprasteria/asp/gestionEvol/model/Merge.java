package com.soprasteria.asp.gestionEvol.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="merge")
public class Merge {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	  
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String version1;
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String version2;
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String personne;
	@NotNull
	@Column(columnDefinition="boolean")
	private boolean fait;
	@NotNull
	@Column(columnDefinition="date")
	private Date date;
	
	
	public Merge() {
	}
	
	public Merge(String version1, String version2, boolean fait, String personne, Date date) {
		this.fait = fait;
		this.version1 = version1;
		this.version2 = version2;
		this.personne = personne;
		this.date = date;
	}
	
	public boolean isFait() {
		return fait;
	}

	public void setFait(boolean fait) {
		this.fait = fait;
	}

	public String getversion1() {
		return version1;
	}
	public void setversion1(String version1) {
		this.version1 = version1;
	}
	public String getversion2() {
		return version2;
	}
	public void setversion2(String version2) {
		this.version2 = version2;
	}
	public String getPersonne() {
		return personne;
	}
	public void setPersonne(String personne) {
		this.personne = personne;
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

	public int getId() {
		return id;
	}
	
	
}
