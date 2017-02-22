package com.test.gestionEvol.controller;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.gestionEvol.model.bean.Appli;
import com.test.gestionEvol.model.bean.Branche;
import com.test.gestionEvol.model.dao.DAOFactory;
import com.test.gestionEvol.model.dao.EvolDao;

@Controller
public class TestController {
	//private final DaoJson daoJson;
	private DAOFactory daoFactory;

	
	@RequestMapping	("/")
	public String afficheListeEvol(ModelMap model)
	{		       
		this.daoFactory = DAOFactory.getInstance();
		EvolDao testJson = this.daoFactory.getUtilisateurDao();
		if(testJson == null)
		{
			throw new JsonNotFoundException();
		}
		ArrayList<String> evolAll = testJson.getNameAllEvol();
		model.put("evols", evolAll);
		return "ListEvol";
	}
	
	@RequestMapping(value="/getAppByEvol" ,method=RequestMethod.GET)
    public @ResponseBody ArrayList<Appli> getAppliByEvol(@RequestParam(value="name", required=true) String name) 
    {
    	this.daoFactory = DAOFactory.getInstance();
		EvolDao testJson = this.daoFactory.getUtilisateurDao();
		if(testJson == null)
		{
			throw new JsonNotFoundException();
		}
		return testJson.find(name).getApplication();
	}
	
	@RequestMapping(value="/getBrancheByAppli" ,method=RequestMethod.GET)
    public @ResponseBody ArrayList<Branche> getBrancheByAppli(@RequestParam(value="nameEvol", required=true) String nameEvol, @RequestParam(value="nameAppli", required=true) String nameAppli) 
    {
    	this.daoFactory = DAOFactory.getInstance();
		EvolDao testJson = this.daoFactory.getUtilisateurDao();
		if(testJson == null)
		{
			throw new JsonNotFoundException();
		}
		System.out.println(nameAppli);
		return testJson.findBrancheByApplication(nameEvol, nameAppli);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class JsonNotFoundException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	

}
