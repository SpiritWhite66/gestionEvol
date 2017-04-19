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
@Table(name="branche_renomme")
public class RenommeBranche extends AbstractModelAction {
	
	  
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String src;
	@NotNull
	@Column(length = 300,columnDefinition="_varchar")
	private String dest;

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	

}
