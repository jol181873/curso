package org.jsf.jol181873.repositorio;

import java.util.List;

import javax.inject.Named;

import org.jsf.jol181873.modelo.dto.UsuarioDTO;

@Named("repoUsuario")
public interface RepoUsuarioI extends RepoAbstracto<UsuarioDTO> {
	@Override
	public UsuarioDTO obtenerObjeto(UsuarioDTO objeto);

	@Override
	public List<UsuarioDTO> obtenerTodoLosObjetos();

	@Override
	public void insertarObjeto(UsuarioDTO objeto);

	@Override
	public void modificarObjeto(UsuarioDTO objeto);

	@Override
	public void borrarObjeto(UsuarioDTO objeto);

}
