package logico;

public class Jugador {
	private String cedula;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private String posicion;
	private int edad;
	private int numero;
	private float altura;
	
	private Equipo equipo;
	private Desempeno Desempenno;
	public Jugador(String cedula, String nombre, String apellido, String nacionalidad, String posicion, int edad,
			int numero, Equipo equipo, Desempeno desempenno,float altura) {
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
		Desempenno = desempenno;
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
	public Desempeno getDesempenno() {
		return Desempenno;
	}
	
}
