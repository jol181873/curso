package org.jsf.jol181873.repositorio;

import org.jsf.jol181873.modelo.dto.AdaptadorAbstracto;
import org.jsf.jol181873.modelo.dto.PeluqueriaDTO;
import org.jsf.jol181873.modelo.jpa.Peluqueria;

public class AdaptadorPelu extends AdaptadorAbstracto<Peluqueria, PeluqueriaDTO> {

	@Override
	public PeluqueriaDTO getDtoDeObjeto(Peluqueria objeto) {
		if (objeto == null) {
			return null;
		}
		PeluqueriaDTO resultado = new PeluqueriaDTO();
		resultado.setPeluId(objeto.getPeluId());
		resultado.setPeluNombre(objeto.getPeluNombre());
		resultado.setPeluDireccion(objeto.getPeluDireccion());
		resultado.setPeluTelefono(objeto.getPeluTelefono());

		return resultado;
	}

	@Override
	public Peluqueria getObjetoDEDto(PeluqueriaDTO objeto) {
		if (objeto == null) {
			return null;
		}
		Peluqueria resultado = new Peluqueria();
		resultado.setPeluId(objeto.getPeluId());
		resultado.setPeluNombre(objeto.getPeluNombre());
		resultado.setPeluDireccion(objeto.getPeluDireccion());
		resultado.setPeluTelefono(objeto.getPeluTelefono());

		return resultado;
	}
}
