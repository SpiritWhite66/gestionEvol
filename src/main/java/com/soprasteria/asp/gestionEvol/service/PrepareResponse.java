package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.ReponseEvolution;
import com.soprasteria.asp.gestionEvol.model.ResponseError;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;

public interface PrepareResponse {

	public ArrayList<ResponseError> convertToResponseError(ArrayList<ErrorException> listError) throws Exception;
	public ReponseEvolution prepareReponseEvolution(ArrayList<Branche> branches);
}
