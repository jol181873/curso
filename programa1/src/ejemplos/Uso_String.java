package ejemplos;
import java.util.Scanner;

public class Uso_String {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = "hola";
		String b;
		String d = "hola";
		// el mismo objeto
		if(a==d){
			System.out.println("Los objetos " +a+" y "+d+" son iguales");
			
		}
		else
		{
			System.out.println("Los objetos "+a+" y "+d+" son diferentes");
			if (a.equals(d)){
				System.out.println(a+" es igual que "+d);
				
			}
			else{
				System.out.println(a+" no es igual que "+d);
			
			}
		}	
		
		//por teclado
		
		System.out.println("Escribe hola:");
		b= sc.nextLine();
		
		
		
		
		
		if(a==b){
			System.out.println("Los objetos " +a+" y "+b+" son iguales");
			
		}
		else
		{
			System.out.println("Los objetos "+a+" y "+b+" son diferentes");
			if (a.equals(b)){
				System.out.println(a+" es igual que "+b);
				
			}
			else{
				System.out.println(a+" no es igual que "+b);
			
			}
		}
		
		//Creando objeto nuevo
		String c = new String(a);
		if(a==c){
			System.out.println("Los objetos " +a+" y "+c+" son iguales");
			
		}
		else
		{
			System.out.println("Los objetos "+a+" y "+c+" son diferentes");
			if (a.equals(c)){
				System.out.println(a+" es igual que "+c);
				
			}
			else{
				System.out.println(a+" no es igual que "+c);
			
			}
		}
		
	}

}
