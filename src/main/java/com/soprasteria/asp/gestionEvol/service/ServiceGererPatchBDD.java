package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.PatchBDD;

public interface ServiceGererPatchBDD {

	ArrayList<PatchBDD> findAll();
	ArrayList<PatchBDD> findByApplication(String application);
	
}
