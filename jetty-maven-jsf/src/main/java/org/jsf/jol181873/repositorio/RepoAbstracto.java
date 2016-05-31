package org.jsf.jol181873.repositorio;

import java.util.List;

public interface RepoAbstracto<T> {
	public T obtenerObjeto(Long id);

	public List<T> obtenerTodoLosObjetos();

	public void insertarObjeto(T objeto);

	public void modificarObjeto(T objeto);

	public void borrarObjeto(T objeto);

}
