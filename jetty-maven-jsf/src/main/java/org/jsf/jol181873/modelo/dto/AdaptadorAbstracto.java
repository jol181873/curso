package org.jsf.jol181873.modelo.dto;

import java.util.ArrayList;
import java.util.List;

public abstract class AdaptadorAbstracto<O, D> {

	public abstract D getDtoDeObjeto(O objeto);

	public abstract O getObjetoDEDto(D objeto);

	public List<D> getListaDtoDeObjeto(List<O> lista) {
		ArrayList<D> resultado = new ArrayList<D>();
		for (O objeto : lista) {
			resultado.add(getDtoDeObjeto(objeto));
		}

		return resultado;
	}
}
