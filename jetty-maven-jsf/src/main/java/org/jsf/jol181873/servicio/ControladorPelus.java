package org.jsf.jol181873.servicio;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jsf.jol181873.modelo.dto.PeluqueriaDTO;
import org.jsf.jol181873.repositorio.RepoPeluqueriaI;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
public class ControladorPelus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6050437226279422404L;

	private List<PeluqueriaDTO> lista;

	@Inject
	RepoPeluqueriaI repoPeluqueria;

	public String getObtenerPelus() {
		// RepoPeluqueriaJPA neg = RepoPeluqueriaJPA.getInstance();

		setLista(repoPeluqueria.obtenerTodoLosObjetos());

		return null;
	}

	public String borrar() {
		String codigPelu = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigoPelu");

		PeluqueriaDTO peluABorrar = null;
		for (PeluqueriaDTO pelu : getLista()) {
			if (pelu.getPeluId() == Long.valueOf(codigPelu)) {
				repoPeluqueria.borrarObjeto(pelu);
				peluABorrar = pelu;
				break;
			}
		}

		lista.remove(peluABorrar);

		return "loginValido";
	}

	public String nuevo() {
		FacesContext.getCurrentInstance().addMessage("miform:msgs", new FacesMessage("Pelu creada"));

		PeluqueriaDTO pelu = new PeluqueriaDTO();

		repoPeluqueria.insertarObjeto(pelu);

		lista.add(pelu);

		return "loginValido";
	}

	public void onRowEdit(RowEditEvent event) {
		FacesContext.getCurrentInstance().addMessage("miform:msgs", new FacesMessage("Pelu editada"));

		PeluqueriaDTO peluDTO = (PeluqueriaDTO) event.getObject();

		repoPeluqueria.modificarObjeto(peluDTO);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesContext.getCurrentInstance().addMessage("miform:msgs", new FacesMessage("Edición Cancelada"));

	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage("miform:msgs", msg);
		}
	}

	public List<PeluqueriaDTO> getLista() {
		return lista;
	}

	public void setLista(List<PeluqueriaDTO> lista) {
		this.lista = lista;
	}

}
