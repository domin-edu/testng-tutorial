package pl.grinder.tutorial.easymock;

import org.easymock.EasyMock;

import pl.grinder.tutorial.testng.domena.KuponZgadula;

public class KuponZgadulaMatchers {
	public static KuponZgadula kuponZLiczba(Integer liczba) {
		EasyMock.reportMatcher(new KuponZLiczbaMatcher(liczba));
		return null;
	}
}
