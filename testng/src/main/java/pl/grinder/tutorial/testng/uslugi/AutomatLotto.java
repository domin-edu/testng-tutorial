package pl.grinder.tutorial.testng.uslugi;

import pl.grinder.tutorial.testng.domena.KuponZgadula;
import pl.grinder.tutorial.testng.domena.RezultatGry;

public interface AutomatLotto {
	RezultatGry zagrajWZgadule(KuponZgadula kupon);
}
