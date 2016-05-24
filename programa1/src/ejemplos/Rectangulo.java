package ejemplos;

public class Rectangulo extends Figura_geometrica{
	private double base;
	private double altura;

	public Rectangulo(double x, double y,double base,double altura) {
		super(x, y);
		this.base=base;
		this.altura=altura;
	}

	@Override
	public double area() {
		return base*altura;
	}

	@Override
	public double perimetro() {
		return 2*base+2*altura;
	}
	
	@Override
	public String toString() {
		return "Rectangulo("+base+","+altura+")";		
	}
}
