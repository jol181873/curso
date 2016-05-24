package biblioteca;

import java.util.ArrayList;

public class Usuario extends UsuarioLogin {
	private static long autoinc=0;	
	private long id;
	
	private Biblioteca biblioteca;
	private ArrayList<Prestamo> listaPrestamos; //0..*
	
	public Usuario() {
		super();
		autoinc++;
		this.listaPrestamos=new ArrayList<Prestamo>();
		this.biblioteca=Biblioteca.getBiblioteca();
		this.setId(autoinc);
	}
	
	public Usuario(String nombre, String primApell, String segunApell,String nombrLogin,String password) {
		super(nombre, primApell, segunApell,nombrLogin,password);
		autoinc++;
		this.listaPrestamos=new ArrayList<Prestamo>();
		this.biblioteca=Biblioteca.getBiblioteca();
		this.setId(autoinc);
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}
	
	public static void setAutoinc(long autoinc) {
		Usuario.autoinc = autoinc;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public ArrayList<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(ArrayList<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void pintar() {
		System.out.println("Nombre     : "+getNombreCompleto());
		for (Prestamo prestamo:listaPrestamos) {
			System.out.println(".............................");
			System.out.println("Desde : "+prestamo.getDesde());
			System.out.println("Hasta : "+prestamo.getHasta());
			for(int i=0;i<prestamo.getListaLibrosEnPrestamo().size();i++) {
				System.out.println("Libro prestado "+i);
				prestamo.getListaLibrosEnPrestamo().get(i).pintar();
			}
		}
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
}
