package logico;

import java.io.Serializable;

public class Desempenno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Jugador jugador;
	private int tirosDeTres;
	private int tirosLibres;
	private int asistencias;
	private int rebotes;

	public Desempenno(Jugador jugador, int tirosDeTres, int tirosLibres, int asistencias, int rebotes) {
		super();
		this.jugador = jugador;
		this.tirosDeTres = tirosDeTres;
		this.tirosLibres = tirosLibres;
		this.asistencias = asistencias;
		this.rebotes = rebotes;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public int getTirosDeTres() {
		return tirosDeTres;
	}
	public void setTirosDeTres(int tirosDeTres) {
		this.tirosDeTres = tirosDeTres;
	}
	public int getTirosLibres() {
		return tirosLibres;
	}
	public void setTirosLibres(int tirosLibres) {
		this.tirosLibres = tirosLibres;
	}
	public int getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}

}
