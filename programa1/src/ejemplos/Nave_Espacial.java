package ejemplos;


public class Nave_Espacial extends Vehiculo_Motor{

	protected int numeroPropulsores;
	protected double temperaturaFuselaje;
	protected double temperaturaMaximaFuselaje;
	protected double nivelOxigeno;
	protected double nivelOxigenoMaximo;
	protected int numeroTripulantes;
	
	public Nave_Espacial(int potencia, double velocidad_Max,
			double combustible_Max, String tipo,
			int numeroPropulsores, double temperaturaMaximaFuselaje, 
			double nivelOxigenoMaximo, int numeroTripulantes) {
		super(potencia, velocidad_Max, combustible_Max, tipo);
		tipo_vehiculo = "Nave espacial";
		this.numeroPropulsores = numeroPropulsores;
		this.temperaturaMaximaFuselaje = temperaturaMaximaFuselaje;
		this.nivelOxigenoMaximo = nivelOxigenoMaximo;
		this.numeroTripulantes = numeroTripulantes;
		temperaturaFuselaje = 22.0;
		nivelOxigeno = 0.0;
		
	}

	@Override
	public void acelerar() {
		System.out.println(this.tipo_vehiculo + " acelerando....");
	}

	@Override
	public void frenar() {
		System.out.println(this.tipo_vehiculo + " frenando....");
		
	}

	@Override
	public void conducir() {
		System.out.println(this.tipo_vehiculo + " en modo pilotaje manual");
		
	}

	@Override
	public void repostar() {
		System.out.println(this.tipo_vehiculo + " repostando....");
		
	}
	
	public double recargarOxigeno(double cantidad){
		if ((this.nivelOxigeno + cantidad) <= this.nivelOxigenoMaximo){
			this.nivelOxigeno += cantidad;
			return (this.nivelOxigenoMaximo-this.nivelOxigeno);
		}
		else{
			this.nivelOxigeno = this.nivelOxigenoMaximo;
			return 0.0;
		}
	}

	public void despegar(){
		// Cuenta Atrás
		for (int i = 5; i >= 0; i--){
			System.out.println(i);
		}
		this.acelerar();
		System.out.println("¡¡¡ DESPEGANDO !!!");
	}

	public void eliminarPropulsores(int numeroPropulsores){
		if(numeroPropulsores <= this.numeroPropulsores){
			this.numeroPropulsores -= numeroPropulsores;
		}
		else{
			this.numeroPropulsores = 0;
		}
	}

	public void aterrizar(){
		this.frenar();
		System.out.println("¡¡Abrochense los cinturones!!");
	}

	public int medirTemperaturaFuselaje(){
		//Devuelve 0 si todo está en orden
		//Devuelve 1 Para tomar las acciones necesarias
		if(this.temperaturaFuselaje == this.temperaturaMaximaFuselaje){
			System.out.println("¡¡¡¡ ALERTA !!!!");
//			i = 1;
			return 1;
		}
		else{
			System.out.println("TODO EN ORDEN");
			return 0;
		}
	}
	

}
