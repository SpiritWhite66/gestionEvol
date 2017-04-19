package com.soprasteria.asp.gestionEvol.model;



public class PropertySession {
	private boolean tri;
	private String fileJSON;
	
	
	public PropertySession() {
	}
	
	public PropertySession(boolean tri, String fileJSON) {
		this.tri = tri;
		this.fileJSON = fileJSON;
	}
	
	public boolean isTri() {
		return tri;
	}
	public void setTri(boolean tri) {
		this.tri = tri;
	}
	public String getFileJSON() {
		return fileJSON;
	}
	public void setFileJSON(String fileJSON) {
		this.fileJSON = fileJSON;
	}
	
}
