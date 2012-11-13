package pl.grinder.tutorial.testng.domena;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KuponZgadula {
	private Set<Integer> liczby;

	private KuponZgadula(int liczba1, int liczba2, int liczba3) {
		this.liczby = new HashSet<Integer>(Arrays.asList(liczba1, liczba2, liczba3));
	}
	
	public static KuponZgadula naLiczby(int liczba1, int liczba2, int liczba3) {
		return new KuponZgadula(liczba1, liczba2, liczba3);
	}
	
	public boolean zwycieskiDla(Set<Integer> wynikLosowania) {
		return liczby.equals(wynikLosowania);
	}

	public boolean zawieraLiczbe(int liczba) {
		return liczby.contains(liczba);
	}
}
