package biblioteca;

import java.io.Serializable;

public class Persona implements Serializable {
	private String nombre;
	private String primApell;
	private String segunApell;
			
	public Persona() {
		
	}
	
	public Persona(String nombre, String primApell, String segunApell) {
		this.nombre = nombre;
		this.primApell = primApell;
		this.segunApell = segunApell;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimApell() {
		return primApell;
	}
	
	public void setPrimApell(String primApell) {
		this.primApell = primApell;
	}
	
	public String getSegunApell() {
		return segunApell;
	}
	
	public void setSegunApell(String segunApell) {
		this.segunApell = segunApell;
	}		
	
	public String getNombreCompleto() {
		return (nombre+" "+primApell+" "+segunApell).trim();
	}
	
	public void rellenarPersona() {
		System.out.println();
		System.out.println();
		System.out.print("Introduzca nombre del usuario           : ");
		this.nombre=Utilidades.getString();
		System.out.print("Introduzca primer apellido del usuario  : ");
		this.primApell=Utilidades.getString();
		System.out.print("Introduzca segundo apellido del usuario : ");
		this.segunApell=Utilidades.getString();		
	}
}
