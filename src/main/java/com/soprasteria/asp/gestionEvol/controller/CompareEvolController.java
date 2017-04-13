package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.model.LigneCompareAppli;
import com.soprasteria.asp.gestionEvol.repository.EvolDao;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;

@Controller
public class CompareEvolController {
	
	@Autowired
	private ParseurEntreePython parseur;
	
	@Autowired
	private EvolDao serviceEvol;	
	

	private void readDataSearch()
	{
		ArrayList<Evol> listEvol;
		String chemin = "Repo\\Osiris_wrk.json";
		listEvol = parseur.getData(chemin).getListEvol();
		serviceEvol.setEvolsData(listEvol);
		
	}
	
	@RequestMapping(value = "/CompareEvol", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String compareEvol(@RequestParam Map<String,String> requestParams, ModelMap model) {
		
		readDataSearch();
		ArrayList<LigneCompareAppli> lignesComposant = new ArrayList<>();
		ArrayList<Evol> listEvol= new ArrayList<>();
		
		for (Entry<String, String> entry : requestParams.entrySet()) {
			listEvol.add(serviceEvol.find(entry.getKey()));
		}
		
		ArrayList<Appli> applis = serviceEvol.findApplicationForEvols(listEvol);
		for(Appli appli : applis)
		{
			LigneCompareAppli ligneComposant=new LigneCompareAppli(listEvol, appli);
			lignesComposant.add(ligneComposant);
		}

		
		model.put("evols",requestParams.keySet());
		model.put("lignesComposant", lignesComposant);
		
		return "CompareEvol";
	}
	
}
