package pl.grinder.tutorial.testng.uslugi.impl;

import java.util.Set;

import pl.grinder.tutorial.testng.domena.KuponZgadula;
import pl.grinder.tutorial.testng.domena.RezultatGry;
import pl.grinder.tutorial.testng.uslugi.AutomatLotto;
import pl.grinder.tutorial.testng.uslugi.MaszynaLosujaca;
import pl.grinder.tutorial.testng.uslugi.ZgadulaNotyfikator;

public class AutomatLottoImpl implements AutomatLotto {
	private MaszynaLosujaca maszynaLosujaca;
	private ZgadulaNotyfikator zgadulaNotyfikator;

	public AutomatLottoImpl(MaszynaLosujaca maszynaLosujaca, ZgadulaNotyfikator zgadulaNotyfikator) {
		this.maszynaLosujaca = maszynaLosujaca;
		this.zgadulaNotyfikator = zgadulaNotyfikator;
	}

	public RezultatGry zagrajWZgadule(KuponZgadula kupon) {
		zgadulaNotyfikator.powiadomONowejGrze();

		Set<Integer> wynikLosowania = maszynaLosujaca.wylosuj(1, 45, 3);

		RezultatGry rezultat = kupon.zwycieskiDla(wynikLosowania) ? RezultatGry.WYGRANA : RezultatGry.PRZEGRANA;

		if (RezultatGry.WYGRANA == rezultat) {
			zgadulaNotyfikator.powiadomOWygranej(kupon);
		}

		return rezultat;
	}
}
