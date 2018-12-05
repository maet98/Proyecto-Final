package logico;

import java.util.Comparator;

public class tiros2Comparator implements Comparator<Jugador>{
	public int compare(Jugador j1, Jugador j2) {
		return Integer.compare(j1.getDesempenno().getTirosDeDos(), j2.getDesempenno().getTirosDeDos());
	}

}
