package mamiferos;

import java.util.*;

public class Voladores extends Mamifero{
    String tipoVuelo;
    double longitudAlas;
    private int altura;
    int alturaMaxima;
    
	public Voladores(String nombreComun,String nombreCientifico, String sexo,String tipoAlimentacion,int alturaMaxima) {		
		super(nombreComun, nombreCientifico, sexo, tipoAlimentacion);
		altura=0;
		this.alturaMaxima=alturaMaxima;
	}
	
	public Voladores(String nombreComun, String nombreCientifico, String tipoAlimentacion, int alturaMaxima){		
		super(nombreComun,nombreCientifico,tipoAlimentacion);
		this.alturaMaxima=alturaMaxima;
	}
	
	public void volar()	{
		Scanner linea = new Scanner(System.in);
		System.out.println("¿A que altura voy a a volar?");
		altura=linea.nextInt();
		while(altura>=alturaMaxima){
			System.out.println("No puedo volar mas alto");
			altura=linea.nextInt();
		}
		if(altura>0)	
		  System.out.println("Volando voy volando vengo a " + altura + " metros ");
		else
		  System.out.println("Estoy en el suelo");		   
	}
	
	public void planear(){
		Scanner linea = new Scanner(System.in);
		System.out.println("¿A que altura voy a a planear?");
		altura=linea.nextInt();
		while(altura>=alturaMaxima){
			System.out.println("No puedo planear mas alto");
			altura=linea.nextInt();
		}
		if(altura>0)
		  System.out.println("Planeando voy planeando vengo");
		else
		  System.out.println("No puedo planear estoy en el suelo");  
	}
	
	public void nacer() {
		if(getEdad()==0)
		  System.out.println("He nacido");
		else 
		  System.out.println("Ya habia nacido");
			  
	}
	
	public void morir() {		
			System.out.println("Hasta aqui");
	}	
		
	public  Voladores[] reproducir(Mamifero madre){
		int nHijos=(new Random()).nextInt(3)+1;
		Voladores[] miVoladores = new Voladores[nHijos];
		for (int i = 0; i < miVoladores.length; i++) {
			Voladores miVolador = new Voladores(getNombreComun(), getNombreCientifico(), getSexo(), getTipoAlimentacion(),getAlturaMaxima()); 
			miVoladores[i]=miVolador;
			
		}
		
		return miVoladores;
	}
	
	public void dormir() {	
		System.out.println("Estoy en la cama");
	}
	
	public void comer() {
		System.out.println("Estoy comiendo");	
	}
	
	public void crecer() {	
		System.out.println("Estoy creciendo");	
	}
	
	public int getAlturaMaxima(){
		return this.alturaMaxima;
	}
}


