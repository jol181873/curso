package calculadoraV2;

public class Constante extends Operaciones {
	private double valor;	

	public Constante(String nombre,double valor) {
		super("constante",nombre,true);
		this.setValor(valor);
	}
	
	@Override
	public double realizaOperacion(double resultado, double numero) {
		return 0;
	}

	@Override
	public double realizaOperacionEspecial(double resultado) {
		return 0;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
