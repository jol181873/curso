package ejemplos;

public abstract class  Vehiculo_Motor {
	protected int potencia;
	protected double velocidad;
	protected double combustible;
	protected boolean encendido;
	protected double velocidad_Max;
	protected double combustible_Max;
	protected String tipo_vehiculo;
	
	public Vehiculo_Motor(int potencia, double velocidad_Max,double combustible_Max, String tipo){
		this.potencia= potencia;
		this.velocidad_Max = velocidad_Max;
		this.combustible_Max= combustible_Max;
		tipo_vehiculo= tipo;
		velocidad = 0;
		combustible = 0;
		encendido = false;		
		
	}
	
	public double verVelocidad(){
		System.out.println("Velocidad "+this.velocidad);
		return this.velocidad;
	}
	
	public double verCombustible(){
		System.out.println("Combustible "+this.combustible);
		return this.combustible;
	}
	
	public void encenderVehiculo(){
		encendido= true;
		System.out.println("Encendiendo "+this.tipo_vehiculo);
	}
	
	public void apagarVehiculo(){
		encendido = false;
		System.out.println("Apagando "+this.tipo_vehiculo);
		
	}
	/*
	 * metodos a implementar
	 *
	 * */
	public abstract void acelerar();
	public abstract void frenar();
	public abstract void conducir();
	public abstract void repostar();
	
	

}
