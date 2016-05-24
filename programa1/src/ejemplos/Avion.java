package ejemplos;


public class Avion extends Vehiculo_Motor{

	private String compania;
	private String tipo_avion;
	private String motor;
	private int num_motores;
	private int plazas;
	private int capacidad;
	
	private boolean acelerator;
	private boolean freno;

	
	public Avion(int potencia, double velocidad_Max, double combustible_Max, String tipo,
			String compania, String tipo_avion, String motor, int num_motores, int plazas, int capacidad) {
		super(potencia, velocidad_Max, combustible_Max, tipo);
		this.compania = compania;
		this.tipo_avion = tipo_avion;
		this.motor = motor;
		this.num_motores = num_motores;
		this.plazas = plazas;
		this.capacidad = capacidad;
		acelerator = false;
		freno = false;
	}	

	@Override
	public void acelerar() {
		acelerator = true;
		if(acelerator && velocidad<=velocidad_Max){
			velocidad= velocidad+10;
			acelerator = false;
		}
		
	}

	@Override
	public void frenar() {
		freno = true;
		if(freno && velocidad<=10){
			velocidad= velocidad-10;
			freno = false;
		}
	}

	@Override
	public void conducir() {
		System.out.println("Estamos volando");		
	}

	@Override
	public void repostar() {	
		if(combustible <= combustible_Max){
			combustible = combustible+10;
		}
		
	}

}
