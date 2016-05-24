package mamiferos ;

import java.util.Random;

public class Terrestres extends Mamifero {

	private int nPatas;
	
	public Terrestres(String nombreComun, String nombreCientifico, String sexo, String tipoAlimentacion, int nPatas) {
		super(nombreComun, nombreCientifico, sexo, tipoAlimentacion);
		this.setnPatas(nPatas);
	}
	
	public Terrestres(String nombreComun, String nombreCientifico, String tipoAlimentacion, int nPatas) {
		super(nombreComun, nombreCientifico, tipoAlimentacion);
		this.setnPatas(nPatas);
	}

	@Override
	public void nacer() {
		System.out.println("Acabo de nacer como "+getNombreComun()+" "+getSexo());
	}

	@Override
	public void crecer() {
		System.out.println("Estoy creciendo");
	}

	@Override
	/**
	 * 
	 * @param se le pasa el mamifero madre
	 * @return devuelve el hijo resultado de reproducirse
	 */
	public Terrestres[] reproducir(Mamifero madre) {
		System.out.println("Me estoy reproduciendo");
		int nHijos=(new Random()).nextInt(10)+1;
		System.out.println("He tenido "+nHijos+" descendientes");
		Terrestres[] camada=new Terrestres[nHijos];
		
		for (int i=0;i<nHijos;i++) {
			camada[i]=new Terrestres(getNombreComun(), getNombreCientifico(), getTipoAlimentacion(), getnPatas());			
		}
		
		return camada;
	}

	@Override
	public void morir() {
		System.out.println("Me muero");
	}

	@Override
	public void comer() {
		if(getTipoAlimentacion().toLowerCase().equals(Tipo_alimentacion.CARNIVORO)){
			System.out.println("Estoy comiendo carne");
		} else if (getTipoAlimentacion().toLowerCase().equals(Tipo_alimentacion.HERVIVORO)){
			System.out.println("Estoy comiendo pepinillos");
		} else {
			System.out.println("Estoy comiendo de todo");
		}
	}

	@Override
	public void dormir() {	
		System.out.println("Estoy durmiendo");
	}

	public int getnPatas() {
		return nPatas;
	}

	public void setnPatas(int nPatas) {
		this.nPatas = nPatas;
	}
}
