package ejemplos;

public class Cuadrado extends Figura_geometrica{	
	private double lado;
	
	public Cuadrado(double x, double y,double lado) {
		super(x, y);
		this.lado=lado;
	}

	public static void main(String[] args) {

		
	}

	@Override
	public double area() {
		return lado*lado;
	}

	@Override
	public double perimetro() {
		return 4*lado;
	}
	
	@Override
	public String toString() {
		return "Cuadrado("+lado+")";		
	}
}
