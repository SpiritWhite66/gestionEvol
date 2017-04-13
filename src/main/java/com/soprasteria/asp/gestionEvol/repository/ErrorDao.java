package com.soprasteria.asp.gestionEvol.repository;

import java.util.ArrayList;

import com.soprasteria.asp.gestionEvol.exceptions.DAOException;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;



public interface ErrorDao {
	public ArrayList<ErrorException> findAll() throws DAOException;
	public ArrayList<ErrorException> findError(int errorType) throws DAOException;
	public boolean delete() throws Exception;
	public boolean delete(String idError) throws Exception;
	public int countType(int type);
	public int countAll() ;

}
