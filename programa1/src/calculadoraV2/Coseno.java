package calculadoraV2;

public class Coseno extends Operaciones {
	public Coseno() {
		super("coseno","cos",true);
	}

	@Override
	public double realizaOperacion(double resultado, double numero) {
		System.out.println("No existe");
		return 0;
	}

	/**
	 * @param resultado en radianes
	 */
	@Override	
	public double realizaOperacionEspecial(double resultado) {
		return Math.cos(resultado);
	}

}
