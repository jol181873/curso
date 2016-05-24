package mbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "utilidades", eager = true)
@SessionScoped
public class Utilidades implements Serializable {
	public void cerrarSesion(ActionEvent ae) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.invalidate();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("hello.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}