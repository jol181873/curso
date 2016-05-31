package org.jsf.jol181873.repositorio.jpa;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.jsf.jol181873.modelo.dto.PeluqueriaDTO;
import org.jsf.jol181873.modelo.jpa.Peluqueria;
import org.jsf.jol181873.repositorio.AdaptadorPelu;
import org.jsf.jol181873.repositorio.RepoPeluqueriaI;

@Named("repoPeluqueriaJPA")
@ApplicationScoped
public class RepoPeluqueriaJPA implements RepoPeluqueriaI {
	private static RepoPeluqueriaJPA instancia;

	@PersistenceContext
	private EntityManager em = Persistence.createEntityManagerFactory("jol.pelus.mascotas").createEntityManager();

	AdaptadorPelu adaptador = new AdaptadorPelu();

	public RepoPeluqueriaJPA() {
	}

	public static RepoPeluqueriaJPA getInstance() {
		if (instancia == null) {
			instancia = new RepoPeluqueriaJPA();
		}

		return instancia;
	}

	// ================================================================================================
	// ================================================================================================
	// ================================================================================================

	@Override
	public PeluqueriaDTO obtenerObjeto(PeluqueriaDTO objeto) {
		Peluqueria pelu = this.em.find(Peluqueria.class, objeto.getPeluId());

		return adaptador.getDtoDeObjeto(pelu);
	}

	@Override
	public List<PeluqueriaDTO> obtenerTodoLosObjetos() {
		List<Peluqueria> lista = this.em
				.createQuery("SELECT pelu FROM Peluqueria pelu ORDER BY pelu.peluNombre", Peluqueria.class)
				.getResultList();

		return adaptador.getListaDtoDeObjeto(lista);
	}

	@Override
	public void insertarObjeto(PeluqueriaDTO objeto) {
		this.em.persist(adaptador.getObjetoDEDto(objeto));
	}

	@Override
	public void modificarObjeto(PeluqueriaDTO objeto) {
		this.em.merge(adaptador.getObjetoDEDto(objeto));
	}

	@Override
	public void borrarObjeto(PeluqueriaDTO objeto) {
		em.getTransaction().begin();
		this.em.remove(adaptador.getObjetoDEDto(objeto));
		em.getTransaction().commit();
	}
}
