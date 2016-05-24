package ejemplos;
public class vehiculo_Especial extends Vehiculo_Motor {

	protected String tipo_servicio;
	protected boolean remolque;
	
	
	public vehiculo_Especial(int potencia, double velocidad_Max, double combustible_Max, String tipo, String tipo_servicio) {
		super(potencia, velocidad_Max, combustible_Max, tipo);
		this.tipo_servicio=tipo_servicio; // La tarea que realiza el Vehiculo Especial
	}

	@Override
	public void acelerar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frenar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void conducir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repostar() {
		// TODO Auto-generated method stub
		
	}
	
	public void comprobarRemolque(){ // comprobar si el vehiculo especial tiene remolque
		remolque= true;
		System.out.println(this.tipo_vehiculo+" Con remolque ");
	}
	
	public double verTipo_Servicio(){
		System.out.println("Tipo de servicio: "+this.tipo_servicio);
		return this.combustible;
	}

}
