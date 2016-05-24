package ejemplos;
public class Submarino extends Vehiculo_Motor {

	double profundidad_Max;
	String nacionalidad;
	int numero_Torpedos;
	boolean detectable;
	boolean periscopioArriba;
	
	//Constructor con atributos de Vehiculo_Motor, profundidad máxima que alcanza el bicho, país de origen, cantidad de torpedos y booleano detectable para evitar a los curiosos
	public Submarino(int potencia, double velocidad_Max,double combustible_Max, String tipo, double profundidad_Max, String nacionalidad, int numero_Torpedos, boolean detectable) {
		super(potencia, velocidad_Max, combustible_Max, tipo);
		this.profundidad_Max = profundidad_Max;
		this.nacionalidad = nacionalidad;
		this.numero_Torpedos = numero_Torpedos;
		this.detectable = detectable;
		periscopioArriba = false;
	}

	@Override
	public void acelerar() {
		velocidad++;
		if(velocidad > velocidad_Max){
			velocidad = velocidad_Max;
		}		
	}

	@Override
	public void frenar() {	
		if(velocidad <= 0){
			velocidad = 0;
		}
		else velocidad--;
	}

	@Override
	public void conducir() {
		System.out.println("Yo pa ser feliz quiero un submarino");		
	}

	@Override
	public void repostar() {
		combustible = combustible_Max;	
		System.out.println("Depósito de combustible lleno");
	}

	//Método que lanza torpedo Whitehead a las coordenadas indicadas
	public void lanzar_Torpedo(double ObjX,double ObjY,double ObjZ){
		if (numero_Torpedos > 0) {
			numero_Torpedos--;
			System.out.println("Muere maldito");
		}
		else System.out.println("No tenemos munición!");
	}

	public void subir_periscopio() {
		periscopioArriba = true;
	}

	public void bajar_periscopio() {
		periscopioArriba = false;
	}

	public void detectar_calamar() {
		System.out.println("CalamardoSonarDetector working...");
	}
}
