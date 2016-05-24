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
		
		System.out.println("Área del círculo   : "+circulo.area());
		System.out.println("Área del cuadrado  : "+cuadrado.area());
		System.out.println("Área del rectángulo: "+rectangulo.area());
		
		System.out.println("Perímetro del circulo   : "+circulo.perimetro());
		System.out.println("Perímetro del cuadrado  : "+cuadrado.perimetro());
		System.out.println("Perímetro del rectángulo: "+rectangulo.perimetro());
		
	}
}
