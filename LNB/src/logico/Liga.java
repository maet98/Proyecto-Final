package logico;

import java.util.ArrayList;

public class Liga {
	private ArrayList <Equipo> equipos;
	private ArrayList <Jugador> jugadores;
	private ArrayList <Partido> partidos;
	
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
	}
	
	public Equipo buscarEquipo(String nombre) {
		for(Equipo equipo: equipos) {
			if (equipo.getNombre().equalsIgnoreCase(nombre)) {
				return equipo;
			}
		}
		return null;
	}
	
}
