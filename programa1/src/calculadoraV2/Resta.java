/**
 * 
 */
package calculadoraV2;

/**
 * @author usrcur200
 *
 */
public class Resta extends Operaciones {

	/**
	 * 
	 */
	public Resta() {
		super("resta","-",false);
	}	
	
	@Override
	public double realizaOperacion(double resultado, double numero) {
		return resultado-numero;
	}

	
	@Override
	public double realizaOperacionEspecial(double resultado) {
		System.out.println("No existe");
		return 0;
	}

}
