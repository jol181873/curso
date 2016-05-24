package micalculadora;

/**
 * Operacion binaria producto
 * 
 * @author usrcur200
 *
 */
public class Producto extends OpBinaria {
	public Producto() {
		
	}
	
	public Producto(Hoja izquierda, Hoja derecha,int prioridad) {
		super(izquierda, derecha, "*", prioridad);
	}
	
	@Override
	public double calcular(double izquierdaDouble,double derechaDouble) {			
		if (derecha instanceof OpBinaria && prioridad>((OpBinaria)derecha).prioridad) {
			
			// tenemos que entrar en la siguiente op binaria y extraer el operando izquierdo
			// cuenta casi casi manual y bastante cutre
			// ¿mejorar con las prioridades? ¿cómo?
			
			Hoja aux=((OpBinaria)derecha).izquierda;			
			double derecha1=aux.get();			
			
			Hoja aux2=((OpBinaria) derecha).derecha;
			double derecha2=aux2.get();
			
			OpBinaria opDerecha=(OpBinaria) derecha;
			
			return opDerecha.calcular(izquierdaDouble*derecha1,derecha2);													
		}
		
		return izquierdaDouble*derechaDouble;
	}	
	
	@Override
	public double getElementoNeutro() {
		return 1;
	}	
}