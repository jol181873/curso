package org.jsf.jol181873.servicio;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

@ManagedBean
@ApplicationScoped
public class Utilidades implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6738878530442505232L;

	public void cerrarSesion(ActionEvent ae) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.invalidate();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("hello.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}