package calculadoraV2;

public class Suma extends Operaciones {

	public Suma() {
		super("suma","+",false);
	}
	
	@Override
	public double realizaOperacion(double resultado, double numero) {				
		return resultado+numero;
	}

	@Override
	public double realizaOperacionEspecial(double resultado) {
		System.out.println("No existe");
		return 0;
	}

}
