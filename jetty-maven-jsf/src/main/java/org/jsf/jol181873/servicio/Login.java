package org.jsf.jol181873.servicio;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
//import javax.annotation.ManagedBean;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jsf.jol181873.modelo.dto.UsuarioDTO;
import org.jsf.jol181873.repositorio.jdbc.RepoUsuarioJDBC;

@RequestScoped
@Named
public class Login implements Serializable {
	private UsuarioDTO usuario = new UsuarioDTO();

	@Inject
	@Named("repoUsuarioJDBC")
	private RepoUsuarioJDBC repoUsuarioJDBC;

	public String login(String nombre, String pass) {
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