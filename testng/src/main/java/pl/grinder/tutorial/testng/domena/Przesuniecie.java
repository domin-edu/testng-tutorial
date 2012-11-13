package pl.grinder.tutorial.testng.domena;

public class Przesuniecie {
	public Integer deltaX;
	public Integer deltaY;

	public Przesuniecie(Integer deltaX, Integer deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	public static Przesuniecie o(Integer deltaX, Integer deltaY) {
		return new Przesuniecie(deltaX, deltaY);
	}
}