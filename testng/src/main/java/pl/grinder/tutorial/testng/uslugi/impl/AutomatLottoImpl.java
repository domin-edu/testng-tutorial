package pl.grinder.tutorial.testng.uslugi.impl;

import java.util.Set;

import pl.grinder.tutorial.testng.domena.KuponZgadula;
import pl.grinder.tutorial.testng.domena.RezultatGry;
import pl.grinder.tutorial.testng.uslugi.AutomatLotto;

public class AutomatLottoImpl implements AutomatLotto {
	private MaszynaLosujaca maszynaLosujaca;

	public AutomatLottoImpl(MaszynaLosujaca maszynaLosujaca) {
		this.maszynaLosujaca = maszynaLosujaca;
	}

	public RezultatGry zagrajWZgadule(KuponZgadula kupon) {
		Set<Integer> wynikLosowania = maszynaLosujaca.wylosuj();
		
		return kupon.zwycieskiDla(wynikLosowania) ? RezultatGry.WYGRANA : RezultatGry.PRZEGRANA;
	}
}
