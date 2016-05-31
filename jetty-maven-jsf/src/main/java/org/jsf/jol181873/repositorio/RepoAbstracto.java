package org.jsf.jol181873.repositorio;

import java.io.Serializable;
import java.util.List;

public interface RepoAbstracto<T> extends Serializable {
	public T obtenerObjeto(T objeto);

	public List<T> obtenerTodoLosObjetos();

	public void insertarObjeto(T objeto);

	public void modificarObjeto(T objeto);

	public void borrarObjeto(T objeto);

}
