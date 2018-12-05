package logico;

import java.util.Comparator;

public class Tiros1Comparator implements Comparator<Jugador> {

	public int compare(Jugador d1, Jugador d2) {
		return Integer.compare(d1.getDesempenno().getTirosLibres(), d2.getDesempenno().getTirosLibres());
	}

}
