package logico;

import java.util.ArrayList;

public class Equipo {
	private ArrayList<Jugador> jugadores;
	private String Entrenador;
	private String nombre;
	private String circuito;
	private String estadio;
	private String ciudad;
	public Equipo( String entrenador, String nombre, String circuito, String estadio,
			String ciudad) {
		super();
		this.jugadores = new ArrayList<>();
		Entrenador = entrenador;
		this.nombre = nombre;
		this.circuito = circuito;
		this.estadio = estadio;
		this.ciudad = ciudad;
	}
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public String getEntrenador() {
		return Entrenador;
	}
	public void setEntrenador(String entrenador) {
		Entrenador = entrenador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCircuito() {
		return circuito;
	}
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	public String getEstadio() {
		return estadio;
	}
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
}
