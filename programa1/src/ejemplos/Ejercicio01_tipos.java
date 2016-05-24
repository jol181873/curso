package ejemplos;
import java.text.DecimalFormat;

public class Ejercicio01_tipos {

	public static void main(String[] args) {
		int n=5;
		double a=4.56532;
		char c='a';
		int g=11;
		double gDouble=11.0;
		
		DecimalFormat df= new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		System.out.println("Variable a :double   : "+a);
		System.out.println("Variable a formateada: "+df.format(a));
		System.out.println("Variable n :int      : "+n);
		System.out.println("n==a?                : "+(n==a));
		System.out.println("Variable c :char     : "+c);
		System.out.println(Character.getNumericValue(c));
		
		System.out.println("Variable g :int      : "+g);
		System.out.println("Variable gDouble     : "+gDouble);
		System.out.println("g==gDouble?          : "+(g==gDouble));
		
		//System.out.println(n+" + "+a+);
	}

}
