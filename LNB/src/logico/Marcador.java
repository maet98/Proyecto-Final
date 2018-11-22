package logico;

import java.io.Serializable;

public class Marcador implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int local;
	private int visitante;
	
	public Marcador(int local, int visitante) {
		super();
		this.local = local;
		this.visitante = visitante;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public int getVisitante() {
		return visitante;
	}

	public void setVisitante(int visitante) {
		this.visitante = visitante;
	}
	
	
}
