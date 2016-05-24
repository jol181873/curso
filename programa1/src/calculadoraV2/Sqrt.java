package calculadoraV2;

public class Sqrt extends Operaciones {

	public Sqrt() {
		super("raiz cuadrada","sqrt",true);
	}

	@Override
	public double realizaOperacion(double resultado, double numero) {
		return 0;
	}

	@Override
	public double realizaOperacionEspecial(double resultado) {
		if (resultado<0) {
			setError("ERROR raiz cuadrada de un número negativo");
			return Double.NaN;
		}
		return Math.sqrt(resultado);
	}

}
