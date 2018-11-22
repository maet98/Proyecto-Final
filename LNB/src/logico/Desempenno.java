package logico;

import java.io.Serializable;

public class Desempenno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Jugador jugador;
	private int partidosJugados;
	private int tirosDeTres;
	private int tirosLibres;
	private int asistencias;
	private int faltas;
	private int rebotes;
	private int tirosDeDos;

	public Desempenno(Jugador jugador) {
		super();
		this.jugador = jugador;
		this.tirosDeTres = 0;
		this.tirosDeDos = 0;
		this.tirosLibres = 0;
		this.faltas = 0;
		this.asistencias = 0;
		this.rebotes = 0;
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
		this.tirosDeTres += tirosDeTres;
	}
	public int getTirosLibres() {
		return tirosLibres;
	}
	public void setTirosLibres(int tirosLibres) {
		this.tirosLibres += tirosLibres;
	}
	public int getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(int asistencias) {
		this.asistencias += asistencias;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes += rebotes;
	}

	public int getTirosDeDos() {
		return tirosDeDos;
	}

	public void setTirosDeDos(int tirosDeDos) {
		this.tirosDeDos += tirosDeDos;
	}

	public int getPartidosJugados() {
		return partidosJugados;
	}

	public void aumentarPartidosJugados() {
		this.partidosJugados ++;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}



}
