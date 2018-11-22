package logico;

import java.io.Serializable;
import java.util.Date;

public class Partido implements Serializable{
	private static final long serialVersionUID = 1L;

	private boolean jugado;
	private String id;
	private Date fecha;
	private Equipo local;
	private Equipo visitante;
	private Marcador marcador;
	
	public Partido(String id, Date fecha, Equipo local, Equipo visitante, Marcador marcador) {
		super();
		this.jugado = false;
		this.id = id;
		this.fecha = fecha;
		this.local = local;
		this.visitante = visitante;
		this.marcador = marcador;
	}
	
	
	public boolean isJugado() {
		return jugado;
	}
	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Equipo getLocal() {
		return local;
	}
	public void setLocal(Equipo local) {
		this.local = local;
	}
	public Equipo getVisitante() {
		return visitante;
	}
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	public Marcador getMarcador() {
		return marcador;
	}
	public void setMarcador(Marcador marcador) {
		this.marcador = marcador;
	}
	

}
