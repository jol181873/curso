package ejemplos;

public class Coche extends Vehiculo_Motor {
	private int numeroRuedas=4;
	private int numeroAsientos;
	private int numeroPuertas;
	private String tipoCombustible;
	
	public int getNumeroRuedas() {
		return numeroRuedas;
	}

	public void setNumeroRuedas(int numeroRuedas) {
		this.numeroRuedas = numeroRuedas;
	}

	public int getNumeroAsientos() {
		return numeroAsientos;
	}

	public void setNumeroAsientos(int numeroAsientos) {
		this.numeroAsientos = numeroAsientos;
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}
	
	public Coche(int potencia, double velocidad_Max,double combustible_Max, int numeroRuedas,int numeroPuertas,int numeroAsientos,String tipoCombustible) {
		super(potencia,velocidad_Max,combustible_Max,"Coche");
		this.numeroAsientos=numeroAsientos;
		this.numeroPuertas=numeroPuertas;
		this.numeroRuedas=numeroRuedas;
		this.tipoCombustible=tipoCombustible;
	}

	// Metodos acelerar sobrecargados
	/**
	 * 
	 * @param incremento debe ser positivo
	 * @return -1 en caso de error
	 */	
	public double acelerar(double incremento) {
		if (incremento<0) {
			System.out.println("Error, parámetro negativo en acelerar");
			
			return -1;
		} else if (combustible==0) {
			System.out.println("No queda combustible para acelerar");
		} else if (this.velocidad+incremento>velocidad_Max) {
			this.velocidad=this.velocidad_Max;
				
			System.out.println("Acelerando al maximo");
		} else {
			this.velocidad=this.velocidad+incremento;
								
			System.out.println("Acelerando hasta "+this.velocidad);
		}
				
		return this.velocidad;		
	}
	
	@Override
	public void acelerar() {
		//TODO cambiar aqui
		double incremento=10;
		
		acelerar(incremento);			
	}
	//--------------------------------
	
	// Metodos frenar sobrecargados
	/**
	 * 
	 * @param decremento debe ser negativo
	 * @return -1 en caso de error
	 */
	public double frenar(double decremento) {
		if (decremento>0) {
			System.out.println("Error, parámetro positivo en frenar");
			
			return -1;
		} else if (combustible==0) {
			System.out.println("No queda combustible para frenar");
		} else if (this.velocidad+decremento<0) {
			this.velocidad=0;
			
			System.out.println("Frenando hasta 0");			
		} else {
			this.velocidad=this.velocidad+decremento;		
				
			System.out.println("Frenando hasta "+this.velocidad);
		}
							
		return this.velocidad;
	}
	
	@Override
	public void frenar() {
		//TODO cambiar aqui
		double decremento=-10;
		
		frenar(decremento);
	}
	//--------------------------------

	
	// Metodos conducir sobrecargados
	/**
	 * 
	 * @param x coordenada x del punto origen
	 * @param y coordenada y del punto origen
	 * @param x2 coordenada x del punto destino
	 * @param y2 coordenada y del punto destino
	 */
	public void conducir(double x,double y,double x2,double y2) {
		if (combustible==0) {
			System.out.println("No queda combustible para conducir");
		} else {
			System.out.println("Conduciendo desde ("+x+","+y+") hasta ("+x2+","+y2+")");
		}
	}
	
	@Override
	public void conducir() {
		int decrementoCombustible=-10;
		
		if (combustible==0) {
			System.out.println("No queda combustible para conducir");
		} else {
			combustible=combustible+decrementoCombustible;
						
			System.out.println("¡Conduciendo!, hemos perdido 10 litros de combustible");
			
			if (combustible<0) {
				combustible=0;
				
				System.out.println("¡Depósito vacío! ¡Debe repostar ya!");
			}
		}
	}
	//--------------------------------

	@Override
	public void repostar() {
		int incremento=10;
		if (encendido) {
			System.out.println("Cuidado, debe apagar el coche antes de repostar!");
		} else if (this.combustible+incremento>combustible_Max) {
			this.combustible=this.combustible_Max;
			
			System.out.println("Se ha intentado llenar el depósito más allá de su capacidad\nse ha llenado el depósito al máximo");			
		} else {
			this.combustible=this.combustible+incremento;
			
			System.out.println("Repostando +10 litros");
		}
	}
}
