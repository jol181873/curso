package org.jsf.jol181873.repositorio;

import org.jsf.jol181873.modelo.dto.AdaptadorAbstracto;
import org.jsf.jol181873.modelo.dto.UsuarioDTO;
import org.jsf.jol181873.modelo.jpa.Usuario;

public class AdaptadorUsuario extends AdaptadorAbstracto<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO getDtoDeObjeto(Usuario objeto) {
		UsuarioDTO resultado = new UsuarioDTO();
		resultado.setId(objeto.getUsuaId());
		resultado.setNombre(objeto.getUsuaNombre());
		resultado.setPassword(objeto.getUsuaPassword());

		return resultado;
	}

	@Override
	public Usuario getObjetoDEDto(UsuarioDTO objeto) {
		Usuario resultado = new Usuario();
		resultado.setUsuaId(objeto.getId());
		resultado.setUsuaNombre(objeto.getNombre());
		resultado.setUsuaPassword(objeto.getPassword());

		return resultado;
	}
}
