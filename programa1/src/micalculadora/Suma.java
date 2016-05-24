package micalculadora;

/**
 * Operacion binaria suma
 * 
 * @author usrcur200
 *
 */
public class Suma extends OpBinaria {
	public Suma() {
		
	}
	
	public Suma(Hoja izquierda, Hoja derecha, int prioridad) {
		super(izquierda, derecha, "+", prioridad);
	}

	@Override
	public double calcular(double izquierdaDouble, double derechaDouble) {
		return izquierdaDouble+derechaDouble;
	}	
	
	@Override
	public double getElementoNeutro() {
		return 0;
	}
}