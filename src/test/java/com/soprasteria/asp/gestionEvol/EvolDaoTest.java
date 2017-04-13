package com.soprasteria.asp.gestionEvol;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soprasteria.asp.gestionEvol.model.Appli;
import com.soprasteria.asp.gestionEvol.model.Branche;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.repository.EvolDao;
import com.soprasteria.asp.gestionEvol.repository.implementation.EvolDaoImpl;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;
import com.soprasteria.asp.gestionEvol.service.impl.ParseurEntreePythonImpl;

@SpringBootTest
public class EvolDaoTest {
	/*private EvolDao evolObject;
	
	@Autowired
	private ParseurEntreePython parseur;
	
	@Before
	public void initialiser() throws Exception {
		String chemin = "Repo\\testRempli.json";
		evolObject =  new EvolDaoImpl(parseur.getData(chemin).getListEvol());

	}
	
	@Test
	public void happyPath() {
		assertTrue(true);
	}
	
	@Test
	public void retourneTout()
	{
		ArrayList<Evol> listEvol= evolObject.findAll();
		assertTrue(listEvol.size()==4);
	}

	@Test
	public void verifieTrouveEvolutionParNom()
	{
		String name ="test3";
		Evol listEvol= evolObject.find(name);
		assertTrue(listEvol.getName().equals(name));
	}
	
	@Test
	public void verifieTrouveAucuneEvolutionParNom()
	{
		String name ="deffe";
		Evol listEvol= evolObject.find(name);
		assertNull(listEvol);
	}
	
	@Test
	public void retourneToutNomEvolution()
	{
		ArrayList<String> listNameEvol = new ArrayList<>();
		listNameEvol.add("test1");
		listNameEvol.add("test2");
		listNameEvol.add("test3");
		listNameEvol.add("test4");
		ArrayList<String> listEvol= evolObject.getNameAllEvol();
		assertTrue(listEvol.containsAll(listNameEvol));
	}
	
	@Test
	public void verifieRechercheBrancheParEvol()
	{
		ArrayList<Appli> listAppli = new ArrayList<>();
		Appli application = new Appli();
		application.setName("applicationTest1");
		listAppli.add(application);
		ArrayList<Branche> listBranche = new ArrayList<>();
		Branche branche = new Branche();
		branche.setName("brancheTest1");
		listBranche.add(branche);
		branche = new Branche();
		branche.setName("brancheTest12");
		listBranche.add(branche);
		
		application.setBranche(listBranche);
		
		String name="test1";
		ArrayList<Evol> listEvol= evolObject.findBrancheByEvol(name);
		assertTrue(listEvol.size()==1);
		assertTrue(listEvol.get(0).getName().equals(name));
		System.out.println(listEvol.get(0).getApplication().toString());
		assertTrue(listEvol.get(0).getApplication().containsAll(listAppli));

	}
	
	@Test
	public void verifieRetourneAucuneBrancheParEvol()
	{
		String name="fefse";
		ArrayList<Evol> listEvol= evolObject.findBrancheByEvol(name);
		assertNull(listEvol);
	}*/


}
