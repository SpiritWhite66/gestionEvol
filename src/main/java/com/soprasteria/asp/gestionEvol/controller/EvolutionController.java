package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.repository.ErrorDao;

@Controller
public class EvolutionController {
	
	@RequestMapping	("/GererEvolution")
	public String gererEvolution(ModelMap model)
	{		       
		return "GestionEvolution";
	}
	
}
