package ejemplos;

public class Fibonacci {
	public int fibo(final int n) {
		if (n==0 || n==1) {
			return 1;
		} else {
			return fibo(n-1)+fibo(n-2);
		}
	}
	public static void main(String[] args) {
		Fibonacci fibo=new Fibonacci();
		System.out.println(fibo.fibo(5));
	}
}
