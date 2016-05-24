package ejemplos;

public abstract class Figura_geometrica {
	protected double x;
	protected double y;
	protected String tipo;
	
	public Figura_geometrica(double x,double y) {
		this.x=x;
		this.y=y;
	}
	
	public Figura_geometrica(double x,double y,String tipo) {
		this.x=x;
		this.y=y;
		this.tipo=tipo;
	}
	
	public abstract double area();
	public abstract double perimetro();

}
