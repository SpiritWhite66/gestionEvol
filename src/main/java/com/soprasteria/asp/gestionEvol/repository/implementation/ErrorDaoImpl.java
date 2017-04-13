package com.soprasteria.asp.gestionEvol.repository.implementation;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.repository.ErrorDao;
import com.soprasteria.asp.gestionEvol.exceptions.DAOException;

public class ErrorDaoImpl implements ErrorDao {

	private ArrayList<ErrorException> errorsData;

    public ErrorDaoImpl(ArrayList<ErrorException> data) {
    	this.errorsData=data;
    }
    
	
	public ArrayList<ErrorException> findAll() throws DAOException
	{
		return errorsData;
	}
	
	public ArrayList<ErrorException> findError(int errorType) throws DAOException
	{
		Predicate<ErrorException> p1;
		p1 = error -> error.getError_code()==errorType;
		return (ArrayList<ErrorException>) errorsData.stream().parallel().filter(p1).collect(Collectors.toList());
	}
	
	public int countAll() 
	{
		return errorsData.size();
	}
	
	public int countType(int type)
	{
		int compteur=0;
		for (ErrorException error : errorsData)
		{
			if(error.getError_code()==type)
			{
				compteur++;
			}
		}
		return compteur;
	}

	@Override
	public boolean delete() throws Exception {
		errorsData.clear();
		return true;
	}

	@Override
	public boolean delete(String idError) throws Exception {
		Predicate<ErrorException> p1;
		p1 = error -> error.getName().equals(idError);
		System.out.println(idError);
		System.out.println(errorsData.stream().anyMatch(p1));
		return errorsData.removeIf(p1);
	}
}
