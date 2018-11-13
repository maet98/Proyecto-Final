package logico;

import java.util.Date;

public class Lesion {
	
	private String concepto;
	private Jugador jugador;
	private Date fechaInicio;
	private Date fechaFinal;
	
	public Lesion(String concepto, Jugador jugador, Date fechaInicio, Date fechaFinal) {
		super();
		this.concepto = concepto;
		this.jugador = jugador;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
}
