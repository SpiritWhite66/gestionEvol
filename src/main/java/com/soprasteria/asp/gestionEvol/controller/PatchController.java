package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soprasteria.asp.gestionEvol.model.PatchBDD;
import com.soprasteria.asp.gestionEvol.model.PropertySession;
import com.soprasteria.asp.gestionEvol.service.ParseurPatchBDDPython;
import com.soprasteria.asp.gestionEvol.service.ServiceGererPatchBDD;

@Controller
public class PatchController {

	@Autowired
	private ServiceGererPatchBDD servicePatchBDD;
	
	
	@RequestMapping	("/Patch")
	public String afficheListePatch()
	{
		return "ListPatch";
	}
	
	@RequestMapping	("/GetPatch")
	public @ResponseBody  ArrayList<PatchBDD> getListePatch()
	{
		return servicePatchBDD.findAll();
	}
	
	@RequestMapping	("/Patch/{application}")
	public @ResponseBody  ArrayList<PatchBDD> getListePatchByApplication(@PathVariable("application") String application)
	{
		return servicePatchBDD.findByApplication(application);
	}
	
}
