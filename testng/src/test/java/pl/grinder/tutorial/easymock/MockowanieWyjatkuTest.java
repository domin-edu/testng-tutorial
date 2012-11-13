package pl.grinder.tutorial.easymock;

import static org.easymock.EasyMock.createMock;
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
import pl.grinder.tutorial.testng.uslugi.wyjatki.AutomatLottoException;
import pl.grinder.tutorial.testng.uslugi.wyjatki.NieczynnaMaszynaLosujacaException;

public class MockowanieWyjatkuTest {
	@Test(expectedExceptions = AutomatLottoException.class)
	public void zdefiniowanie_rzucania_wyjatku_dla_mocka() {
		// given
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		ZgadulaNotyfikator notyfikatorMock = createMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andThrow(new NieczynnaMaszynaLosujacaException());
		replay(maszynaMock);

		// when
		automat.zagrajWZgadule(KuponZgadula.naLiczby(1, 2, 3));
	}
}
