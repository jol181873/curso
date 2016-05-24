package mamiferos;

import java.util.Random;

//import ejemplos.Voladores;

public class Murcielago extends Voladores {

	public Murcielago() {
		super("Urcielago","Murcielagus Vampirus",Tipo_alimentacion.CARNIVORO,200);
	}
	public Murcielago(String sexo) {
		super("Urcielago","Murcielagus Vampirus",sexo,Tipo_alimentacion.CARNIVORO,200);
	}
	/*public  Murcielago reproducir(Mamifero madre){
		Voladores miVolador = new Volador(getNombreComun(), getNombreCientifico(), getSexo(), getTipoAlimentacion(),getAlturaMaxima()); 
		return miVolador;
	}*/

	

	
	public  Murcielago[] reproducir(Mamifero madre){
		if (this.getSexo()!=madre.getSexo()&&(this.getClass()==(madre.getClass()))){
		   System.out.println("Me estoy reproduciendo "+ this.getClass().toString()+" "+this.getSexo());
		   System.out.println("Me estoy reproduciendo "+ madre.getClass().toString()+" "+madre.getSexo());
		   
		   int nHijos=(new Random()).nextInt(2)+1;
		   Murcielago[] misMurcielagos = new Murcielago[nHijos];
		   for (int i = 0; i < misMurcielagos.length; i++) {
			 Murcielago miMurcielago = new Murcielago(); 
			 misMurcielagos[i]=miMurcielago;
		   
		   }
		
		   System.out.println("He tenido "+misMurcielagos.length+" hijos " );
			return misMurcielagos;
		} else if (this.getClass()!=madre.getClass())  {
			System.out.println("No se pueden cruzar mamíferos de distinta especie");			
		} else {
			System.out.println("Con el mismo sexo mal vamos para reproducirnos");
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Murcielago dracula = new Murcielago();
        Murcielago hembra  = new Murcielago();
        
        System.out.println(hembra.getSexo());
        
        dracula.reproducir(hembra);
	}


}
