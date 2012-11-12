package pl.grinder.tutorial.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

public class TestowanieWyjatkowTest {
	
	@Test(expectedExceptions = ArithmeticException.class)
	public void rzuca_wyjatek_gdy_dzielenie_przez_zero() {
		// given
		int dzielnik = 100;

		// when
		@SuppressWarnings("unused")
		int iloraz = dzielnik / 0;
	}

	@Test
	public void rzuca_wyjatek_z_informacja_gdy_dzielenie_przez_zero() {
		// given
		int dzielnik = 100;

		// when
		try {
			@SuppressWarnings("unused")
			int iloraz = dzielnik / 0;
			
			fail("dzialanie przez 0 nie jest dozwolone");
		} catch (ArithmeticException ae) {
			// then
			assertEquals(ae.getMessage(), "/ by zero");
		}
	}
}
