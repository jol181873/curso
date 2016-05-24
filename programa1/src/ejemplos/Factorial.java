package ejemplos;

public class Factorial {
	
	private int factorial(int n,int resultado) {
		if (n<=1) {
			return resultado;
		} else {
			return factorial(n-1,resultado*n);
		}
	}
	
	public int factorial(int n) {
		return factorial(n-1,n);
	}
	
	public int factorial0(int n) {
		if (n<=1) {
			return 1;
		} else {
			return n*factorial0(n-1);
		}
	}

	public static void main(String[] args) {
		Factorial fac=new Factorial();
		System.out.println(fac.factorial(5));
		System.out.println(fac.factorial0(5));
	}
}
