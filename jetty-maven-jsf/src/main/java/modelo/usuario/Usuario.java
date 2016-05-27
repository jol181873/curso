package modelo.usuario;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import modelo.Bean;
import utilidades.Utilidades;

public class Usuario extends Bean<Usuario> {
	private Set<ConstraintViolation<Usuario>> validatePassword;

	private String id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nombre;

	private String password;

	public Usuario() {

	}

	public Usuario(String nombr, String password) {
		this.nombre = nombr;
		this.setPassword(password);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombrLogin) {
		this.nombre = nombrLogin;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Se guarda el hash md5 del password, si se pierde la contrase�a, es
	 * irrecuperable
	 * 
	 * @param nombre
	 */

	public void setPassword(@NotBlank @Size(min = 1, max = 50) String password) {
		validatePassword = Utilidades.validarMetodo(this, "setPassword", new Class<?>[] { String.class }, password);

		// this.password = Utilidades.getMd5(password);
		this.password = password;
	}

	public boolean esCorrecto(String otraPass) {
		// return this.password.equals(Utilidades.getMd5(otraPass));
		return this.password.equals(otraPass);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Set<ConstraintViolation<Usuario>> validar() {
		Set<ConstraintViolation<Usuario>> constraintViolations = super.validar();
		constraintViolations.addAll(validatePassword);

		logErroresValidacion(validatePassword, METODOS);

		return constraintViolations;
	}
}
