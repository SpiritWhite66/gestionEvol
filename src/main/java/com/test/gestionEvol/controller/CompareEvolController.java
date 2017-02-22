package com.test.gestionEvol.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.gestionEvol.model.bean.Appli;
import com.test.gestionEvol.model.bean.Evol;
import com.test.gestionEvol.model.bean.LigneCompareAppli;
import com.test.gestionEvol.model.dao.DAOFactory;
import com.test.gestionEvol.model.dao.EvolDao;

@Controller
public class CompareEvolController {
	private DAOFactory daoFactory;

	@RequestMapping(value = "/CompareEvol", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String compareEvol(@RequestParam Map<String,String> requestParams, ModelMap model) {
		
		this.daoFactory = DAOFactory.getInstance();
		EvolDao facadeJson = this.daoFactory.getUtilisateurDao();
		ArrayList<LigneCompareAppli> lignesComposant = new ArrayList<>();
		ArrayList<Evol> listEvol= new ArrayList<>();
		
		for (Entry<String, String> entry : requestParams.entrySet()) {
			listEvol.add(facadeJson.find(entry.getKey()));
		}
		
		ArrayList<Appli> applis = facadeJson.findApplicationForEvols(listEvol);
		System.out.println(applis.size());
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
