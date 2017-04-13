package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.repository.EvolDao;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;
import com.soprasteria.asp.gestionEvol.service.TriSVN;

@Controller
public class HomeController {
	
	@Autowired
	private ParseurEntreePython parseur;
	
	@Autowired
	private EvolDao serviceevol;	

	private void readDataSearch()
	{
		ArrayList<Evol> listEvol;
		String chemin = "Repo\\Osiris_wrk.json";
		listEvol = parseur.getData(chemin).getListEvol();
		serviceevol.setEvolsData(listEvol);
	}
	
	@RequestMapping	("/")
	public String afficheListeEvol(ModelMap model)
	{		       
		readDataSearch();
		ArrayList<String> evolAll = serviceevol.getNameAllEvol();
		model.put("evols", evolAll);
		return "ListEvol";
	}
	
	@RequestMapping	("/SVN")
	public String afficheListeSVN(ModelMap model)
	{		       
		readDataSearch();
		TriSVN test = new TriSVN(serviceevol.findAll());
		ArrayList<Appli> testSVN = test.triParApplication();
		model.put("applications", testSVN);
		return "ListSVN";
	}
	
	@RequestMapping(value="/getBrancheSVN" ,method=RequestMethod.GET)
    public @ResponseBody ArrayList<Branche> getBrancheSVN(@RequestParam(value="name", required=true) String name) 
    {
		readDataSearch();
		TriSVN test = new TriSVN(serviceevol.findAll());
		ArrayList<Appli> testSVN = test.triParApplication();
		
		Predicate<Appli> p1;
		p1 = (error -> error.getName().equals(name));
		testSVN = (ArrayList<Appli>) testSVN.stream().parallel().filter(p1).collect(Collectors.toList());
		if(testSVN!=null)
		{
			return testSVN.get(0).getBranche();
		}		

		return null;
	}
	
	@RequestMapping(value="/getAppByEvol" ,method=RequestMethod.GET)
    public @ResponseBody ArrayList<Appli> getAppliByEvol(@RequestParam(value="name", required=true) String name) 
    {
		readDataSearch();
		return serviceevol.find(name).getApplication();
	}
	
	@RequestMapping(value="/getBrancheByAppli" ,method=RequestMethod.GET)
    public @ResponseBody ArrayList<Branche> getBrancheByAppli(@RequestParam(value="nameEvol", required=true) String nameEvol, @RequestParam(value="nameAppli", required=true) String nameAppli) 
    {
		readDataSearch();	
		Evol evol = serviceevol.find(nameEvol);
		return serviceevol.findBrancheByApplication(evol, nameAppli);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class JsonNotFoundException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	

}
