package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable {
	private static long autoinc=0;
	
	public static void setAutoinc(long autoinc) {
		Libro.autoinc = autoinc;
	}

	private Biblioteca biblioteca;
	private String titulo;
	private long id;
	private String ISBN;
	private ArrayList<Autor> listaAutores; 
	private Prestamo prestamo; //0..1, es decir si es null no esta prestado
	
	public Libro(String titulo,String ISBN) {
		autoinc++;
		this.id=autoinc;
		this.titulo=titulo;
		this.ISBN=ISBN;
		listaAutores=new ArrayList<Autor>();
		biblioteca=Biblioteca.getBiblioteca();
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public ArrayList<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(ArrayList<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}		
	
	public void pintar() {
		System.out.println("Título     : "+titulo);
		System.out.println("Autor(es)  : ");
		for (Autor autor:listaAutores) {
			System.out.println("     .............................");
			System.out.println("     Nombre           : "+autor.getNombre());
			System.out.println("     Primer apellido  : "+autor.getPrimApell());
			System.out.println("     Segundo apellido : "+autor.getSegunApell());
		}
		System.out.println("ISBN       : "+ISBN);
	}
}
