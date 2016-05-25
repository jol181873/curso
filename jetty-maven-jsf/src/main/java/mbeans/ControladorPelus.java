package mbeans;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.peluqueria.NegocioPeluqueria;
import modelo.peluqueria.Peluqueria;

@ManagedBean(name = "controladorPelus")
@SessionScoped
public class ControladorPelus {

	private List<Peluqueria> lista;

	private String filtroNombre;

	public String obtenerPelus() {
		NegocioPeluqueria neg = NegocioPeluqueria.getInstance();

		try {
			neg.conectar();

			System.out.println("XXXXXXXX " + filtroNombre);

			if (filtroNombre == null || filtroNombre.equals("")) {
				setLista(neg.findAll(neg.getConnection()));
			} else {
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("pelu_nombre", filtroNombre);

				setLista(neg.findParecidos(neg.getConnection(), hash));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Peluqueria> getLista() {
		return lista;
	}

	public void setLista(List<Peluqueria> lista) {
		this.lista = lista;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

}
