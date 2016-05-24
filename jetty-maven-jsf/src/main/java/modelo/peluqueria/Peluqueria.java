package modelo.peluqueria;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import modelo.Bean;

public class Peluqueria extends Bean<Peluqueria> {
	private String id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nombre;

	@NotBlank
	@Size(min = 1, max = 100)
	private String direccion;

	@NotBlank
	@Size(min = 6, max = 12)
	private String telefono;

	public Peluqueria() {

	}

	public Peluqueria(String nombr, String direccion, String telefono) {
		this.nombre = nombr;
		this.setDireccion(direccion);
		this.setTelefono(telefono);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombrLogin) {
		this.nombre = nombrLogin;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
