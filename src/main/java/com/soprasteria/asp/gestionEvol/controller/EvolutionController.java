package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soprasteria.asp.gestionEvol.model.Evolution;
import com.soprasteria.asp.gestionEvol.model.ActionMerge;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.repository.ErrorDao;
import com.soprasteria.asp.gestionEvol.service.ServiceGererEvolution;
import com.soprasteria.asp.gestionEvol.service.ServiceGererBranche;

@Controller
public class EvolutionController {
	
	@Autowired
	private ServiceGererEvolution evolutionService;
	
	@RequestMapping	("/GererEvolution")
	public String gererEvolution(ModelMap model)
	{		       
		return "GestionEvolution";
	}
	
	@RequestMapping ("/GetAllEvolution")
	public @ResponseBody ArrayList<Evolution> getAllEvolutione(@ModelAttribute(value = "myEvolution") final Evolution evolution)
	{
		return evolutionService.findAll();
	}
	
	@RequestMapping ("/SaveEvolutionLine")
	public @ResponseBody int saveEvolutionLine(@ModelAttribute(value = "myEvolution") final Evolution evolution)
	{
		return evolutionService.save(evolution).getId();
	}
	
	@RequestMapping ("/RemoveEvolutionLine")
	public @ResponseBody int removeEvolutionLine(int id)
	{
		evolutionService.remove(id);
		return id;
	}
}
