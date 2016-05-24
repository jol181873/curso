package ejemplos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Persona implements Serializable{
	private String dni;
	private String nombre;
	private Calendar fechaNacimiento = Calendar.getInstance();
	public Persona(){
		
	}
	
	public Persona(String dni, String nombre, Calendar fechaNacimiento){
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	
}
