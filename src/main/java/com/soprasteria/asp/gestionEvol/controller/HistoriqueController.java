package com.soprasteria.asp.gestionEvol.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soprasteria.asp.gestionEvol.model.Historique;
import com.soprasteria.asp.gestionEvol.service.ServiceGererHistorique;

@Controller
public class HistoriqueController {
	
	@Autowired
	ServiceGererHistorique historiqueService;
	
	@RequestMapping	("/Historique")
	public String historique(ModelMap model)
	{	
		return "ListHistorique";
	}
	@RequestMapping	("/getAllHistorique")
	public @ResponseBody ArrayList<Historique> afficheHistorique(ModelMap model)
	{	
		return historiqueService.findAll();
	}
}
