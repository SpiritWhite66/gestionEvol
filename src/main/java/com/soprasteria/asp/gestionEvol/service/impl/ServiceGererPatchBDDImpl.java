package com.soprasteria.asp.gestionEvol.service.impl;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soprasteria.asp.gestionEvol.model.PatchBDD;
import com.soprasteria.asp.gestionEvol.service.ParseurPatchBDDPython;
import com.soprasteria.asp.gestionEvol.service.ServiceGererPatchBDD;

@Component
public class ServiceGererPatchBDDImpl implements ServiceGererPatchBDD {

	@Autowired
	private ParseurPatchBDDPython parseur;
	
	@Override
	public ArrayList<PatchBDD> findAll() {
		return parseur.getData("test.json").getListPatchBDD();
	}

	@Override
	public ArrayList<PatchBDD> findByApplication(String application) {
		Predicate<PatchBDD> p1 = p -> p.getApplication().equals(application);
		ArrayList<PatchBDD> listResult = (ArrayList<PatchBDD>) parseur.getData("test.json").getListPatchBDD().stream().parallel().filter(p1).collect(Collectors.toList());
		return listResult;
	}

}
