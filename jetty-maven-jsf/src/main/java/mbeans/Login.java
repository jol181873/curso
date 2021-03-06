package mbeans;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import modelo.usuario.NegocioUsuario;
import modelo.usuario.Usuario;

@ManagedBean(name = "login", eager = true)
@SessionScoped
public class Login implements Serializable {
	private String nombreUsuario;
	private String password;

	@NotBlank
	@Size(min = 1, max = 50)
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String name) {
		this.nombreUsuario = name;
	}

	@NotBlank
	@Size(min = 1, max = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		Usuario usuario = new Usuario(nombreUsuario, password);

		try {
			NegocioUsuario negocioUsuario = NegocioUsuario.getInstance();

			negocioUsuario.conectar();
			Usuario usuarioLogueado = negocioUsuario.obtenerObjeto(negocioUsuario.getConnection(),
					"SELECT * FROM USUARIO WHERE USUA_NOMBRE=?", nombreUsuario);

			if (usuarioLogueado == null) {
				// no es un nombre de usuario correcto
				FacesContext.getCurrentInstance().addMessage("miform:username",
						new FacesMessage("El usuario no existe"));

				return null;
			} else {
				if (!usuarioLogueado.esCorrecto(usuario.getPassword())) {
					FacesContext.getCurrentInstance().addMessage("miform:password",
							new FacesMessage("Password incorrecto"));

					return null;
				} else {
					return "loginValido";
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}
}