package micalculadora;

/**
 * Clase abstracta, subclase de Hoja y superclase de OpBinaria y OpUnaria
 * 
 * @author usrcur200
 *
 */
public abstract class Operacion extends Hoja {
	/**
	 * Es el signo del operador que identifica la operacion
	 */
	String operacion;
	
	public Operacion() {
		
	}
	
	public Operacion(String operacion,int prioridad) {
		super(prioridad);
		this.operacion=operacion;	
	}
}