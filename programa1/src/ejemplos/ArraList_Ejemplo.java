package ejemplos;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ArraList_Ejemplo {
	private static ArrayList<String> vectorDinamico = new ArrayList<String>();
	public static void main(String[] args) {
		int count=0;
		vectorDinamico.add("Elemento 1");
		vectorDinamico.add("Elemento 2");
		vectorDinamico.add("Elemento 3");
		vectorDinamico.add("Elemento 2");
		vectorDinamico.add("Elemento 2");
		//Bucle recomendado para elimanar, o editar
		Iterator it = vectorDinamico.iterator();
		while(it.hasNext()){
			System.out.println(it.next());		
		}
		//tipico bucle foreach, util para recorrer el array
		//for(tipoObjeto Objeto : ListaObjetos)
		for (String elemento : vectorDinamico) {
			 
			 System.out.println(elemento);
		}
		
		for (int i=0;i<vectorDinamico.size();i++) {
			 
			 if(vectorDinamico.get(i).equals("Elemento 2")){
				 vectorDinamico.remove("Elemento 2");
				 count++;
				 System.out.println("eliminado por elemento for clasico");
			 }
		}
		
		System.out.println("Habia 3 elemenos por borrar, ha borrado: "+count );
		//recargo los elementos borrados
		vectorDinamico.add("Elemento 2");
		vectorDinamico.add("Elemento 2");		
		
		count=0;
		
		try{
			//fallo con for al borrar todos los elementos elemento 2
			for (String elemento : vectorDinamico) {
				 
				 if(elemento.equals("Elemento 2")){
					 vectorDinamico.remove("Elemento 2");
					 count++;
					 System.out.println("eliminado elemento foreach");
				 }
			}
		}catch(ConcurrentModificationException e){
			System.out.println(e);
		}finally{
			System.out.println("Habia 3 elemenos por borrar, ha borrado: "+count );
			//recargo los elementos borrados
			vectorDinamico.add("Elemento 2");
			//Vemos que con Iteratorator no sucede
			// reiniciamos el iterador
			count=0;
			it = vectorDinamico.iterator();
			while(it.hasNext()){
				if(it.next().equals("Elemento 2")){
					it.remove();
					System.out.println("eliminado elemento itereator");
					count++;
				}
			}
			//System.out.println("Habia 3 elemenos por borrar, ha borrado: "+count );
		}

		
		
	}

}
