package ejemplos;
import java.util.*;

public class Ejercicio03_teclado {

	public static void main(String[] args) {
		int n1;
		int n2;
		Scanner sc=new Scanner(System.in);
		//leer el primer numero insertado por teclado
		System.out.println("Introduzca un número entero:");
		n1=sc.nextInt();
		
		//leer el segundo numero
		System.out.println("Introduzca un número entero:");
		n2=sc.nextInt();
		System.out.println("Ha introducido los números: "+n1+" y "+n2);
		
		System.out.println(n1+" + "+n2+"="+(n1+n2));
		System.out.println(n1+" * "+n2+"="+(n1*n2));
		System.out.println(n1+" / "+n2+"="+(n1/n2));
		System.out.println(n1+" % "+n2+"="+(n1%n2));
		
		System.out.println("Vaya metiendo sumandos, -1 para terminar:");
		
		int suma=0;
		int entrada=0;
		while(entrada!=-1) {
			entrada=sc.nextInt();
			if (entrada!=-1) suma=suma+entrada;
		}
		System.out.println("Resultado: "+suma);
	}

}
