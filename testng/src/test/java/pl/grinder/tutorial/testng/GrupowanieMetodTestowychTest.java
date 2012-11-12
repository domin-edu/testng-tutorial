package pl.grinder.tutorial.testng;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

public class GrupowanieMetodTestowychTest {
	@Test(groups="wolne") 
	public void wolny_test_1() throws InterruptedException {
		Thread.sleep(TimeUnit.SECONDS.toMillis(1));
		
		assertTrue(1 < 2);
	}
	
	@Test(groups="wolne") 
	public void wolny_test_2() throws InterruptedException {
		Thread.sleep(TimeUnit.SECONDS.toMillis(2));
		
		assertTrue(1 < 2);
	}
	
	@Test(groups="wolne") 
	public void wolny_test_3() throws InterruptedException {
		Thread.sleep(TimeUnit.SECONDS.toMillis(3));
		
		assertTrue(1 < 2);
	}
	
	@Test(groups="szybkie") 
	public void szybki_test_1() {
		assertTrue(1 < 2);
	}
	
	@Test(groups="szybkie") 
	public void szybki_test_2() {
		assertTrue(1 < 2);
	}
	
	@Test(groups="szybkie") 
	public void szybki_test_3() {
		assertTrue(1 < 2);
	}
}
