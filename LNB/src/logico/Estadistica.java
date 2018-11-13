package logico;

import java.util.ArrayList;

public class Estadistica {

	private Partido partido;
	private ArrayList<Desempeno> desempeno;

	public Estadistica(Partido partido) {
		super();
		this.partido = partido;
		desempeno = new ArrayList<>();
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public ArrayList<Desempeno> getDesempeno() {
		return desempeno;
	}

	public void setDesempeno(ArrayList<Desempeno> desempeno) {
		this.desempeno = desempeno;
	}
	
	
}