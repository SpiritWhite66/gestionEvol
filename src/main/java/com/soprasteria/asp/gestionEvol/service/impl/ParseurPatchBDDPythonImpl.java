package com.soprasteria.asp.gestionEvol.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.model.PatchBDD;
import com.soprasteria.asp.gestionEvol.model.StructureFichier;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;
import com.soprasteria.asp.gestionEvol.service.ParseurPatchBDDPython;
import com.soprasteria.asp.gestionEvol.service.SvnResultFileReader;

@Component
public class ParseurPatchBDDPythonImpl implements ParseurPatchBDDPython{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ParseurPatchBDDPythonImpl.class);

	@Autowired
	private SvnResultFileReader fileReader;
	
	

    

	@Override
	public StructureFichier getData(String name)
	{
		StructureFichier data = new StructureFichier();
		data.setListPatchBDD(fileReader.getData(name, new TypeReference<ArrayList<PatchBDD>>() {}));
		return data;
	}





	@Override
	public ArrayList<String> getAllFileRepo() {
		return fileReader.getAllFileRepo();
	}


}


