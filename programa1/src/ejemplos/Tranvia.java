package ejemplos;
public class Tranvia extends Vehiculo_Motor{
	private double valor_aceleracion;
	private double valor_frenar;
	private double inicio;
	private double destino;
	private double num_pasajeros;
	private double pasajeros_max;

	
	public Tranvia(int potencia, double velocidad_Max,double combustible_Max, String tipo,double valor_aceleracion, double valor_frenar, double inicio, double destino, double num_pasajeros, double pasajeros_max) {
		super(potencia,velocidad_Max,combustible_Max,tipo);
		this.valor_aceleracion=valor_aceleracion;
		this.valor_frenar=valor_frenar;
		this.inicio=inicio;
		this.destino=destino;		
		this.num_pasajeros=num_pasajeros;
		this.pasajeros_max=pasajeros_max;
		
	}
	
	public void acelerar(){
		if (verVelocidad()<velocidad_Max){
			System.out.println(verVelocidad()+valor_aceleracion);
		}
	}
	public void frenar(){
		if (verVelocidad()>0){
			System.out.println(verVelocidad()-valor_frenar);
		}
	}
	public void conducir(){
		System.out.println("El trayecto se ha realizado de la parada número "+inicio+" a la parada número "+destino+".");
	}
	public void repostar(){
		System.out.println("Necesita de una catenaria para su funcionamiento");
	}
	public void exceso_pasajeros(){
		if (pasajeros_max > num_pasajeros){
			System.out.println("Hay un exceso de pasajeros en el tranvía");
		}
	}

}
