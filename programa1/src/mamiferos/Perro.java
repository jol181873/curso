package mamiferos;
import java.util.*;

public class Perro extends Terrestres {
	public Perro(String sexo) {
		super("perro", "canis lupus familiaris", sexo, Tipo_alimentacion.CARNIVORO, 4);
	}	
	public Perro() {
		super("perro", "canis lupus familiaris", Tipo_alimentacion.CARNIVORO, 4);
	}

	public Perro[] reproducir(Mamifero madre) {
		if (this.getSexo()!=madre.getSexo()&&(this.getClass()==(madre.getClass()))){					
			//System.out.println("Me estoy reproduciendo "+ this.getClass().toString()+" "+this.getSexo());
			//System.out.println("Me estoy reproduciendo "+ madre.getClass().toString()+" "+madre.getSexo());
			
			System.out.println("Me estoy reproduciendo");
			   
			int nHijos=(new Random()).nextInt(2)+1;
			System.out.println("He tenido "+nHijos+" descendientes");
			Perro[] camada=new Perro[nHijos];
			
			for (int i=0;i<nHijos;i++) {
				camada[i]=new Perro();	
				camada[i].nacer();
			}
			
			return camada;
		} else if (this.getClass()!=madre.getClass())  {
			System.out.println("No se pueden cruzar mamíferos de distinta especie");			
		} else {
			System.out.println("Con el mismo sexo mal vamos para reproducirnos");
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Perro padre=new Perro("macho");
		Perro madre=new Perro("hembra");
		
		Perro[] camada=padre.reproducir(madre);
		for (int i=0;i<camada.length;i++) {
			System.out.println(camada[i].getNombreComun()+"  "+camada[i].getSexo());
		}
	}
}
