package pl.grinder.tutorial.testng.domena;


public class Punkt {
	private Integer x;
	private Integer y;

	private Punkt(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public static Punkt oWspolrzednych(Integer x, Integer y) {
		return new Punkt(x, y);
	}

	public Punkt przesunO(Przesuniecie przesuniecie) {
		return new Punkt(x + przesuniecie.deltaX, y + przesuniecie.deltaY);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punkt other = (Punkt) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	public static Punkt poczatekUkladu() {
		return Punkt.oWspolrzednych(0, 0);
	}

	@Override
	public String toString() {
		return "Punkt [x=" + x + ", y=" + y + "]";
	}
}
