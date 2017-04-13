package com.soprasteria.asp.gestionEvol.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soprasteria.asp.gestionEvol.model.ResponseError;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.repository.ErrorDao;
import com.soprasteria.asp.gestionEvol.repository.implementation.ErrorDaoImpl;
import com.soprasteria.asp.gestionEvol.service.ConvertResponse;
import com.soprasteria.asp.gestionEvol.service.ParseurErreurPython;

@Controller
public class ErrorController {
	
	@Autowired
	private ParseurErreurPython parseur;
	
	private ConvertResponse convertisseur;
	
	 
	private ErrorDao readDataError()
	{
		ArrayList<ErrorException> listError;
		String chemin = "Log\\log_Osiris_wrk.json";
		listError = parseur.getData(chemin).getListError();
		return new ErrorDaoImpl(listError);
	}
	
	@RequestMapping	("/Error")
	public String afficheListeError(ModelMap model)
	{		       
		ErrorDao serviceError = readDataError();
	
		ArrayList<ErrorException> errorAll = serviceError.findAll();
		model.put("countWarning", serviceError.countType(1));
		model.put("countError", serviceError.countType(2));
		model.put("errors", errorAll);
		return "ListError";
	}
	

	@RequestMapping(value={"/getError", "/getError/{ErrorType}"})
    public @ResponseBody ArrayList<ResponseError> getError(@PathVariable("ErrorType") Optional<String> errorType) 
    {
		ErrorDao serviceError = readDataError();
		
		ArrayList<ResponseError> response = null;
		ArrayList<ErrorException> errorAll = null;
		convertisseur=new ConvertResponse();
		if(errorType.isPresent() && errorType.get().equals("Error"))
		{
			errorAll = serviceError.findError(2);
		}
		else if(errorType.isPresent() && errorType.get().equals("Warning"))
		{
			errorAll = serviceError.findError(1);
		}
		else 
		{
			errorAll = serviceError.findAll();
		}
		try {
			response = convertisseur.convertToResponseError(errorAll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value="/getCountError")
    public @ResponseBody int countError() 
    {
		ErrorDao serviceError = readDataError();
		return serviceError.countType(2);
	}
	
	
	@RequestMapping(value="/getCountWarning")
    public @ResponseBody int countWarning() 
    {
		ErrorDao serviceError = readDataError();
		return serviceError.countType(1);
	}
	
	@RequestMapping(value={"/deleteError"},method=RequestMethod.GET)
    public @ResponseBody boolean deleteError(@RequestParam(value="idError", required=false) String idError) 
    {
		ErrorDao serviceError = readDataError();
		if(idError!=null)
		{
			try {
				return serviceError.delete(idError);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				return serviceError.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
