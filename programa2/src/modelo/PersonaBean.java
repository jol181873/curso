package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonaBean extends BeanAbstracto implements Serializable {
	private String nombre;
	private String correo;
	private String password;
	private String sexo;
	private int edad;
	private String observaciones;
	private String aficiones;

	public PersonaBean() {
		super();
	}

	public ArrayList<String> validar() {

		ArrayList<String> resultado = new ArrayList<String>();

		if (getNombre() == null || getNombre().equals("")) {
			resultado.add("El nombre es obligatorio");
		}

		if (getCorreo() == null || getCorreo().equals("")) {
			resultado.add("El correo es obligatorio");
		} else if (getCorreo().indexOf("@") == -1 || getCorreo().indexOf(".") == -1
				|| getCorreo().indexOf("@") != getCorreo().lastIndexOf("@")) {
			resultado.add("El correo no es una dirección válida");
		}

		if (getPassword() == null || getPassword().equals("")) {
			resultado.add("El password es obligatorio");
		} else if (getPassword().length() < 5) {
			resultado.add("El password debe tener al menos 5 caracteres");
		}

		if (getSexo() == null || getSexo().equals("")) {
			resultado.add("El sexo es obligatorio");
		}

		return resultado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreProperty(String nombre) {
		pcs.firePropertyChange("nombre", this.nombre, nombre);
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setCorreoProperty(String correo) {
		pcs.firePropertyChange("correo", this.correo, correo);
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordProperty(String password) {
		pcs.firePropertyChange("password", this.password, password);
		this.password = password;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setSexoProperty(String sexo) {
		pcs.firePropertyChange("sexo", this.sexo, sexo);
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setEdadProperty(int edad) {
		pcs.firePropertyChange("edad", this.edad, edad);
		this.edad = edad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setObservacionesProperty(String observaciones) {
		pcs.firePropertyChange("observaciones", this.observaciones, observaciones);
		this.observaciones = observaciones;
	}

	public String getAficiones() {
		return aficiones;
	}

	public void setAficiones(String aficiones) {
		this.aficiones = aficiones;
	}

	public void setAficionesProperty(String aficiones) {
		pcs.firePropertyChange("aficiones", this.aficiones, aficiones);
		this.aficiones = aficiones;
	}

	@Override
	public boolean equals(Object otro) {
		if (!(otro instanceof PersonaBean)) {
			return false;
		}

		PersonaBean otra = (PersonaBean) otro;

		return otra.getNombre().toLowerCase().equals(nombre.toLowerCase());
	}
}
