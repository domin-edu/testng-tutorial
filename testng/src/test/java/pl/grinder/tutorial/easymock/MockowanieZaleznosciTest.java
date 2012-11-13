package pl.grinder.tutorial.easymock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.testng.annotations.Test;

import pl.grinder.tutorial.testng.domena.KuponZgadula;
import pl.grinder.tutorial.testng.domena.RezultatGry;
import pl.grinder.tutorial.testng.uslugi.AutomatLotto;
import pl.grinder.tutorial.testng.uslugi.MaszynaLosujaca;
import pl.grinder.tutorial.testng.uslugi.ZgadulaNotyfikator;
import pl.grinder.tutorial.testng.uslugi.impl.AutomatLottoImpl;

public class MockowanieZaleznosciTest {
	@Test
	public void utworzenie_mocka_i_zastapienie_zaleznosci() {
		// given
		// mock - gdy nie zdefiniujemy wartosci zwracanej, a wywolanie nastapi => wyjatek
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		// niceMock - gdy nie zdefiniujmy wartosci zwracanej, a wywolanie nastapi => zwroci 0, null lub false, brak bledu
		ZgadulaNotyfikator notyfikatorMock = createNiceMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andReturn(zbudujWynikLosowania(1, 2, 3));
		replay(maszynaMock);

		// when
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
		// maszynaMock traktowany jako stub - nie weryfikujemy czy nagrane
		// zachowanie zostalo odtworzone
	}

	@Test
	public void utworzenie_mocka_i_sprawdzenie_kolejnosci_wywolan_metod() {
		// given
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		ZgadulaNotyfikator notyfikatorMock = createMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andReturn(zbudujWynikLosowania(1, 2, 3));
		expect(maszynaMock.wylosuj(1, 45, 1)).andReturn(zbudujWynikLosowania(4, 5, 6)).times(2);
		replay(maszynaMock);

		// when
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
		// jest jedno wywolanie a zadeklarowalismy dwa, wiec weryfikacja sie nie powiedzie
		// maszynaMock traktowany jako prawdziwy mock
		verify(maszynaMock);
	}

	private HashSet<Integer> zbudujWynikLosowania(Integer... liczby) {
		return new HashSet<Integer>(Arrays.asList(liczby));
	}
}
