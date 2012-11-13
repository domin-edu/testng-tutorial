package pl.grinder.tutorial.testng.uslugi;

import pl.grinder.tutorial.testng.domena.KuponZgadula;

public interface ZgadulaNotyfikator {

	void powiadomONowejGrze();

	void powiadomOWygranej(KuponZgadula kupon);

}
