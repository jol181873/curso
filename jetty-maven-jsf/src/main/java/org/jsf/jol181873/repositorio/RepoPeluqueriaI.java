package org.jsf.jol181873.repositorio;

import java.util.List;

import org.jsf.jol181873.modelo.dto.PeluqueriaDTO;

public interface RepoPeluqueriaI extends RepoAbstracto<PeluqueriaDTO> {
	@Override
	public PeluqueriaDTO obtenerObjeto(PeluqueriaDTO objeto);

	@Override
	public List<PeluqueriaDTO> obtenerTodoLosObjetos();

	@Override
	public void insertarObjeto(PeluqueriaDTO objeto);

	@Override
	public void modificarObjeto(PeluqueriaDTO objeto);

	@Override
	public void borrarObjeto(PeluqueriaDTO objeto);
}
