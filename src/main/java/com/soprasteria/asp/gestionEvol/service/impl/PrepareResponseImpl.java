package com.soprasteria.asp.gestionEvol.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.soprasteria.asp.gestionEvol.model.AbstractActionModel;
import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.ActionRenommeBranche;
import com.soprasteria.asp.gestionEvol.model.ActionSupprimeBranche;
import com.soprasteria.asp.gestionEvol.model.ReponseEvolution;
import com.soprasteria.asp.gestionEvol.model.ResponseError;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.service.PrepareResponse;
import com.soprasteria.asp.gestionEvol.service.ServiceGererBranche;


@Component
public class PrepareResponseImpl implements PrepareResponse {
	
	@Autowired 
	ServiceGererBranche serviceBranche;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ParseurEntreePythonImpl.class);

	
	public ArrayList<ResponseError> convertToResponseError(ArrayList<ErrorException> listError) throws Exception
	{
		ArrayList<ResponseError> listResult = new ArrayList<>();
		if(listError != null)
		{
			ResponseError errorTmp;
			for(ErrorException error : listError)
			{
				errorTmp = new ResponseError(error);
				listResult.add(errorTmp);
			}
		}
		else
		{
			throw new Exception("Aucune Erreur");
		}
		return listResult;
	}
	
	public ReponseEvolution prepareReponseEvolution(ArrayList<Branche> branches)
	{
		ReponseEvolution reponse = new ReponseEvolution();
		ArrayList<ActionRenommeBranche> listActionRenomme;
		ArrayList<ActionSupprimeBranche> listActionSupprime;
		ArrayList<ActionRenommeBranche> listActionRenommeResult;
		ArrayList<ActionSupprimeBranche> listActionSupprimeResult;

		LOGGER.debug("Branches retournée : "+ branches.toString());
		listActionRenomme = serviceBranche.findByListRenommeBranche(branches);
		listActionSupprime = serviceBranche.findByListSupprimeBranche(branches);
		LOGGER.debug("Action renommage : "+ listActionRenomme);
		LOGGER.debug("Action Suppression : "+ listActionSupprime);
	    Predicate<ActionRenommeBranche> p1;
	    Predicate<ActionSupprimeBranche> p2;
	    
		for(Branche branche : branches)
		{
			listActionRenommeResult = new ArrayList<>();
			listActionSupprimeResult = new ArrayList<>();
			p1 = e -> (e.getSrc().trim().toUpperCase().equals(branche.getName().trim().toUpperCase()) && e.isFait()==false);
			p2 = e -> (e.getSrc().trim().toUpperCase().equals(branche.getName().trim().toUpperCase()) && e.isFait()==false);
		    ArrayList<ActionRenommeBranche> listTmp = (ArrayList<ActionRenommeBranche>) listActionRenomme.stream().filter(p1).collect(Collectors.toList());
		    if(!listTmp.isEmpty())
		    {
		    	listActionRenommeResult.addAll(listTmp);
		    }
		    ArrayList<ActionSupprimeBranche> listTmp2 = (ArrayList<ActionSupprimeBranche>) listActionSupprime.stream().filter(p2).collect(Collectors.toList());
		    if(!listTmp.isEmpty())
		    {
		    	listActionSupprimeResult.addAll(listTmp2);
		    }
		    reponse.addListActionRenomme(listActionRenommeResult);
		    reponse.addListActionSupprime(listActionSupprimeResult);
		}
		
		reponse.setBranches(branches);
		return reponse;
	}
}
