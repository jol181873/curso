package org.jsf.jol181873.repositorio.jpa;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.jsf.jol181873.modelo.dto.UsuarioDTO;
import org.jsf.jol181873.modelo.jpa.Usuario;
import org.jsf.jol181873.repositorio.AdaptadorUsuario;
import org.jsf.jol181873.repositorio.RepoUsuarioI;

@Named("repoUsuarioJPA")
@ApplicationScoped
public class RepoUsuarioJPA implements RepoUsuarioI {
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
		Usuario usuario = this.em.find(Usuario.class, objeto.getId());

		return adaptador.getDtoDeObjeto(usuario);
	}

	@Override
	public List<UsuarioDTO> obtenerTodoLosObjetos() {
		List<Usuario> lista = this.em
				.createQuery("SELECT pelu FROM Peluqueria pelu ORDER BY pelu.peluNombre", Usuario.class)
				.getResultList();

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
