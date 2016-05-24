package biblioteca;

public class Trabajador extends UsuarioLogin {
	private static long autoinc=0;		
	private long id;
	
	private Biblioteca biblioteca;
	private double sueldoMensualNeto;	

	public Trabajador() {
		super();
		autoinc++;
		this.biblioteca=Biblioteca.getBiblioteca();
		this.id=autoinc;
	}
	
	public Trabajador(String nombre, String primApell, String segunApell,double sueldoMensualNeto,String nombrLogin,String password) {
		super(nombre, primApell, segunApell,nombrLogin,password);
		autoinc++;
		this.sueldoMensualNeto=sueldoMensualNeto;
		this.biblioteca=Biblioteca.getBiblioteca();
		this.id=autoinc;
	}
	
	public static void setAutoinc(long autoinc) {
		Trabajador.autoinc = autoinc;
	}

	public double getSueldoMensualNeto() {
		return sueldoMensualNeto;
	}

	public void setSueldoMensualNeto(double sueldoMensualNeto) {
		this.sueldoMensualNeto = sueldoMensualNeto;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}		
	
	public void pintar() {
		System.out.println("Nombre        : "+getNombreCompleto());
		System.out.println("Sueldo        : "+sueldoMensualNeto);
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
