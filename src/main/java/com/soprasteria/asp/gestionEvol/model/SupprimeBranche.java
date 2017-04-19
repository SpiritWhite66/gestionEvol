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
@Table(name="branche_supprime")
public class SupprimeBranche extends AbstractModelAction{
	
//	@Id
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
//	private int id;
	  
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String src;

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	

}
