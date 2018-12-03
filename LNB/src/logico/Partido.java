package logico;

import java.io.Serializable;
import java.util.Date;

public class Partido implements Serializable, Comparable <Partido>{
	private static final long serialVersionUID = 1L;
	private boolean jugado;
	private String id;
	private Date fecha;
	private Equipo local;
	private Equipo visitante;
	private Marcador marcador;
	
	public Partido(String id, Date fecha, Equipo local, Equipo visitante) {
		super();
		this.jugado = false;
		this.id = id;
		this.fecha = fecha;
		this.local = local;
		this.visitante = visitante;
		marcador = null;
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
	public void JugarPartido(Marcador marcador ) {
		if(jugado == false) {
			jugado = true;
			this.marcador= marcador;
		}
	}


	@Override
	public int compareTo(Partido o) {
		//return Integer.compare(this.fecha.getTime(), (Integer) o.fecha.getTime());
		if (this.fecha.getTime()>o.fecha.getTime()) {
			return 1;
		}else {
			return 0;
		}
	}
}
