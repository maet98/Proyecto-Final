package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Liga implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private ArrayList <Equipo> equipos;
	private ArrayList <Jugador> jugadores;
	private ArrayList <Partido> partidos;
	private int idsPartidos;
	private static Liga liga;
	
	private Liga() {
		super();
		equipos = new ArrayList<>();
		jugadores = new ArrayList<>();
		partidos = new ArrayList<>();
		setIdsPartidos(0);
	}
	
	public static Liga getInstance() {
		if(liga == null) {
			liga = new Liga();
		}
		return liga;
	}
	public static void setInstance(Liga nueva) {
		if(liga == null) {
			liga = nueva;
		}
	}
	
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}
	public void addEquipo(Equipo equipo) {
		this.equipos.add(equipo);
	}
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public void addJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
	public ArrayList<Partido> getPartidos() {
		return partidos;
	}
	public void addPartido(Partido partido) {
		this.partidos.add(partido);
		idsPartidos++;
	}
	
	public Equipo buscarEquipo(String nombre) {
		for(Equipo equipo: equipos) {
			if (equipo.getNombre().equalsIgnoreCase(nombre)) {
				return equipo;
			}
		}
		return null;
	}
	
	public Jugador buscarJugador(String nombre) {
		for(Jugador jugador: jugadores) {
			if(jugador.getNombre().equalsIgnoreCase(nombre)) {
				return jugador;
			}
		}
		return null;
	}
	
	public Jugador buscarJugadorId(String cedula) {
		for(Jugador jugador: jugadores) {
			if(jugador.getCedula().equalsIgnoreCase(cedula)) {
				return jugador;
			}
		}
		return null;
	}

	public int getIdsPartidos() {
		return idsPartidos;
	}

	public void setIdsPartidos(int idsPartidos) {
		this.idsPartidos = idsPartidos;
	}
}
