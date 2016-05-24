package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;

public class Autor extends Persona implements Serializable {
	private static long autoinc=0;
	public static void setAutoinc(long autoinc) {
		Autor.autoinc = autoinc;
	}

	private long id;
	private Biblioteca biblioteca;
	private ArrayList<Libro> listaLibrosEscritos;

	public Autor(String nombre, String primApell, String segunApell) {
		super(nombre, primApell, segunApell);
		autoinc++;
		this.biblioteca=Biblioteca.getBiblioteca();
		this.setId(autoinc);
	}

	public ArrayList<Libro> getListaLibrosEscritos() {
		return listaLibrosEscritos;
	}

	public void setListaLibrosEscritos(ArrayList<Libro> listaLibrosEscritos) {
		this.listaLibrosEscritos = listaLibrosEscritos;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	/**
	 * Igualdad basada en nombre y dos apellidos
	 */
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Autor)) {
			return false;
		}
		Autor otro=(Autor) objeto;
		
		String nombreCompleto=(getNombre()+" "+getPrimApell()+" "+getSegunApell()).trim().toUpperCase();
		String nombreCompleto2=(otro.getNombre()+" "+otro.getPrimApell()+" "+getSegunApell()).trim().toUpperCase();
		
		if (nombreCompleto.equals(nombreCompleto2)) {
			return true;
		} else {
			return false;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
