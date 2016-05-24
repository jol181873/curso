package ejemplos;
public class Tren extends Vehiculo_Motor{

	
	
	private int viajeros;
	private int capacidad_viajeros;
	

	public Tren(double x, double y,double velocidad, double combustible, double ver_velocidad, int capacidad_viajeros, int hora_salida, int hora_llegada, String tipo_vehiculo) {
		super(hora_llegada, ver_velocidad, ver_velocidad, tipo_vehiculo);
		
	}
	
	public void acelerar(){
		
		while(velocidad<velocidad_Max)
			{velocidad++;}
		System.out.println("Acelerando: " + velocidad);
		
	}
	
	public void frenar(){
		
		while(velocidad>0)
			{velocidad--;}
		System.out.println("Frenando: " + velocidad);
		
	}
	
	
	public void repostar(){
		
		if(combustible<5){
			System.out.println(" Repostando "+ combustible);
			
		}
		
		
	}
	public void recoger_viajeros(){
		
		if(viajeros<capacidad_viajeros){
			
			System.out.println("Pasajeros al tren");
		}else{
			System.out.println("Acceso denegado, plazas completas");
		}
	}

	@Override
	public void conducir() {
		// TODO Auto-generated method stub
		if(velocidad>0){
			
			System.out.println(" Circulando a velocidad "+ velocidad);}
			
	}

	
	
}
