package mamiferos;

import java.util.Random;

public class Morsa extends Acuatico{

	public Morsa(String sexo) {
		super("Morsa",  "Morsus Acuaticus", sexo,Tipo_alimentacion.CARNIVORO, 30);		
	}

	public Morsa() {
		super("Morsa", "Morsus Acuaticus",Tipo_alimentacion.CARNIVORO, 30);		
	}
	
	@Override
	public Morsa[] reproducir(Mamifero madre) {
		if (this.getSexo()!=madre.getSexo()&&(this.getClass()==(madre.getClass()))){
			System.out.println("Me estoy reproduciendo "+ this.getClass().toString()+" "+this.getSexo());
			System.out.println("Me estoy reproduciendo "+ madre.getClass().toString()+" "+madre.getSexo());
			   
			int nHijos=(new Random()).nextInt(2)+1;
			System.out.println("He tenido "+nHijos+" descendientes");
			Morsa[] camada=new Morsa[nHijos];
			
			for (int i=0;i<nHijos;i++) {
				camada[i]=new Morsa();			
			}
			
			System.out.println("He tenido "+camada.length+" hijos " );
			return camada;
		} else if (this.getClass()!=madre.getClass())  {
			System.out.println("No se pueden cruzar mamíferos de distinta especie");			
		} else {
			System.out.println("Con el mismo sexo mal vamos para reproducirnos");
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Morsa morsa = new Morsa("hembra");
		morsa.bucear();
		System.out.println(morsa.getNombreCientifico());
		System.out.println(morsa.getSexo());
		
		Morsa padre=new Morsa("macho");
		Morsa madre=new Morsa("hembra");
		
		Morsa[] camada=padre.reproducir(madre);
		for (int i=0;i<camada.length;i++) {
			System.out.println(camada[i].getNombreComun()+"  "+camada[i].getSexo());
		}
	}
}
