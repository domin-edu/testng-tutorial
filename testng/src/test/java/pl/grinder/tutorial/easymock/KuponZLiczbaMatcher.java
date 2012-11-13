package pl.grinder.tutorial.easymock;

import org.easymock.IArgumentMatcher;

import pl.grinder.tutorial.testng.domena.KuponZgadula;

public final class KuponZLiczbaMatcher implements IArgumentMatcher {
	private Integer liczba;
	
	public KuponZLiczbaMatcher(Integer liczba) {
		this.liczba = liczba;
	}
	
	public boolean matches(Object argument) {
		if (!(argument instanceof KuponZgadula)) {
			return false;
		}
		
		KuponZgadula kupon = (KuponZgadula) argument;
		return kupon.zawieraLiczbe(liczba);
	}

	public void appendTo(StringBuffer buffer) {
		buffer.append("oczekiwano kuponu z liczba " + liczba);
	}
	
}