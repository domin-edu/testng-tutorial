package pl.grinder.tutorial.testng;

import static junit.framework.Assert.fail;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(sequential=true)
public class AdnotacjeMetodTest {

	@BeforeClass
	private void przedWszystkimiTestami() {
		System.out.println("<< Przed wszystkimi testami >>");
	}

	@BeforeGroups("pozytywne")
	private void przedGrupaTestowPozytywnych() {
		System.out.println("<< Przed wszystkimi testami z grupy pozytywnych >>");
	}
	
	@BeforeGroups("negatywne")
	private void przedGrupaTestowNegatywnych() {
		System.out.println("<< Przed wszystkimi testami z grupy negatywnych >>");
	}
	
	@BeforeMethod
	private void przedKazdaMetodaTestowa() {
		System.out.println("<< Przed metoda testowa >>");
	}
	
	@AfterGroups("pozytywne")
	private void podGrupieTestowPozytywnych() {
		System.out.println("<< Po wszystkimi testach z grupy pozytywnych >>");
	}
	
	@AfterGroups("negatywne")
	private void ppoGrupieTestowNegatywnych() {
		System.out.println("<< Po wszystkich testach z grupy negatywnych >>");
	}

	@AfterMethod
	private void poKazdejMetodzieTestowej() {
		System.out.println("<< Po metodzie testowej >>");
	}

	@AfterClass
	private void poWszystkichTestach() {
		System.out.println("<< Po wszystkich testach >>");
	}

	@Test(groups="pozytywne")
	public void pozytywnyTest1() {
		assertTrue(true, "true musi byc prawda");
	}
	
	@Test(groups="pozytywne")
	public void pozytywnyTest2() {
		assertTrue(true, "true musi byc prawda");
	}
	
	@Test(groups="negatywne")
	public void negatywnyTest1() {
		fail("Nie udalo sie...");
	}
	
	@Test(groups="negatywne")
	public void negatywnyTest2() {
		fail("Nie udalo sie...");
	}
}
