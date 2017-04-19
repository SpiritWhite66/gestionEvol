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
public class Merge extends AbstractModelAction {
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String version1;
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String version2;	
	
	private String commentaire;
	
	public Merge() {
	}
	
	public Merge(String version1, String version2, boolean fait, String personne, Date date, String commentaire) {
		this.fait = fait;
		this.version1 = version1;
		this.version2 = version2;
		this.personne = personne;
		this.date = date;
		this.commentaire=commentaire;
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
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


}
