package micalculadora;

/**
 * Operacion binaria suma
 * 
 * @author usrcur200
 *
 */
public class Resta extends OpBinaria {
	public Resta() {
		
	}
	
	public Resta(Hoja izquierda, Hoja derecha, int prioridad) {
		super(izquierda, derecha, "-", prioridad);
	}

	@Override
	public double calcular(double izquierdaDouble, double derechaDouble) {
		return izquierdaDouble-derechaDouble;
	}	
	
	@Override
	public double getElementoNeutro() {
		return 0;
	}
}