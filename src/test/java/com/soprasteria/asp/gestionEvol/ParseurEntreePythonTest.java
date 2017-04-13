package com.soprasteria.asp.gestionEvol;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.soprasteria.asp.gestionEvol.configuration.BusinessConfiguration;
import com.soprasteria.asp.gestionEvol.model.Evol;
import com.soprasteria.asp.gestionEvol.service.ParseurEntreePython;

@SpringBootTest(classes = BusinessConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ParseurEntreePythonTest {
	
	@Autowired
	private ParseurEntreePython parseur;

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertTrue(true);
		assertNotNull(parseur);
	}


	@Test
	public void verifieFichierVide()
	{
		ArrayList<Evol> listEvol;
		String chemin = "Repo\\testVide.json";
		listEvol = parseur.getData(chemin).getListEvol();
		System.out.println("Size : " + listEvol.size()); // LOG
		assertNotNull(listEvol);
		assertTrue(listEvol.size()==0);
	}
	
	@Test
	public void verifieFichierRempli()
	{
		
		ArrayList<Evol> listEvol;
		String chemin = "Repo\\testRempli.json";
		listEvol = parseur.getData(chemin).getListEvol();
		System.out.println("Size : " + listEvol.size());
		assertNotNull(listEvol);
		assertTrue(listEvol.size()>0);
	}
	
}
