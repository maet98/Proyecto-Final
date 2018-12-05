package logico;

import java.util.Comparator;

public class Tiros3Comparator implements Comparator<Jugador>{
	public int compare(Jugador o1, Jugador o2) {
		return Integer.compare(o1.getDesempenno().getTirosDeTres(), o2.getDesempenno().getTirosDeTres());
	}
}
