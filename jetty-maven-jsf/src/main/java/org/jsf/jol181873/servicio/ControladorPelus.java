package org.jsf.jol181873.servicio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
//import javax.annotation.ManagedBean;
//import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

import org.jsf.jol181873.modelo.dto.PeluqueriaDTO;
import org.jsf.jol181873.repositorio.jpa.RepoPeluqueriaJPA;

@ManagedBean
@RequestScoped
public class ControladorPelus {

	private List<PeluqueriaDTO> lista;

	private List<PeluqueriaDTO> pelusFiltradas;

	private String filtroNombre;

	RepoPeluqueriaJPA repoPeluqueriaJPA;

	public String getObtenerPelus() {
		RepoPeluqueriaJPA neg = RepoPeluqueriaJPA.getInstance();

		setLista(neg.obtenerTodoLosObjetos());

		return null;
	}

	public String borrar() {
		String codigPelu = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigoPelu");

		// RepoPeluqueriaJPA neg = RepoPeluqueriaJPA.getInstance();

		for (PeluqueriaDTO pelu : lista) {
			if (pelu.getPeluId() == Long.valueOf(codigPelu)) {
				repoPeluqueriaJPA.borrarObjeto(pelu);
				break;
			}
		}

		return "loginValido";
	}

	public List<PeluqueriaDTO> getLista() {
		return lista;
	}

	public void setLista(List<PeluqueriaDTO> lista) {
		this.lista = lista;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public List<PeluqueriaDTO> getPelusFiltradas() {
		return pelusFiltradas;
	}

	public void setPelusFiltradas(List<PeluqueriaDTO> pelusFiltradas) {
		this.pelusFiltradas = pelusFiltradas;
	}

}
