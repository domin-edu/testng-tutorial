package pl.grinder.tutorial.testng.uslugi;

import java.util.Set;

public interface MaszynaLosujaca {
	Set<Integer> wylosuj(int min, int max, int ile);
	Set<Integer> wylosuj(int max, int ile);
}
