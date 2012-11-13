package pl.grinder.tutorial.easymock;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pl.grinder.tutorial.easymock.KuponZgadulaMatchers.kuponZLiczba;

import java.util.Arrays;
import java.util.HashSet;

import org.easymock.Capture;
import org.easymock.CaptureType;
import org.testng.annotations.Test;

import pl.grinder.tutorial.testng.domena.KuponZgadula;
import pl.grinder.tutorial.testng.domena.RezultatGry;
import pl.grinder.tutorial.testng.uslugi.AutomatLotto;
import pl.grinder.tutorial.testng.uslugi.MaszynaLosujaca;
import pl.grinder.tutorial.testng.uslugi.ZgadulaNotyfikator;
import pl.grinder.tutorial.testng.uslugi.impl.AutomatLottoImpl;

public class DopasowywanieArgumentowTest {
	@Test
	public void dopasowywanie_argumentu_do_zadanej_klasy() {
		// given
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		ZgadulaNotyfikator notyfikatorMock = createNiceMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andReturn(zbudujWynikLosowania(1, 2, 3));
		replay(maszynaMock);
		
		notyfikatorMock.powiadomONowejGrze();
		notyfikatorMock.powiadomOWygranej(isA(KuponZgadula.class));
		// dopasowywanie argumentow - istnieje wiele predefiniowanych
		// - eq(X value)
		// - anyBoolean(), anyByte(), anyChar(), anyDouble(), anyFloat(), anyInt(), anyLong(), anyObject(), anyShort()
		// - eq(X value, X delta)
		// - aryEq(X value) (=> Arrays.equals)
		// - isNull(), notNull()
		// - same(X value)
		// - isA(Class clazz)
		// - lt(X value), leq(X value), geq(X value), gt(X value)
		// - startsWith(String prefix), contains(String substring), endsWith(String suffix)
		// - matches(String regex), find(String regex)
		// - and(X first, X second), or(X first, X second), not(X value)
		// - cmpEq(X value)
		// - capture(Capture<T> capture)
		
		replay(notyfikatorMock);

		// when
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
		verify(notyfikatorMock);
	}
	
	@Test
	public void przechwytywanie_parametrow_przekazywanych_do_mocka() {
		// given
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		ZgadulaNotyfikator notyfikatorMock = createNiceMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andReturn(zbudujWynikLosowania(1, 2, 3));
		replay(maszynaMock);
		
		notyfikatorMock.powiadomONowejGrze();
		Capture<KuponZgadula> kuponCapture = new Capture<KuponZgadula>(CaptureType.FIRST);
		// pozwala na przechwycenie argumentu, mozliwe typy:
		// - FIRST
		// - LAST (domyslny)
		// - ALL
		// - NONE
		notyfikatorMock.powiadomOWygranej(capture(kuponCapture));
	
		replay(notyfikatorMock);

		// when
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
		KuponZgadula przekazanyKupon = kuponCapture.getValue();
		assertTrue(przekazanyKupon.zawieraLiczbe(1), "kupon zawiera liczbe 1");
		assertTrue(przekazanyKupon.zawieraLiczbe(2), "kupon zawiera liczbe 2");
		assertTrue(przekazanyKupon.zawieraLiczbe(3), "kupon zawiera liczbe 3");
	}
	
	@Test
	public void dopasowywanie_argumentu_na_podstawienie_wlasnego_matchera() {
		// given
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		ZgadulaNotyfikator notyfikatorMock = createNiceMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andReturn(zbudujWynikLosowania(1, 2, 3));
		replay(maszynaMock);
		
		notyfikatorMock.powiadomONowejGrze();
		notyfikatorMock.powiadomOWygranej(kuponZLiczba(4));
		
		replay(notyfikatorMock);

		// when
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
		verify(notyfikatorMock);
	}
	
	private HashSet<Integer> zbudujWynikLosowania(Integer... liczby) {
		return new HashSet<Integer>(Arrays.asList(liczby));
	}
}
