package jdbc.p2;

public class Tabla {
	String nombre;
	String apellido;
	String dni;
	String fnac;
	
	
	public Tabla(String nombre, String apellido, String dni, String fnac) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fnac = fnac;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFnac() {
		return fnac;
	}
	public void setFnac(String fnac) {
		this.fnac = fnac;
	}
}
