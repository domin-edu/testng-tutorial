package pl.grinder.tutorial.easymock;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.easymock.EasyMock;
import org.testng.annotations.Test;

import pl.grinder.tutorial.testng.domena.KuponZgadula;
import pl.grinder.tutorial.testng.domena.RezultatGry;
import pl.grinder.tutorial.testng.uslugi.AutomatLotto;
import pl.grinder.tutorial.testng.uslugi.impl.AutomatLottoImpl;
import pl.grinder.tutorial.testng.uslugi.impl.MaszynaLosujaca;

public class MockowanieZaleznosciTest {
	@Test
	public void potrafi_zastapic_zaleznosci() {
		// given
		MaszynaLosujaca maszynaMock = EasyMock.createMock(MaszynaLosujaca.class);
		expect(maszynaMock.wylosuj()).andReturn(zbudujWynikLosowania(1,2,3));
		replay(maszynaMock);

		AutomatLotto automat = new AutomatLottoImpl(maszynaMock);
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);

		// when
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
	}

	private HashSet<Integer> zbudujWynikLosowania(int liczba1, int liczba2, int liczba3) {
		return new HashSet<Integer>(Arrays.asList(liczba1, liczba2, liczba3));
	}
}
