package ejemplos;

public class Figuras {	
	public static void main(String[] args) {
		Circulo circulo;
		Cuadrado cuadrado;
		Rectangulo rectangulo;
		
		circulo=new Circulo(0, 0, 10);
		cuadrado=new Cuadrado(0,0,10);
		rectangulo=new Rectangulo(0,0,20,10);
		
		System.out.println(circulo);
		System.out.println(cuadrado);
		System.out.println(rectangulo);
		System.out.println();
		
		System.out.println("�rea del c�rculo   : "+circulo.area());
		System.out.println("�rea del cuadrado  : "+cuadrado.area());
		System.out.println("�rea del rect�ngulo: "+rectangulo.area());
		
		System.out.println("Per�metro del circulo   : "+circulo.perimetro());
		System.out.println("Per�metro del cuadrado  : "+cuadrado.perimetro());
		System.out.println("Per�metro del rect�ngulo: "+rectangulo.perimetro());
		
	}
}
