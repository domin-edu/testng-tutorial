package pl.grinder.tutorial.testng.domena;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KuponZgadula {
	private int liczba1;
	private int liczba2;
	private int liczba3;

	private KuponZgadula(int liczba1, int liczba2, int liczba3) {
		this.liczba1 = liczba1;
		this.liczba2 = liczba2;
		this.liczba3 = liczba3;
	}
	
	public static KuponZgadula naLiczby(int liczba1, int liczba2, int liczba3) {
		return new KuponZgadula(liczba1, liczba2, liczba3);
	}
	
	public boolean zwycieskiDla(Set<Integer> wynikLosowania) {
		return new HashSet<Integer>(Arrays.asList(liczba1, liczba2, liczba3)).equals(wynikLosowania);
	}
}
