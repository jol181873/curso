package mamiferos;

import java.util.Random;

public class Gato extends Terrestres {

	public Gato(String nombreComun, String nombreCientifico, String sexo, String tipoAlimentacion) {
		super("Gato", "Felis Silvestris Catus", sexo, Tipo_alimentacion.CARNIVORO, 0);
	}

	public Gato() {
		super("Gato", "Felis Silvestris Catus", Tipo_alimentacion.CARNIVORO, 0);
	}
	
	public Gato[] reproducir(Terrestres madre) {
		System.out.println("Me estoy reproduciendo");
		int nHijos=(new Random()).nextInt(10)+1;
		System.out.println("He tenido "+nHijos+" descendientes");
		Gato[] camada=new Gato[nHijos];
		
		for (int i=0;i<nHijos;i++) {
			camada[i]=new Gato();			
		}		
		return camada;
	}
	
	public static void main(String[] args) {
		
		Gato padre = new Gato();
		Perro madre = new Perro();
		if(padre.getNombreCientifico()!=madre.getNombreCientifico()){
			System.out.println("No se pueden reproducir entre ellos TAMOS LOCOS O QUE!!!");
		}else{
			Gato[] camada=padre.reproducir(madre);
			for (int i=0;i<camada.length;i++) {
				System.out.println(camada[i].getNombreComun()+"  "+camada[i].getSexo());		
			}
		}
		
	}	
}
