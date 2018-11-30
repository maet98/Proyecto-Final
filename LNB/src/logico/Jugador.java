package logico;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Jugador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private String posicion;
	private int edad;
	private int numero;
	private float altura;
	private ImageIcon fotoJugador;
	private boolean lesionado;
	private Lesion lesion;
	private Equipo equipo;
	private Desempenno Desempenno;
	
	public Jugador(String cedula, String nombre, String apellido, String nacionalidad, String posicion, int edad,
			int numero, Equipo equipo,float altura, ImageIcon fotoJugador) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
		this.edad = edad;
		this.numero = numero;
		this.equipo = equipo;
		this.altura = altura;
		this.fotoJugador = fotoJugador;
		this.lesion = null;
		lesionado = false;
		Desempenno = new Desempenno(this);
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public Desempenno getDesempenno() {
		return Desempenno;
	}
	public ImageIcon getFotoJugador() {
		return fotoJugador;
	}
	public void setFotoJugador(ImageIcon fotoJugador) {
		this.fotoJugador = fotoJugador;
	}
	public boolean isLesionado() {
		return lesionado;
	}
	public void setLesionado(boolean lesionado) {
		this.lesionado = lesionado;
	}
	public Lesion getLesion() {
		return lesion;
	}
	public void setLesion(Lesion lesion) {
		this.lesion = lesion;
	}
	
	
}
