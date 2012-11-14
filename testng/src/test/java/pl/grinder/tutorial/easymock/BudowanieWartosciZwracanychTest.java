package pl.grinder.tutorial.easymock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.easymock.EasyMock.replay;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.IAnswer;
import org.testng.annotations.Test;

public class BudowanieWartosciZwracanychTest {
	@SuppressWarnings({ "unchecked", "serial" })
	@Test
	public void zwracanie_jako_rezultat_argumentu() {
		List<String> listMock = createMock(List.class);

		expect(listMock.remove(1)).andAnswer(new IAnswer<String>() {
			public String answer() throws Throwable {
				return getCurrentArguments()[0].toString();
			}
		});

		expect(listMock.remove(2)).andDelegateTo(new ArrayList<String>() {
			@Override
			public String remove(int index) {
				return Integer.toString(index);
			}
		});
		
		replay(listMock);
		
		// when
		String usun1 = listMock.remove(1);
		String usun2 = listMock.remove(2);
		
		// then
		assertEquals(usun1, "1");
		assertEquals(usun2, "2");
	}
}
