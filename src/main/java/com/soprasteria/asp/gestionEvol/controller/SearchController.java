package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.soprasteria.asp.gestionEvol.exceptions.DAOException;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.repository.EvolDao;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;


@Controller
public class SearchController {
	
	@Autowired
	private ParseurEntreePython parseur;
	
	@Autowired
	private EvolDao serviceEvol;	
	
	private void readDataSearch()
	{
		ArrayList<Evol> listEvol;
		String chemin = "Repo\\testVide.json";
		listEvol = parseur.getData(chemin).getListEvol();
		serviceEvol.setEvolsData(listEvol);
	}
	
	@RequestMapping(value="/SearchApplication" ,method=RequestMethod.POST)
    public String searchByApplication(@RequestParam Map<String,String> param, ModelMap model) 
    {
		readDataSearch();
		ArrayList<Evol> brancheByEvol = null;
		if(param.get("fonction").equals("Application")||param.get("fonction").equals("Branche"))
		{
			try {
				brancheByEvol = serviceEvol.findEvolByBranche(param.get("param"), param.get("fonction"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(param.get("fonction").equals("Evolution"))
		{
			try {
				brancheByEvol = serviceEvol.findBrancheByEvol(param.get("param"));
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		model.put("evols", brancheByEvol);
		return "Search";
	}
	
}
