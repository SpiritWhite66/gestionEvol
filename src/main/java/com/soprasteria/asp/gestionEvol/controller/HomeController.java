package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.model.PropertySession;
import com.soprasteria.asp.gestionEvol.model.ReponseEvolution;
import com.soprasteria.asp.gestionEvol.repository.EvolDao;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;
import com.soprasteria.asp.gestionEvol.service.PrepareResponse;
import com.soprasteria.asp.gestionEvol.service.TriSVN;
import com.soprasteria.asp.gestionEvol.service.impl.ParseurEntreePythonImpl;

@Controller
@SessionAttributes(value = "propertybean", types = {PropertySession.class})

public class HomeController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ParseurEntreePythonImpl.class);

	@Autowired
	private ParseurEntreePython parseur;
	
	@Autowired
	private EvolDao serviceevol;	
	
	@Autowired
	private PrepareResponse convertisseur;
	
	@ModelAttribute("propertybean")
    public PropertySession addAttributes() {
		PropertySession property = new PropertySession();
		property.setTri(false);
		property.setFileJSON("Osiris_wrk.json");
		return property;
    }
	
	private void readDataSearch(PropertySession sessionObj, ModelMap model)
	{
		if(sessionObj==null)
		{
			LOGGER.debug("position dans afficheListEvol sessionObj null =>"+sessionObj);
			sessionObj = new PropertySession(false, "Repo/Osiris_wrk.json");
			model.addAttribute("propertybean", sessionObj);
		}
		String chemin = sessionObj.getFileJSON();
		ArrayList<Evol> listEvol;
		if(chemin==null)
		{
			chemin = "Repo\\Osiris_wrk.json";
		}
		listEvol = parseur.getData(chemin).getListEvol();
		serviceevol.setEvolsData(listEvol);
	}
	
	@RequestMapping	("/")
	public String afficheListeEvol(HttpSession session, ModelMap model)
	{
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		readDataSearch(sessionObj, model);
		ArrayList<String> evolAll = serviceevol.getNameAllEvol(((PropertySession) model.get("propertybean")).isTri());
		model.put("evols", evolAll);
		return "ListEvol";
	}
	
	@RequestMapping("/InverseTri")
	public String inverseTri(HttpSession session, ModelMap model)
	{
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		sessionObj.setTri(!sessionObj.isTri());
		return "redirect:/";
	}
	
	
	@RequestMapping	("/SVN")
	public String afficheListeSVN(HttpSession session, ModelMap model)
	{		       
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		readDataSearch(sessionObj, model);	
		TriSVN test = new TriSVN(serviceevol.findAll());
		ArrayList<Appli> testSVN = test.triParApplication();
		model.put("applications", testSVN);
		return "ListSVN";
	}
	
	@RequestMapping(value="/getBrancheSVN" ,method=RequestMethod.GET)
    public @ResponseBody ArrayList<Branche> getBrancheSVN(HttpSession session, ModelMap model, @RequestParam(value="name", required=true) String name) 
    {
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		readDataSearch(sessionObj, model);
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
    public @ResponseBody ArrayList<Appli> getAppliByEvol(HttpSession session, ModelMap model, @RequestParam(value="name", required=true) String name) 
    {
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		readDataSearch(sessionObj, model);
		return serviceevol.find(name).getApplication();
	}
	
	@RequestMapping(value="/getBrancheByAppli" ,method=RequestMethod.GET)
    public @ResponseBody ReponseEvolution getBrancheByAppli(HttpSession session, ModelMap model, @RequestParam(value="nameEvol", required=true) String nameEvol, @RequestParam(value="nameAppli", required=true) String nameAppli) 
    {
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		readDataSearch(sessionObj, model);
		Evol evol = serviceevol.find(nameEvol);
		return convertisseur.prepareReponseEvolution(serviceevol.findBrancheByApplication(evol, nameAppli));
	}
	
	
	
	@RequestMapping(value="/getAllFile")
	public @ResponseBody ArrayList<String> getAllFile(HttpSession session)
	{
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		ArrayList<String> listFile = new ArrayList<>();
		ArrayList<String> listFileTmp = new ArrayList<>();
		listFileTmp=parseur.getAllFileRepo();

		if(sessionObj.getFileJSON()!=null){
			listFile.add(sessionObj.getFileJSON().split("/")[1]);
			listFileTmp.remove(sessionObj.getFileJSON().split("/")[1]);

		}
		listFile.addAll(listFileTmp);
		return listFile;
	}
	
	@RequestMapping(value="/setFile", method=RequestMethod.POST)
	public @ResponseBody void setFile(HttpSession session, @RequestParam(value="nameFile", required=true) String nameFile)
	{
		PropertySession sessionObj = (PropertySession)session.getAttribute("propertybean");
		sessionObj.setFileJSON("Repo/"+nameFile);
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class JsonNotFoundException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	

}
