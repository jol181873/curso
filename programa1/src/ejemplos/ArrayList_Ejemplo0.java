package ejemplos;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayList_Ejemplo0 {
	private ArrayList<String> vector=new ArrayList<String>();
	
	public static void main(String[] args) {
		ArrayList_Ejemplo0 ej=new ArrayList_Ejemplo0();
		
		ej.vector.add("Hola mundo 1");
		ej.vector.add("Hola mundo 2");
		ej.vector.add("Hola mundo 3");
		
		for(int i=0;i<ej.vector.size();i++) {
			ej.vector.remove(i);
		}
		
		/*Iterator<String> it=ej.vector.iterator();
		while(it.hasNext()) {
			ej.vector.remove(0);
		}
		
		for (String cadena:ej.vector) {
			ej.vector.remove(cadena);
		}
		
		*/
		
		
		
	}
	

}
