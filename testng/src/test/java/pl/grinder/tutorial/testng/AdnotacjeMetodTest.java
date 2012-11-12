package pl.grinder.tutorial.testng;

import static junit.framework.Assert.fail;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdnotacjeMetodTest {

	@BeforeClass
	private void przedWszystkimiTestami() {
		System.out.println("<< Przed wszystkimi testami >>");
	}

	@BeforeMethod
	private void przedKazdaMetodaTestowa() {
		System.out.println("<< Przed metoda testowa >>");
	}

	@AfterMethod
	private void poKazdejMetodzieTestowej() {
		System.out.println("<< Po metodzie testowej >>");
	}

	@AfterClass
	private void poWszystkichTestach() {
		System.out.println("<< Po wszystkich testach >>");
	}

	@Test
	public void pierwszyTest() {
		assertTrue(true, "true musi byc prawda");
	}
	
	@Test
	public void negatywnyTest() {
		fail("Nie udalo sie...");
	}
}
