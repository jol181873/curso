package calculadoraV2;

public abstract class Operaciones {
	private String tipoOperacion;
	private String simboloOperacion;
	private boolean especial;
	private String error;
	
	public Operaciones() {
		
	}
		
	public Operaciones(String tipoOperacion, String simboloOperacion, boolean especial) {
		this.tipoOperacion = tipoOperacion;
		this.simboloOperacion = simboloOperacion;
		this.especial = especial;
		this.setError("");
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}
	
	public String getSimboloOperacion() {
		return simboloOperacion;
	}
	
	
	
	public boolean isEspecial() {
		return especial;
	}
	
	/**
	 * Operacion de operador estandar
	 * @param resultado
	 * @param numero
	 * @return
	 */
	public abstract double realizaOperacion(double resultado,double numero);
	
	/**
	 * Operacion de operador especial
	 * @param resultado
	 * @return
	 */
	public abstract double realizaOperacionEspecial(double resultado);

	public String getError() {
		String err=error;
		error="";
		return err;
	}

	public void setError(String error) {
		this.error = error;
	}
}
