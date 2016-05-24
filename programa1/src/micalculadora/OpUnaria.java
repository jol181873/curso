package micalculadora;

/**
 * Actualmente no se usa, en el futuro incluir funciones o constantes (?) como subclase de esta
 * 
 * @author usrcur200
 *
 */
public class OpUnaria extends Operacion {
	Hoja centro;

	public OpUnaria(Hoja centro,String operacion,int prioridad) {
		super(operacion,prioridad);
		this.centro = centro;
	}	
	
	public String toString() {
		return "OpUnaria("+centro+")";
	}
	
	public double get() {
		return centro.get();
	}
}