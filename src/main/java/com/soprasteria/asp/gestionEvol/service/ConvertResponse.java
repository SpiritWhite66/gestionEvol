package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.ResponseError;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;



public class ConvertResponse {
	
	
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
}
