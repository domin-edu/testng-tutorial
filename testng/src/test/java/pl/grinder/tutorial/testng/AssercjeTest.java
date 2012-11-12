package pl.grinder.tutorial.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;

import org.testng.annotations.Test;

public class AssercjeTest {
	@Test
	public void strukturaTestu() {
		// given
		String tekst = "SuperTest";

		// when
		String rezultat = tekst.replaceFirst("Super", ""); // => actual

		// then
		String oczekiwany = "Test"; // => expected
		assertEquals(rezultat, oczekiwany); // w odroznieniu od JUnit mamy najpierw rezultat
		                                    // a dopiero pozniej wartosc oczekiwana
	}

	@Test
	public void assercjeNaRownosc() {
		// typy prymitywne
		assertEquals(2 + 3, 5);
		
		// kazda asercja ma przeladowana metode z parametrem typu String do opisu tekstowego
		assertEquals(2 + 3, 5, "2 + 3 = 5");
		
		// tablice typow prymitywnych
		assertEquals(new int[] { 2 + 3, 7 - 2 } , new int[] { 5, 5 } , "2 + 3 = 5, 7 - 2 = 5");
		
		// liczby zmiennoprzecinkowe z tolerancja
		assertEquals(1.0 - (9 * 0.1), 0.1, 0.01);
		
		// porywnywanie tablic - gdy uporzadkowanie nie ma znaczenia
		assertEqualsNoOrder(new String[] { "A", "B" }, new String[] { "B", "A" });
		
		// assercje dla wartosci boolean
		assertTrue(new ArrayList<Object>().isEmpty(), "nowo utworzona tablica jest pusta");
		assertFalse("a".isEmpty(), "lancuch skladajacy sie z jednej litery nie jest pusty");
	}
	
	@Test
	public void pozostaleAssercje() {
		// sprawdzenie null
		assertNull(null);
		assertNotNull("test");
		
		// sprawdzenie rownosci referencji
		assertSame(Integer.valueOf(1), Integer.valueOf(1));
		assertNotSame(new Integer(1), new Integer(1));
		
		// silowe oblanie testu
		fail("To sie nie moglo udac...");
		// UWAGA! nie stosowac w powyzszym celu konstrukcji:
		// assertEquals(true, false);
		// assertTrue(false);
		// assertFalse(true);
	}
}
