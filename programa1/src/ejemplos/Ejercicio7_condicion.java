package ejemplos;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio7_condicion {

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		
		char caracter;
		
		System.out.println("Escribe un caracter:");
		caracter=(char) System.in.read();
		
		if (Character.isUpperCase(caracter)) {
			System.out.println(caracter+" esta en mayúsculas");
		} else {
			System.out.println(caracter+" esta en minúsculas");
		}

	}

}
