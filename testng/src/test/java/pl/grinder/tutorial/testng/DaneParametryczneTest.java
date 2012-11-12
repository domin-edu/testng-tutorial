package pl.grinder.tutorial.testng;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pl.grinder.tutorial.testng.domena.Punkt;

public class DaneParametryczneTest {
	
	@Test(dataProvider="przesunieciePunktuDataProvider")
	public void potrafi_wyznaczyc_wspolrzedne_punktu_po_przesunieciu(Punkt start, Integer deltaX, Integer deltaY, Punkt oczekiwany) {
		// when
		Punkt przesuniety = start.przesunO(deltaX, deltaY);
		
		// then
		assertEquals(przesuniety, oczekiwany);
	}
	
	@DataProvider(name="przesunieciePunktuDataProvider")
	public Object[][] przesunieciePunktuDataProvider() {
		return new Object[][] {
				{ Punkt.oWspolrzednych(1, 1), -1, -1, Punkt.oWspolrzednych(0, 0) },
				{ Punkt.oWspolrzednych(0, 0), -1, -1, Punkt.oWspolrzednych(-1, -1) },
				{ Punkt.oWspolrzednych(0, 0), 1, -1, Punkt.oWspolrzednych(1, -1) }
		};
	}
}
