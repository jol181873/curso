package micalculadora;

/**
 * Clase abstracta cuyas hijas son operaciones (OpBinaria,OpUnaria) o valores reales (HojaValor)
 * 
 * @author usrcur200
 */
public abstract class Hoja {
	/**
	 * No tiene uso efectivo, se mantiene porque seguramente sea posible utilizarlo para mejorar el 
	 * algoritmo
	 */
	int prioridad;
	
	public Hoja() {
		
	}
	
	public Hoja(int prioridad) {
		this.prioridad=prioridad;
	}
	
	/**
	 * Obtiene el valor numerico de la hoja. Dependiendo de su subtipo, el calculo sera diferente
	 * 
	 * @return un numero real
	 */
	public abstract double get();	
}