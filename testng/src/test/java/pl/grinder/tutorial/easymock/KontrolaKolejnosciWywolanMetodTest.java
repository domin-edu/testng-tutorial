package pl.grinder.tutorial.easymock;

import static org.easymock.EasyMock.checkOrder;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
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

public class KontrolaKolejnosciWywolanMetodTest {
	@Test
	public void utworzenie_mocka_i_zastapienie_zaleznosci() {
		// given
		MaszynaLosujaca maszynaMock = createMock(MaszynaLosujaca.class);
		ZgadulaNotyfikator notyfikatorMock = createNiceMock(ZgadulaNotyfikator.class);
		AutomatLotto automat = new AutomatLottoImpl(maszynaMock, notyfikatorMock);

		expect(maszynaMock.wylosuj(1, 45, 3)).andReturn(zbudujWynikLosowania(1, 2, 3));
		replay(maszynaMock);
		
		KuponZgadula kupon = KuponZgadula.naLiczby(1, 2, 3);

		checkOrder(notyfikatorMock, true);
		notyfikatorMock.powiadomOWygranej(kupon);
		expectLastCall().times(1); 
		notyfikatorMock.powiadomONowejGrze();
		expectLastCall().times(1); 
		
		replay(notyfikatorMock);

		// when
		RezultatGry rezultat = automat.zagrajWZgadule(kupon);

		// then
		assertEquals(rezultat, RezultatGry.WYGRANA);
		verify(notyfikatorMock);
	}
	
	private HashSet<Integer> zbudujWynikLosowania(Integer... liczby) {
		return new HashSet<Integer>(Arrays.asList(liczby));
	}
}
