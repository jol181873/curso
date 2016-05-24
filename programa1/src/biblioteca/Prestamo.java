package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Prestamo implements Serializable{
	private Biblioteca biblioteca;
	private Usuario usuario; //no nulo
	private ArrayList<Libro> listaLibrosEnPrestamo; // maximo 3
	private Date desde;
	private Date hasta;
	
	public Prestamo() {
		super();
		this.listaLibrosEnPrestamo=new ArrayList<Libro>();
		this.setBiblioteca(Biblioteca.getBiblioteca());
	}
	
	public Prestamo(Usuario usuario,Date desde,Date hasta) {
		this.usuario=usuario;
		this.desde=desde;
		this.hasta=hasta;
		this.listaLibrosEnPrestamo=new ArrayList<Libro>();
		this.setBiblioteca(Biblioteca.getBiblioteca());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Libro> getListaLibrosEnPrestamo() {
		return listaLibrosEnPrestamo;
	}

	public void setListaLibrosEnPrestamo(ArrayList<Libro> listaLibrosEnPrestamo) {
		this.listaLibrosEnPrestamo = listaLibrosEnPrestamo;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}			
}
