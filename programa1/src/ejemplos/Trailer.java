package ejemplos;

public class Trailer extends Vehiculo_Motor {

	private int ruedas;
	private int capacidad;
	private double pesototal;
	private double tamaniolargo;

	public Trailer(int potencia, double velocidad_max, double combustible_max, String tipo_vehiculo, int ruedas,
			int capacidad, double pesototal, double tamaniolargo) {
		super(potencia, velocidad_max, combustible_max, tipo_vehiculo);
		this.ruedas = ruedas;
		this.capacidad = capacidad;
		this.pesototal = pesototal;
		this.tamaniolargo = tamaniolargo;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void acelerar() {
		// TODO Auto-generated method stub
		if (encendido == true && combustible > 0) {
			velocidad = velocidad + 10;
			combustible = combustible - 1;
			System.out.println("Acelerando");
		} else {
			System.out.println("Imposible acelerar. Trailer apagado o sin combustible");
		}
	}

	@Override
	public void frenar() {
		// TODO Auto-generated method stub
		if (velocidad > 0) {
			velocidad = velocidad - 10;
		} else {
			System.out.println("El trailer ya está parado");
		}
		if (velocidad < 10) {
			velocidad = 0;
			System.out.println("Trailer parado");
		}
	}

	@Override
	public void conducir() {
		// TODO Auto-generated method stub
		if (encendido == true && combustible > 4 && velocidad > 0) {
			combustible = combustible - 5;
			System.out.println("Conduciendo");
		} else {
			if (encendido) {
				System.out.println("Acelere primero");
			} else {
				System.out.println("Imposible conducir. Trailer apagado o sin combustible");
			}
			System.out.println("Imposible conducir. Trailer apagado o sin combustible");
		}
	}

	@Override
	public void repostar() {
		// TODO Auto-generated method stub
		combustible = combustible + 12;
		System.out.println("Trailer repostado");
	}

	public void getCombustible() {
		System.out.println("Combustible: " + combustible + " litros");
	}

	public void getVelocidad() {
		System.out.println("Velocidad: " + velocidad + " km/h");
	}

	public int getRuedas() {
		return ruedas;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public double getPesototal() {
		return pesototal;
	}

	public double getTamaniolargo() {
		return tamaniolargo;
	}

	public void caracteristicas() {
		System.out.println("Tipo de vehículo: " + tipo_vehiculo);
		System.out.println("Potencia: " + potencia + " caballos");
		System.out.println("Velocidad máxima: " + velocidad_Max + " km/h");
		System.out.println("Combustible máximo: " + combustible_Max + " litros");
		System.out.println();
	}

}
