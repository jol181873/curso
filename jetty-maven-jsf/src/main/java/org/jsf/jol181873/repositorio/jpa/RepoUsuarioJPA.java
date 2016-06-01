package org.jsf.jol181873.repositorio.jpa;

import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jsf.jol181873.modelo.dto.UsuarioDTO;
import org.jsf.jol181873.modelo.jpa.Usuario;
import org.jsf.jol181873.repositorio.AdaptadorUsuario;
import org.jsf.jol181873.repositorio.RepoUsuarioI;

@Model
@Alternative
public class RepoUsuarioJPA implements RepoUsuarioI {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7086517960658436753L;

	private static RepoUsuarioJPA instancia;

	@PersistenceContext
	private EntityManager em = Persistence.createEntityManagerFactory("jol.pelus.mascotas").createEntityManager();

	AdaptadorUsuario adaptador = new AdaptadorUsuario();

	public RepoUsuarioJPA() {
	}

	public static RepoUsuarioJPA getInstance() {
		if (instancia == null) {
			instancia = new RepoUsuarioJPA();
		}

		return instancia;
	}

	// ================================================================================================
	// ================================================================================================
	// ================================================================================================

	@Override
	public UsuarioDTO obtenerObjeto(UsuarioDTO objeto) {
		TypedQuery<Usuario> q = this.em.createQuery(
				"SELECT usua FROM Usuario usua where usua.usuaNombre=?1 ORDER BY usua.usuaNombre", Usuario.class);
		q.setParameter(1, objeto.getNombre());

		return adaptador.getDtoDeObjeto(q.getSingleResult());
	}

	@Override
	public List<UsuarioDTO> obtenerTodoLosObjetos() {
		List<Usuario> lista = this.em
				.createQuery("SELECT usua FROM Usuario usua ORDER BY usua.usuaNombre", Usuario.class).getResultList();

		return adaptador.getListaDtoDeObjeto(lista);
	}

	@Override
	public void insertarObjeto(UsuarioDTO objeto) {
		this.em.persist(adaptador.getObjetoDEDto(objeto));
	}

	@Override
	public void modificarObjeto(UsuarioDTO objeto) {
		this.em.merge(adaptador.getObjetoDEDto(objeto));
	}

	@Override
	public void borrarObjeto(UsuarioDTO objeto) {
		em.getTransaction().begin();
		this.em.remove(adaptador.getObjetoDEDto(objeto));
		em.getTransaction().commit();
	}
}
