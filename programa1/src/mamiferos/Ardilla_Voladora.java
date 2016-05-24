package mamiferos;

import java.util.Random;

public class Ardilla_Voladora extends Voladores{

	public Ardilla_Voladora(String sexo, int alturaMaxima) {
		super("Ardilla", "Flying Ardillus", sexo, Tipo_alimentacion.HERVIVORO, 20);

	}
	
	public Ardilla_Voladora() {
		super("Ardilla", "Flying Ardillus", Tipo_alimentacion.HERVIVORO, 20);

	}
	
	public Ardilla_Voladora[] reproducir(Voladores madre) {
		System.out.println("Me estoy reproduciendo");
		int nHijos=(new Random()).nextInt(10)+1;
		System.out.println("He tenido "+nHijos+" descendientes");
		Ardilla_Voladora[] camada=new Ardilla_Voladora[nHijos];
		
		for (int i=0;i<nHijos;i++) {
			camada[i]=new Ardilla_Voladora();			
		}
		
		return camada;
	}
	
	public static void main(String[] args) {
		Ardilla_Voladora ardilluca = new Ardilla_Voladora("varona", 15);
		ardilluca.dormir();
		//ardilluca.volar();
		ardilluca.comer();	
		System.out.println(ardilluca.getNombreCientifico());
		
		Ardilla_Voladora padre = new Ardilla_Voladora("macho", 10);
		Ardilla_Voladora madre = new Ardilla_Voladora("hembra", 10);
		
		if(padre.getNombreCientifico()!=madre.getNombreCientifico()){
			System.out.println("No se pueden reproducir entre ellos");
		}else{
			Ardilla_Voladora[] camada=padre.reproducir(madre);
			for (int i=0;i<camada.length;i++) {
				System.out.println(camada[i].getNombreComun()+"  "+camada[i].getSexo());		
			}
		}
		
	}
}