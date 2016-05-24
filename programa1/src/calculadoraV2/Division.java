package calculadoraV2;

public class Division extends Operaciones {

	public Division() {
		super("division","/",false); 
	}

	@Override
	public double realizaOperacion(double resultado, double numero) {
		if (numero==0) {
			setError("ERROR: division por cero");
			return Double.NaN;
		}
		return resultado/numero;
	}

	@Override
	public double realizaOperacionEspecial(double resultado) {
		return 0;
	}

}
