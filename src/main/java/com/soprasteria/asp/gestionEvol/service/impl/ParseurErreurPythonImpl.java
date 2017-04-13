package com.soprasteria.asp.gestionEvol.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.soprasteria.asp.gestionEvol.model.StructureFichier;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.service.ParseurErreurPython;
import com.soprasteria.asp.gestionEvol.service.SvnResultFileReader;

@Component
public class ParseurErreurPythonImpl implements ParseurErreurPython{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ParseurErreurPythonImpl.class);

	@Autowired
	private SvnResultFileReader fileReader;
	
	

    

	@Override
	public StructureFichier getData(String name)
	{
		StructureFichier data = new StructureFichier();
		data.setListError(fileReader.getData(name, new TypeReference<ArrayList<ErrorException>>() {}));
		return data;
	}
}


