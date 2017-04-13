package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Merge;

public interface ServiceGererMerge {

	public Merge save(Merge merge);
	public ArrayList<Merge> findAllOrderByDateDesc(Boolean all);
	public boolean modifierMergeFait(int id);

}
