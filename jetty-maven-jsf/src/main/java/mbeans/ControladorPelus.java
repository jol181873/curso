package mbeans;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Peluqueria;
import modelo.peluqueria.NegocioPeluqueria;

@ManagedBean(name = "controladorPelus")
@ViewScoped
public class ControladorPelus {

	private List<Peluqueria> lista;

	private List<Peluqueria> pelusFiltradas;

	private String filtroNombre;

	public String getObtenerPelus() {
		NegocioPeluqueria neg = NegocioPeluqueria.getInstance();

		try {
			neg.conectar();

			if (filtroNombre == null || filtroNombre.equals("")) {
				setLista(neg.findAll(neg.getConnection()));
			} else {
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("pelu_nombre", filtroNombre);

				setLista(neg.findParecidos(neg.getConnection(), hash));
				setPelusFiltradas(getLista());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String borrar() {
		try {
			String codigPelu = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("codigoPelu");

			NegocioPeluqueria neg = NegocioPeluqueria.getInstance();

			for (Peluqueria pelu : lista) {
				if (pelu.getPeluId() == Long.valueOf(codigPelu)) {
					neg.borrarObjeto(neg.getConnection(), pelu);
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "loginValido";
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

	public List<Peluqueria> getPelusFiltradas() {
		return pelusFiltradas;
	}

	public void setPelusFiltradas(List<Peluqueria> pelusFiltradas) {
		this.pelusFiltradas = pelusFiltradas;
	}

}
