package logico;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Equipo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Jugador> jugadores;
	private String Entrenador;
	private String nombre;
	private String estadio;
	private String ciudad;
	private ImageIcon logo;
	public Equipo( String entrenador, String nombre, String estadio,
			String ciudad,ImageIcon logo) {
		super();
		this.jugadores = new ArrayList<>();
		Entrenador = entrenador;
		this.nombre = nombre;
		this.estadio = estadio;
		this.ciudad = ciudad;
		this.logo = logo;
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
	public ImageIcon getLogo() {
		return logo;
	}
	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}
	public boolean estaOcupado(int actual) {
		boolean ocupado = false;
		int i = 0;
		while(!ocupado && i < jugadores.size()) {
			if(actual == jugadores.get(i).getNumero()) {
				ocupado = true;
			}
			i++;
		}
		return ocupado; 
	}
	
	
}
