package micalculadora;

/**
 * Operacion binaria, tiene una Hoja izquierda (primer operando) y una derecha (segundo operando)
 *  
 * @author usrcur200
 *
 */
public abstract class OpBinaria extends Operacion {
	Hoja izquierda;
	Hoja derecha;
	
	public OpBinaria() {
	}
		
	public OpBinaria(Hoja izquierda,Hoja derecha,String operacion,int prioridad) {
		super(operacion,prioridad);
		this.izquierda=izquierda;
		this.derecha=derecha;
	}
	
	@Override
	public String toString() {
		return "OpBinaria("+prioridad+","+izquierda+operacion+derecha+")";
	}
	
	public double get() {
		double izquierdaDouble=this.izquierda.get();
		double derechaDouble=this.derecha.get();
		
		return calcular(izquierdaDouble,derechaDouble);
	}
	
	/**
	 * Computo efectivo de la operacion sobre dos operandos reales
	 * 
	 * @param izquierdaDouble operando izquierdo
	 * @param derechaDouble operando derecho
	 * @return el resultado de izquierdaDouble op derechaDouble, 
	 */
	public abstract double calcular(double izquierdaDouble,double derechaDouble);	
	
	/**
	 * Elemento neutro de la opracion binaria
	 * Ejemplo: en el producto sera igual a 1.0 y en la suma a 0.0
	 * 
	 * @return el elemento neutro de la operacion
	 */
	public abstract double getElementoNeutro(); 
}