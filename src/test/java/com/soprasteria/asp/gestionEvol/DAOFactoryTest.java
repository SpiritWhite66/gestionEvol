package com.soprasteria.asp.gestionEvol;

import java.io.BufferedReader;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;
import com.soprasteria.asp.gestionEvol.service.impl.ParseurEntreePythonImpl;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
public class DAOFactoryTest extends TestCase {

	@Test
	public void openFileJsonTest()
	{
//		BufferedReader br;
//		String chemin = "test";
//		br = DAOFactory.openFileJson(chemin);
//		assertNotNull(br);
	}
	
	@Test
	public void getDataTest()
	{
//		ArrayList<Evol> listEvol;
//		String chemin = "test";
//		listEvol = DAOFactory.getData(chemin);
//		assertNotNull(listEvol);
	}
	@Test
	public void getDataErrorTest()
	{
//		ArrayList<ErrorException> listError;
//		String chemin = "test";
//		listError = DAOFactory.getDataError(chemin);
//		assertNotNull(listError);
	}
	
	
}
