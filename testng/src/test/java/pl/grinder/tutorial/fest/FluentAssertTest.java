package pl.grinder.tutorial.fest;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static pl.grinder.tutorial.fest.FluentAssertTest.PunktAssert.assertThat;
import static pl.grinder.tutorial.fest.FluentAssertTest.UpperCaseCondition.uppercase;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.fest.assertions.Condition;
import org.fest.assertions.GenericAssert;
import org.testng.annotations.Test;

import pl.grinder.tutorial.testng.domena.Punkt;

public class FluentAssertTest {
	public static class UpperCaseCondition extends Condition<String> {

		@Override
		public boolean matches(String arg) {
			return arg.equals(arg.toUpperCase());
		}

		public static UpperCaseCondition uppercase() {
			return new UpperCaseCondition();
		}
	}

	public static class PunktAssert extends GenericAssert<PunktAssert, Punkt> {

		public PunktAssert(Punkt actual) {
			super(PunktAssert.class, actual);
		}

		public static PunktAssert assertThat(Punkt punkt) {
			return new PunktAssert(punkt);
		}
		
		public PunktAssert jestPoczatkiemUkladuWspolrzednych() {
			isNotNull();
			String opisBledu = String.format("Spodziewano sie punktu:  %s; otrzymano: %s", Punkt.poczatekUkladu(), actual);
			assertThat(actual).overridingErrorMessage(opisBledu).isEqualTo(Punkt.poczatekUkladu());
			
			return this;
		}
	}

	@Test
	public void fluent_interface() {
		assertThat(BigDecimal.ZERO).isZero();
		assertThat("test").isNotEmpty();
		assertThat(Arrays.asList(1, 2, 3)).contains(3, 2, 1);
		assertThat(2 > 1).isTrue();

		Map<Integer, String> mapa = new HashMap<>();
		mapa.put(1, "jeden");
		mapa.put(2, "dwa");
		assertThat(mapa).overridingErrorMessage("mapa zawiera dwa elementy").hasSize(2);
		assertThat(mapa).includes(entry(1, "jeden")).excludes(entry(3, "trzy"));
	}

	@Test
	public void umozliwia_definiowanie_wlasnych_warunkow_dla_asercji() {
		assertThat("TEST").is(uppercase());
	}
	
	@Test
	public void umozliwa_definiowanie_wlasnych_asercji() {
		assertThat(Punkt.oWspolrzednych(0, 1)).jestPoczatkiemUkladuWspolrzednych();
	}
}
