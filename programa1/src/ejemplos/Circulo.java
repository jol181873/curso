package ejemplos;

public class Circulo extends Figura_geometrica {
	private double radio;

	public Circulo(double x, double y, double radio) {
		super(x, y);
		this.radio=radio;
	}
	
	public Circulo(double x, double y, double radio,String tipo) {
		super(x, y, tipo);
		this.radio=radio;
	}

	public static void main(String[] args) {

	}

	@Override
	public double area() {
		return Math.PI*radio*radio;
	}

	@Override
	public double perimetro() {
		return 2*Math.PI*radio;
	}
	
	@Override
	public String toString() {
		return "Circulo("+radio+")";		
	}

}
