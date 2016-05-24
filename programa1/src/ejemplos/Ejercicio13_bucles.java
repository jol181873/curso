package ejemplos;

public class Ejercicio13_bucles {

	public static void main(String[] args) {
		System.out.println("Bucle while del 1 al 100");
		
		int i=1;
		while(i<=100) {		
			
			switch (i) {
				case 20:
				case 40:
				case 60:
				case 80:
				case 100:
					System.out.println(i);
					break;
				default:
					System.out.print(i);
					break;					
			}
			i++;
		}
		
		i=1;
		do {				
			switch (i) {
				case 20:
				case 40:
				case 60:
				case 80:
					System.out.println(i);
					break;
				default:
					System.out.print(i);
					break;					
			}
			i++;
		} while(i<=100);
	}

}
