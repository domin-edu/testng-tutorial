package pl.grinder.tutorial.testng;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

public class AdnotacjaTestDodatkoweOpcjeTest {
	public static Random RANDOM = new Random(125125123);
	
	@Test(enabled = false, description = "test wylaczony gdyz jeszcze nie jest gotowy")
	public void wylaczony_test() {
		fail("jeszcze nie gotowe...");
	}

	@Test
	public void pozytywny_test() {
		assertTrue(1 < 2);
	}

	@Test(dependsOnMethods = "pozytywny_test")
	public void test_wykonywany_gdy_metoda_od_ktorej_zalezy_zostala_wykonana_bez_bledow() {
		assertTrue(1 < 3);
	}

	@Test
	public void negatywny_test() {
		assertTrue(1 > 2);
	}

	@Test(dependsOnMethods = "negatywny_test")
	public void test_nie_jest_wykonywany_gdy_metoda_od_ktorej_zalezy_zostala_wykonana_z_bledami() {
		assertTrue(1 > 3);
	}

	@Test(dependsOnMethods = "negatywny_test", alwaysRun = true)
	public void test_jest_wykonywany_nawet_gdy_nie_powinien_byc_gdy_aktywna_flaga_wykonywania_bez_wzledu_na_wszystko() {
		assertTrue(1 > 3);
	}
	
	@Test(invocationCount=50) 
	public void test_wykonywany_wielokrotnie() {
		// when
		int wylosowana = RANDOM.nextInt(5);
		
		// then
		assertTrue(wylosowana >= 0 && wylosowana < 5, "zawsze losowana jest liczba z przedzialu [0;5)");
	}
	
	@Test(timeOut=100) 
	public void oczekuje_maksymalnie_100ms_na_zakonczenie_testu() throws InterruptedException {
		// given
		// symulacja dluzszego przeliczania
		Thread.sleep(TimeUnit.SECONDS.toMillis(1));
		
		// then
		assertTrue(1 < 2);
	}
}
