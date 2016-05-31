package org.jsf.jol181873.servicio;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Utilidades implements Serializable {
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