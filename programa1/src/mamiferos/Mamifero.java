package mamiferos;

public abstract class Mamifero {
	private String nombreComun;
	private String nombreCientifico;
	private int edad;
	private String sexo;
	private double peso;
	private String tipoAlimentacion;
	
	public String getNombreCientifico() {
		return nombreCientifico;
	}
	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}	
	public String getTipoAlimentacion() {
		return tipoAlimentacion;
	}
	public void setTipoAlimentacion(String tipoAlimentacion) {
		this.tipoAlimentacion = tipoAlimentacion;
	}	
	public String getNombreComun() {
		return nombreComun;
	}
	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String azar_sexual() {
		int num = (int) (Math.random()*1000);
    		if (num%2 == 0) {
    			return "macho";
    		}
    		else return "hembra";
	}
	
	/**
	 * Constructor
	 * @param nombreComun nombre coloquial: perro, gato...
	 * @param nombreCientifico nombre cientifico: canis lupus familiaris 
	 * @param sexo macho,hembra
	 * @param tipoAlimentacion carnivoros,herviboros,omnivoros
	 */
	public Mamifero(String nombreComun, String nombreCientifico, String sexo,String tipoAlimentacion) {
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.edad = 0;
		this.sexo = sexo;
		this.peso = 0;
		this.tipoAlimentacion = tipoAlimentacion;
	}
	
	/**
	 * Constructor llamado desde reproduccion
	 * @param nombreComun nombre coloquial: perro, gato...
	 * @param nombreCientifico nombre cientifico: canis lupus familiaris
	 * @param tipoAlimentacion carnivoros,herviboros,omnivoros
	 */
	public Mamifero(String nombreComun, String nombreCientifico, String tipoAlimentacion) {
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.edad = 0;
		this.sexo = azar_sexual();
		this.peso = 0;
		this.tipoAlimentacion = tipoAlimentacion;
	}
	
	public abstract void nacer();
	public abstract void crecer();
	public abstract Mamifero[] reproducir(Mamifero madre);
	public abstract void morir();
	public abstract void comer();
	public abstract void dormir();	
	
	@Override
	public String toString() {
		return nombreComun+" "+sexo;
	}
}
