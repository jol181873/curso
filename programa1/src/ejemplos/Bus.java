package ejemplos;


public class Bus extends Vehiculo_Motor{
	//declaraciones
	private int ruedas, pasajeros, asientos;
	private String marca, modelo, linea;
	private boolean wc;
	//constructor
	public Bus(int potencia, double velocidad_Max, double combustible_Max, String tipo,
			int ruedas, int asientos, String marca, String modelo, String linea,
			boolean wc) {
		super(potencia, velocidad_Max, combustible_Max, tipo);
		this.ruedas = ruedas;
		pasajeros = 0;
		this.asientos = asientos;
		this.marca = marca;
		this.modelo = modelo;
		this.linea = linea;
		this.wc = wc;
	}
	//Getters
	public void getRuedas(){
		System.out.println("Ruedas: " + ruedas);
	}
	public void getPasajeros(){
		System.out.println("Pasajeros en el bus: " + pasajeros);
	}
	public void getAsientos(){
		System.out.println("Asientos: " + asientos);
	}
	public void getWc(){
		String resultado;
		if (wc) resultado ="Tiene baño";
		else resultado = "No tiene baño";
		System.out.println(resultado);
	}
	public void getMarca(){
		System.out.println("Marca: " + marca);
	}
	public void getModelo(){
		System.out.println("Modelo: " + modelo);
	}
	public void getLinea(){
		System.out.println("Linea: " + linea);
	}
	//Métodos
	@Override
	public void acelerar() {
		if (velocidad > velocidad_Max){
			velocidad = velocidad_Max;
			System.out.println("¡CUIDADO!");
		}else{
			if (velocidad + 10 >= velocidad_Max){
				velocidad = velocidad_Max;
				System.out.println("Viajando a velocidad máxima");
			}else velocidad += 10;
		}
		verVelocidad();
	}

	@Override
	public void frenar() {
		if (velocidad - 10 <= 0){
			velocidad = 0;
			System.out.println("Autobús parado");
		}
		else{
			velocidad -= 10;	
			System.out.println("Frenando");
		}
		verVelocidad();
	}

	@Override
	public void conducir() {}
	public void conducir(double ox, double oy, double dx, double dy, int entrantes, int salientes) {
		System.out.println("Origen: " + ox + ", " + oy);
		System.out.println("Destino: " + dx + ", " + dy);
		if ((pasajeros - salientes)<0)pasajeros = 0;
		else pasajeros -= salientes;
		if ((pasajeros + entrantes) > asientos){
			pasajeros = asientos;
			System.out.println("No pudieron entrar tantos pasajeros al bus debido a que se superó la capacidad máxima");			
		}else{
			pasajeros += entrantes;
			getPasajeros();
		}
	}

	@Override
	public void repostar() {}
	public void repostar(double cantidad){
		if ((combustible + cantidad) >= combustible_Max){
			combustible = combustible_Max;
			System.out.println("Depósito lleno");
		}else		
			combustible += cantidad;		
		verCombustible();		
	}

}
