package micalculadora;

/**
 * Hoja que almacena un valor numerico real
 * 
 * @author usrcur200
 *
 */
public class HojaValor extends Hoja {
	private double valor;
	
	public HojaValor(double valor) {
		super(Integer.MIN_VALUE);
		this.valor=valor;
	}
	
	public String toString() {
		return "HojaValor("+valor+")";
	}

	@Override
	public double get() {		
		return valor;
	}
}