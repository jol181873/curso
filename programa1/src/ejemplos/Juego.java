package ejemplos;

import java.util.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import mamiferos.Perro;

public class Juego {
	
	// porcentaje del total de habitantes del zoo que se cruzan con otro en cada generacion
	public static int porcentajeDelZooQueSeReproduce=25;
	public static int numeroGeneraciones=6;
	
	public static void main(String[] args) {
		ArrayList<Perro> zoo=new ArrayList<Perro>();
		
		
		Perro adanPerro=new Perro("macho");
		Perro evaPerro=new Perro("hembra");
		zoo.add(adanPerro);
		zoo.add(evaPerro);
		
		Perro[] cainesAbeles=adanPerro.reproducir(evaPerro);
		
		List<Perro> lista=Arrays.asList(cainesAbeles);
		zoo.addAll(lista);
		
		List<Perro> listaHijos=new ArrayList<Perro>();
		
		for (int generaciones=0;generaciones<numeroGeneraciones;generaciones++) {
			System.out.println("=========================== generacion: "+generaciones);
			for (int pareja=0;pareja<zoo.size()*porcentajeDelZooQueSeReproduce/100;pareja++) {
				int indicePadre=-1;
				do {
					indicePadre=(new Random()).nextInt(zoo.size());
				} while (!zoo.get(indicePadre).getSexo().equals("macho"));
				
				int indiceMadre=-1;
				do {
					indiceMadre=(new Random()).nextInt(zoo.size());
				} while (!zoo.get(indiceMadre).getSexo().equals("hembra"));
				
				Perro padre=zoo.get(indicePadre);
				Perro madre=zoo.get(indiceMadre);
				Perro[] descendientes=padre.reproducir(madre);
							
				listaHijos.addAll(Arrays.asList(descendientes));							
			}
			
			System.out.println("=========================== la generacion ha tenido: "+listaHijos.size()+" descendientes");
			// despues de cada generacion se aniaden los hijos al zoo
			zoo.addAll(listaHijos);
			listaHijos.clear();
		}
		
		System.out.println(zoo.size());		
	}

}
