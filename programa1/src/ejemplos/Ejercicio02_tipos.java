package ejemplos;

/* declarar dos variables x e y de tipo entero
 * dos variables n y m de tipo double 
 * x=1, y=2
 * m=3.2, n=4.7
 * suma de x+y
 * diferencia de x-y
 * el producto de x*y
 * la suma de n+m
 * la diferencia n-m
 * el cociente de n/m
 * el resto de n%m
 * la suma de x+n
 * el cociente de y%m
 * cociente x/y
 * el doble de cada variable
 * la suma de todas las variables
 * el producto de todas las variables 
 */

public class Ejercicio02_tipos {

	public static void main(String[] args) {
		int x=1;
		int y=2;
		double m=3.2;
		double n=4.7;
		
		System.out.println("x :int="+x);
		System.out.println("y :int="+y);
		System.out.println("m :double="+m);
		System.out.println("n :double="+n);
		System.out.println("x+y="+(x+y));
		System.out.println("x-y="+(x-y));
		System.out.println("x*y="+(x*y));
		System.out.println("n+m="+(n+m));
		System.out.println("n-m="+(n-m));
		System.out.println("n/m="+(n/m));
		System.out.println("n%m="+(n%m));
		System.out.println("x+n="+(x+n));
		System.out.println("y%m="+(y%m));
		System.out.println("2*x="+(2*x));
		System.out.println("2*y="+(2*y));
		System.out.println("2*m="+(2*m));
		System.out.println("2*n="+(2*n));
		System.out.println("x/y="+(x/y));
		System.out.println("x+y+m+n="+(x+y+m+n));
		System.out.println("x*y*m*n="+(x*y*m*n));
		
		System.out.println("(int)"+m+"="+((int) m));
		System.out.println("(double)"+y+"="+((double) y));
		System.out.println("(double)x+(int)m="+((double)x+(int)m));
		System.out.println("(double)(x+m)="+((double)(x+m)));
		System.out.println("x+m="+(x+m));

	}

}
