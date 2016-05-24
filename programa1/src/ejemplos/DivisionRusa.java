package ejemplos;

public class DivisionRusa {
	//public static resultado;
	
	
	public static int division(int dividendo,int divisor) {
		if (divisor>dividendo) {
			return 0;
		} else {
			return 1+division(dividendo-divisor,divisor);
		}
	}

	public static void main(String[] args) {
		System.out.println(division(10,1));
	}

}
