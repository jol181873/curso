package ejemplos;

import java.util.Scanner;

public class Ejercicio04_teclado {

	public static void main(String[] args) {		
		Scanner sc=new Scanner(System.in);
		
		String cadena;
		String otraCadena;
		//System.out.println("Introduce tu nombre: ");
		//cadena=sc.nextLine();
		//System.out.println("Hola "+cadena);
		
		cadena="Esto es una ola";
		otraCadena= "HOLA";
		int indice=0;
		
		if (cadena==otraCadena) {
			System.out.println(cadena+" es igual a "+otraCadena);
		} else {
			System.out.println(cadena+" no es igual a "+otraCadena);
		}
		
		if (otraCadena.equals(cadena.toUpperCase())) {
			System.out.println(cadena.toUpperCase()+" es igual a "+otraCadena);
		} else {
			System.out.println(cadena.toUpperCase()+" no es igual a "+otraCadena);
		}
		
		otraCadena=cadena.substring(5,cadena.length());
		System.out.println("la subcadena desde 5 hasta el final es: "+otraCadena);
		
		indice=cadena.lastIndexOf('a');
		System.out.println("último lugar de la letra a:"+indice);
		
	}

}
