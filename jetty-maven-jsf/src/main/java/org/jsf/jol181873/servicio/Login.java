package org.jsf.jol181873.servicio;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
//import javax.annotation.ManagedBean;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jsf.jol181873.modelo.dto.UsuarioDTO;
import org.jsf.jol181873.repositorio.jdbc.RepoUsuarioJDBC;

@Dependent
@Named
@ManagedBean
@ApplicationScoped
public class Login implements Serializable {
	// private String nombreUsuario;
	// private String password;
	//
	// @NotBlank
	// @Size(min = 1, max = 50)
	// public String getNombreUsuario() {
	// return nombreUsuario;
	// }
	//
	// public void setNombreUsuario(String name) {
	// this.nombreUsuario = name;
	// }
	//
	// @NotBlank
	// @Size(min = 1, max = 50)
	// public String getPassword() {
	// return password;
	// }
	//
	// public void setPassword(String password) {
	// this.password = password;
	// }

	private UsuarioDTO usuario = new UsuarioDTO();

	@Inject
	@Named("repoUsuarioJDBC")
	public RepoUsuarioJDBC repoUsuarioJDBC;

	public String login() {
		UsuarioDTO usuarioLogueado = repoUsuarioJDBC.obtenerObjeto(getUsuario());

		if (usuarioLogueado == null) {
			// no es un nombre de usuario correcto
			FacesContext.getCurrentInstance().addMessage("miform:username", new FacesMessage("El usuario no existe"));

			return null;
		} else {
			if (!usuarioLogueado.esCorrecto(getUsuario().getPassword())) {
				FacesContext.getCurrentInstance().addMessage("miform:password",
						new FacesMessage("Password incorrecto"));

				return null;
			} else {
				return "loginValido";
			}
		}
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}